package Server.Model;


public class OrdersEntity {
    private static final long serialVersionUID = 1L;
    private int orderNumber;
    private double totalPrice;
    private String status;

    public OrdersEntity(){}
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int idOrder) {
        this.orderNumber = idOrder;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

