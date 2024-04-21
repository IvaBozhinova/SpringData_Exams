package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DeviceDTO;
import softuni.exam.models.dto.DeviceRootDTO;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.service.SaleService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParserImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final String DEVICES_FILE_PATH = "src/main/resources/files/xml/devices.xml";
    private final DeviceRepository deviceRepository;
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validator;
    private final XmlParserImpl xmlParser;


    public DeviceServiceImpl(DeviceRepository deviceRepository, SaleRepository saleRepository, ModelMapper modelMapper, ValidationUtilImpl validator, XmlParserImpl xmlParser, SellerService sellerService) {
        this.deviceRepository = deviceRepository;
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlParser = xmlParser;

    }
    @Override
    public boolean areImported() {
        return this.deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(DEVICES_FILE_PATH)));
    }

    @Override
    public String importDevices() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        DeviceRootDTO rootDto = this.xmlParser.fromFile(this.readDevicesFromFile(), DeviceRootDTO.class);

        for (DeviceDTO dto : rootDto.getDevicesList()) {

            Optional<Device> optionalDevice =
                    this.deviceRepository.findByBrandAndModel(dto.getBrand(), dto.getModel());

            Optional<Sale> sale = saleRepository.findById(dto.getSale());

            if (!this.validator.isValid(dto) || optionalDevice.isPresent() || sale.isEmpty()) {
                sb.append("Invalid device");
                sb.append(System.lineSeparator());
                continue;
            }

            Device device = this.modelMapper.map(dto, Device.class);
            device.setSale(sale.get());
            this.deviceRepository.saveAndFlush(device);
            sb.append(String.format("Successfully imported device of type %s with brand %s",
                    device.getModel(), device.getBrand()));
            sb.append(System.lineSeparator());
        }


        return sb.toString();
    }


    @Override
    public String exportDevices() {
        StringBuilder sb = new StringBuilder();



        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.00", symbols);
        this.deviceRepository.findAllSmartPhonesCheaperThan1000AndStorageMoreThan128().forEach(d -> {
            sb.append("Device brand: ").append(d.getBrand()).append("\n");
            sb.append("   *Model: ").append(d.getModel()).append("\n");
            sb.append("   **Storage: ").append(d.getStorage()).append("\n");
            sb.append("   ***Price: ").append(decimalFormat.format(d.getPrice())).append("\n");
        });

        return sb.toString();
    }}

