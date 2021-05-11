package controller;

import domain.Operator;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class SignInController {
    private Stage primaryStage;
    private Service service;

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setService(Service service){
        this.service = service;
    }


    @FXML
    Button buttonLogInUser;
    @FXML
    Button buttonLogInOperator;
    @FXML
    TextField textBoxCnp;
    @FXML
    TextField textBoxPassword;

    public void handleButtonLogInUser() throws IOException {
        String cnp = textBoxCnp.getText();
        String password = textBoxPassword.getText();
        User user = service.findUserByCnpAndPassword(cnp, password);
        if(user != null) {
            Integer id = user.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserWindow.fxml"));
            Parent root = loader.load();
            UserWindowController ridesAndReservationsController = loader.getController();
            ridesAndReservationsController.setId(id);
            ridesAndReservationsController.setService(service);
            ridesAndReservationsController.setPrimaryStage(primaryStage);

            Scene userScene = new Scene(root);
            primaryStage.setTitle("Hello user!");
            primaryStage.setScene(userScene);
            primaryStage.show();

        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error logging in!");
            alert.setResizable(true);
            alert.showAndWait();
        }

    }

    public void handleButtonLogInOperator() throws IOException {
        String cnp = textBoxCnp.getText();
        String password = textBoxPassword.getText();
        Operator operator = service.findOperatorByCnpAndPassword(cnp, password);
        if(operator != null) {
            Integer id = operator.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminWindow.fxml"));
            Parent root = loader.load();
            OperatorWindowController ridesAndReservationsController = loader.getController();
            ridesAndReservationsController.setId(id);
            ridesAndReservationsController.setService(service);
            ridesAndReservationsController.setPrimaryStage(primaryStage);

            Scene userScene = new Scene(root);
            primaryStage.setTitle("Hello admin!");
            primaryStage.setScene(userScene);
            primaryStage.show();

        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error logging in!");
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

}
