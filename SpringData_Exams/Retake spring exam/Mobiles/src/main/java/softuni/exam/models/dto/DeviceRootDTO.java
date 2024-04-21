package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


    @XmlRootElement(name = "devices")
    @XmlAccessorType(XmlAccessType.FIELD)
    public class DeviceRootDTO{

        @XmlElement(name = "device")
        private List<DeviceDTO> devices;

        public DeviceRootDTO() {
            this.devices = new ArrayList<>();
        }

        public List<DeviceDTO> getDevicesList() {
            return devices;
        }

        public void setDevices(List<DeviceDTO> devices) {
            this.devices = devices;
        }
}
