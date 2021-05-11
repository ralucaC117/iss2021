package controller;

import domain.Exemplar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.Service;

public class OperatorWindowController {
    private Integer currentOperatorId;
    private Service service;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setService(Service service){
        this.service = service;
    }

    public void setId(Integer id){
        this.currentOperatorId = id;
    }

    @FXML
    public void initialize(){
    }

    public void handleButtonLogOut(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignInWindow.fxml"));
            Parent root = loader.load();
            SignInController startUpController = loader.getController();
            startUpController.setService(service);
            startUpController.setPrimaryStage(primaryStage);

            Scene operatorScene = new Scene(root);
            primaryStage.setTitle("Sign in");
            primaryStage.setScene(operatorScene);
            primaryStage.show();}
        catch (Exception exception){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error logging out!" + exception);
            alert.showAndWait();
        }
    }
}
