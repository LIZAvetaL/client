package Entity;

import javax.persistence.*;
import java.io.Serializable;



public class ProductEntity implements Serializable{

    private int id_product;
    private String type;
    private String name;

    private int amount;

    private double price;



    public ProductEntity(){}

    public ProductEntity( String type, String nameproduct, int amount, double price){
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id_product != that.id_product) return false;
        if (amount != that.amount) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price!= that.price) return false;

        return true;
    }

}
