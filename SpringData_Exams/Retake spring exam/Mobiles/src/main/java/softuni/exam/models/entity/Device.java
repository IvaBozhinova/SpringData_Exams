package softuni.exam.models.entity;


import softuni.exam.models.enums.DeviceType;

import javax.persistence.*;

@Entity
@Table(name = "devices")
public class Device extends BaseEntity{

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false, unique = true)
    private String model;

    @Column(name = "device_type")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Column(name = "price")
    private Double price;

    @Column(name = "storage")
    private int storage;

    @ManyToOne
    private Sale sale;


    public Device(){};

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
    public Sale getSale() {
        return sale;
    }

    public Device setSale(Sale sale) {
        this.sale = sale;
        return this;
    }
}
