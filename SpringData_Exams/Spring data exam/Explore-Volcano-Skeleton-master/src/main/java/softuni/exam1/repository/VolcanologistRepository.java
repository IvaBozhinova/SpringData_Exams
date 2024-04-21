package softuni.exam1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam1.models.entity.Volcanologist;

import java.util.Optional;

@Repository
public interface VolcanologistRepository extends JpaRepository<Volcanologist, Long> {

    Optional<Volcanologist> findByFirstNameAndLastName(String firstName, String lastName);
}
