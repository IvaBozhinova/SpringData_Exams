package softuni.exam1.areImported;
//TestCountryServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam1.repository.CountryRepository;
import softuni.exam1.service.impl.CountryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestCountryServiceAreImportedFalse {

    @InjectMocks
    private CountryServiceImpl countryService;
    @Mock
    private CountryRepository mockCountryRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockCountryRepository.count()).thenReturn(0L);
        Assertions.assertFalse(countryService.areImported());
    }
}