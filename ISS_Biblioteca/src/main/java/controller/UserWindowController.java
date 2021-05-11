package controller;

import domain.Exemplar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.Service;

public class UserWindowController {
    private Integer currentUserId;
    private Service service;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setService(Service service){
        this.service = service;
        exemplarsObservableList.setAll(service.findAllExemplars());
    }

    public void setId(Integer id){
        this.currentUserId = id;
    }

    @FXML
    TableView<Exemplar> tableViewExemplars;
    @FXML
    TableColumn<Exemplar, Integer> columnIdExemplar;
    @FXML
    TableColumn<Exemplar, String> columnTitle;
    @FXML
    TableColumn<Exemplar, String> columnAuthor;

    ObservableList<Exemplar> exemplarsObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        columnIdExemplar.setCellValueFactory(new PropertyValueFactory<Exemplar, Integer>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("clientName"));
        this.tableViewExemplars.setItems(exemplarsObservableList);
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
