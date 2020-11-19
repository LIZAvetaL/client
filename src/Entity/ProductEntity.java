package Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductEntity{

    private int id_product;
    private String type;
    private String name;
    private int amount;
    private double price;

public ProductEntity(){}
    public ProductEntity(int idproduct, String type, String nameproduct, int amount){
        this.id_product=idproduct;
        this.type=type;
        this.name=nameproduct;
        this.amount=amount;
        this.price=price;

    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int idproduct) {
        this.id_product = idproduct;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String nameproduct) {
        this.name = nameproduct;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
