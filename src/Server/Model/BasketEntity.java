package Server.Model;

import java.io.Serializable;

public class BasketEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    private int idBasket;
    private int amount;
    private double price;
    private ProductEntity product;
    private String name;

    public BasketEntity(){}

    public int getId() {
        return idBasket;
    }

    public void setId(int id) {
        this.idBasket = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = product.getName();
    }

    public String getName() {
        return product.getName();
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public ProductEntity getProduct() {
        return product;
    }
}