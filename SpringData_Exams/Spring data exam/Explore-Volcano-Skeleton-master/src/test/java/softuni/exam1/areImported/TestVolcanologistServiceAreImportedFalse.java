package softuni.exam1.areImported;
//TestVolcanologistServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam1.repository.VolcanologistRepository;
import softuni.exam1.service.impl.VolcanologistServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestVolcanologistServiceAreImportedFalse {


    @InjectMocks
    private VolcanologistServiceImpl astronomerService;
    @Mock
    private VolcanologistRepository mockVolcanologistRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockVolcanologistRepository.count()).thenReturn(0L);
        Assertions.assertFalse(astronomerService.areImported());
    }
}