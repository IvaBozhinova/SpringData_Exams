package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SaleDTO;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    private final String SALE_FILE_PATH = "src/main/resources/files/json/sales.json";
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validator;
    private final Gson gson;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper, ValidationUtilImpl validator, Gson gson) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
      return  this.saleRepository.count()>0;
    }

    @Override
    public String readSalesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(SALE_FILE_PATH)));
    }

    @Override
    public String importSales() throws IOException {
        StringBuilder sb = new StringBuilder();
        SaleDTO[] importDtos = this.gson.fromJson(this.readSalesFileContent(), SaleDTO[].class);

        for (SaleDTO dto : importDtos) {
            Optional<Sale> optionalSale = this.saleRepository.findByNumber(dto.getNumber());

            if (!this.validator.isValid(dto) || optionalSale.isPresent()) {
                sb.append("Invalid sale");
                sb.append(System.lineSeparator());
                continue;
            }

            Sale sale = this.modelMapper.map(dto, Sale.class);
            this.saleRepository.saveAndFlush(sale);
            sb.append(String.format("Successfully imported sale with number %s", sale.getNumber()));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }



}
