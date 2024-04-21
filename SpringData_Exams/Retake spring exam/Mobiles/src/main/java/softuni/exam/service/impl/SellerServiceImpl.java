package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerDTO;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    private final String SELLER_FILE_PATH = "src/main/resources/files/json/sellers.json";
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validator;
    private final Gson gson;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, ValidationUtilImpl validator, Gson gson) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count()>0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(SELLER_FILE_PATH)));}

    @Override
    public String importSellers() throws IOException {
        StringBuilder sb = new StringBuilder();
        SellerDTO[] importDtos = this.gson.fromJson(this.readSellersFromFile(),SellerDTO[].class);

        for (SellerDTO dto : importDtos) {
            Optional<Seller> optionalSeller = this.sellerRepository.findByLastName(dto.getLastName());
        //    Optional<Seller> sellerByNumber = sellerRepository.findByPersonalNumber(dto.getPersonalNumber());
            if (!this.validator.isValid(dto) || optionalSeller.isPresent()) {
                sb.append("Invalid seller");
                sb.append(System.lineSeparator());
                continue;
            }

            Seller seller = this.modelMapper.map(dto, Seller.class);
            this.sellerRepository.saveAndFlush(seller);
            sb.append(String.format("Successfully imported seller %s %s", seller.getFirstName(), seller.getLastName()));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    }
