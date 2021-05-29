package controller;

import domain.Exemplar;
import domain.ImprumutDTO;
import domain.Review;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.Service;

public class ReviewsController {
    private Integer currentUserId;
    private Service service;
    private Stage primaryStage;
    private Integer currentExemplar;


    @FXML
    TableView<Review> tableViewReviews;
    @FXML
    TableColumn<Review, Integer> columnUserName;

    @FXML
    TextArea textReview;
    @FXML
    TextArea textMyReview;


    ObservableList<Review> reviewsObservableList = FXCollections.observableArrayList();

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
    }

    public void setService(Service service) {
        this.service = service;
        this.reviewsObservableList.setAll(service.findAllReviewsForExemplar(currentExemplar));

    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setCurrentExemplar(Integer currentExemplar) {
        this.currentExemplar = currentExemplar;
    }

    @FXML
    public void initialize() {
        columnUserName.setCellValueFactory(new PropertyValueFactory<Review, Integer>("userId"));
        this.tableViewReviews.setItems(reviewsObservableList);
    }

        public void handleButtonBack(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserWindow.fxml"));
            Parent root = loader.load();
            UserWindowController ridesAndReservationsController = loader.getController();
            ridesAndReservationsController.setId(currentUserId);
            ridesAndReservationsController.setService(service);
            ridesAndReservationsController.setPrimaryStage(primaryStage);

            Scene reviewsScene = new Scene(root);
            primaryStage.setTitle("Hello user!");
            primaryStage.setScene(reviewsScene);
            primaryStage.show();}
        catch (Exception exception){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error" + exception);
            alert.showAndWait();
        }
    }

    public void handleSeeReview(){
        Integer idUser = tableViewReviews.getSelectionModel().getSelectedItem().getUserId().getId();
        Review review = service.findReviewByUser(this.currentExemplar, idUser);
        textReview.setText(review.getText());

    }

    public void handleButtonPostReview(){
        Review review = new Review();
        User user = service.findOneUser(currentUserId);
        Exemplar exemplar = service.findOneExemplar(currentExemplar);
        review.setUserId(user);
        review.setExemplarId(exemplar);
        review.setText(textMyReview.getText());
        service.addReview(review);
        this.reviewsObservableList.setAll(service.findAllReviewsForExemplar(currentExemplar));
        this.tableViewReviews.setItems(reviewsObservableList);
        textMyReview.clear();

    }
}
