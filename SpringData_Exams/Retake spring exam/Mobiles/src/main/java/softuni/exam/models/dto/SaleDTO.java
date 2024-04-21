package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.Seller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class SaleDTO {
    @Expose
    @NotNull
    @Size(min = 7,max = 7)
    private String number;

    @Expose
    @NotNull
    private boolean discounted;

    @Expose
    @NotNull
    private String saleDate;

    @Expose
    private long seller;

    public SaleDTO() {}
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean Discounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this. discounted = discounted;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate=saleDate;
    }

    public long getSeller() {
        return seller;
    }

    public void setSeller(long seller) {
        this.seller = seller;
    }
}
