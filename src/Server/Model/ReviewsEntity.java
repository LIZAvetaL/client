package Server.Model;

import java.io.Serializable;

public class ReviewsEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String review;
    private UsersEntity user;
    private ProductEntity product;

    public ReviewsEntity(){}
    public int getIdReview() {
        return id;
    }

    public void setIdReview(int idReview) {
        this.id = idReview;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getReview() {
        return review;
    }

    public void setReviews(String review) {
        this.review = review;
    }


}
