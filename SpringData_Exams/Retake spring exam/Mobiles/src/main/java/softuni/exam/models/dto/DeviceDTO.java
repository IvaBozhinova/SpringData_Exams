package softuni.exam.models.dto;


import softuni.exam.models.enums.DeviceType;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDTO {
    @XmlElement(name = "brand")
    @NotNull
    @Size(min = 2, max = 20)
    private String brand;

    @XmlElement(name = "device_type")
    private DeviceType deviceType;

    @XmlElement(name = "model")
    @NotNull
    @Size(min = 1, max = 20)
    private String model;

    @XmlElement
    @Positive
    private Double price;

    @XmlElement
    @Positive
    private int storage;

    @XmlElement(name = "sale_id")
    private long sale;

    public DeviceDTO() {}

    public DeviceType getDeviceType(){return  deviceType;}

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model= model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
    public long getSale() {
        return sale;
    }

    public void setSale(long sale) {
        this.sale= sale;
    }
}
