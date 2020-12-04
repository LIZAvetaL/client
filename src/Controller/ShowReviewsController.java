package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Server.Model.ReviewsEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ShowReviewsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label reviewArea;

    @FXML
    private Label modelField;

    @FXML
    private Label userField;

    @FXML
    private Label MessageLabel;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;
    private List<ReviewsEntity> review= (List<ReviewsEntity>) Client.is.readObject();
    private int current=0;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        reviewArea.setWrapText(true);
        MessageLabel.setWrapText(true);

        if(review.size()== 0) MessageLabel.setText("На выбранный товар нет отзывов");
        else{
            modelField.setText(review.get(current).getProduct().getName());
            showReview(review.get(current));
        }

    }

    private void showReview(ReviewsEntity review) {
        MessageLabel.setText("");
        userField.setText(review.getUser().getLogin());
        reviewArea.setText(review.getReview());
    }

    public void clickNext(ActionEvent actionEvent) {
        if (current+1<review.size()) showReview(review.get(++current));
        else MessageLabel.setText("Это крайний отзыв");
    }
    public void clickPrev(ActionEvent actionEvent) {
        if (current-1>=0) showReview(review.get(--current));
        else MessageLabel.setText("Это крайний отзыв");
    }

    public ShowReviewsController() throws IOException, ClassNotFoundException {
    }
}
