package Entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


public class BasketEntity {
    private int amount;
    private double price;
    private String name;
    public BasketEntity(){}

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}