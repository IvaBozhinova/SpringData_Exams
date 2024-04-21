package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Device;
import softuni.exam.models.enums.DeviceType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {



   // Optional<Device> findByModel(String model);

    Optional<Device> findByBrandAndModel(String brand, String model);

//    List<Device> findDeviceByPriceLessThanAndDeviceTypeAndStorageGreaterThanEqualOrderByBrandAsc(Double price,DeviceType type, int storage);

    @Query("select d from Device d where d.price < 1000 and  d.deviceType = 'SMART_PHONE' and d.storage >= 128" +
            " order by LOWER(d.brand) asc")
    List<Device> findAllSmartPhonesCheaperThan1000AndStorageMoreThan128();
}
