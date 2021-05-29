package controller;

import domain.Exemplar;
import domain.Imprumut;
import domain.ImprumutDTO;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserWindowController {
    private Integer currentUserId;
    private Service service;
    private Stage primaryStage;
    private List<Exemplar> exemplareImprumutCurent  = new ArrayList<>();

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setService(Service service){
        this.service = service;
       afiseazaToateExemplarele();

    }

    public void afiseazaToateExemplarele(){
        exemplarsObservableList.setAll(service.findAllExemplars());
        exemplareImprumutCurentObservableList.setAll(exemplareImprumutCurent);
        imprumuturiUserCurent.setAll(service.findImprumuturiUser(currentUserId).stream()
                .filter(imprumut -> imprumut.getUser().getId() == currentUserId)
                .map(ImprumutDTO::new)
                .collect(Collectors.toList()));
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

    @FXML
    TableView<Exemplar> tableViewExemplareImprumut;
    @FXML
    TableColumn<Exemplar, Integer> columnIdExemplarImprumut;
    @FXML
    TableColumn<Exemplar, String> columnNumeExemplarImprumut;
    @FXML
    TableColumn<Exemplar, String> columnAutorExemplarImprumut;

    @FXML
    TableView<ImprumutDTO> tableViewImprumuturi;
    @FXML
    TableColumn<ImprumutDTO, Integer> columnIdImprumut;
    @FXML
    TableColumn<ImprumutDTO, LocalDate> columnDataImprumut;
    @FXML
    TableColumn<ImprumutDTO, LocalDate> columnDataRestituire;


    ObservableList<Exemplar> exemplarsObservableList = FXCollections.observableArrayList();
    ObservableList<Exemplar> exemplareImprumutCurentObservableList = FXCollections.observableArrayList();
    ObservableList<ImprumutDTO> imprumuturiUserCurent = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        columnIdExemplar.setCellValueFactory(new PropertyValueFactory<Exemplar, Integer>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("author"));
        this.tableViewExemplars.setItems(exemplarsObservableList);


        columnIdExemplarImprumut.setCellValueFactory(new PropertyValueFactory<Exemplar, Integer>("id"));
        columnNumeExemplarImprumut.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("title"));
        columnAutorExemplarImprumut.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("author"));
        this.tableViewExemplareImprumut.setItems(exemplareImprumutCurentObservableList);

        columnIdImprumut.setCellValueFactory(new PropertyValueFactory<ImprumutDTO, Integer>("id"));
        columnDataImprumut.setCellValueFactory(new PropertyValueFactory<ImprumutDTO, LocalDate>("date"));
        columnDataRestituire.setCellValueFactory(new PropertyValueFactory<ImprumutDTO, LocalDate>("dataRestituire"));
        this.tableViewImprumuturi.setItems(imprumuturiUserCurent);
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


    public void handleButtonAdaugaPentruImprumut(ActionEvent actionEvent) {
        Exemplar selected = tableViewExemplars.getSelectionModel().getSelectedItem();
        this.exemplareImprumutCurent.add(selected);
        exemplareImprumutCurentObservableList.setAll(exemplareImprumutCurent);
        this.tableViewExemplareImprumut.setItems(exemplareImprumutCurentObservableList);
    }

    public void handleButtonEliminaExemplar(){
        Exemplar selected = tableViewExemplars.getSelectionModel().getSelectedItem();
        this.exemplareImprumutCurent.remove(selected);
        exemplareImprumutCurentObservableList.setAll(exemplareImprumutCurent);
        this.tableViewExemplareImprumut.setItems(exemplareImprumutCurentObservableList);
    }

    public void handleButtonFinalizeazaImprumut(){
        User thisUser = service.findOneUser(this.currentUserId);
        Imprumut imprumut = new Imprumut(thisUser, exemplareImprumutCurent, LocalDate.now());
        this.service.saveImprumut(imprumut);
        //update imprumuturi
        imprumuturiUserCurent.setAll(service.findImprumuturiUser(currentUserId).stream().filter(imp -> imp.getUser().getId() == currentUserId).map(ImprumutDTO::new).collect(Collectors.toList()));
        this.tableViewImprumuturi.setItems(imprumuturiUserCurent);
        //update exemplare
        for(Exemplar exemplar : exemplareImprumutCurent)
            service.updateExemplar(exemplar, service.findLastImprumutId());
        //update exemplare disponibile
        exemplarsObservableList.setAll(service.findAllExemplars());
        this.tableViewExemplars.setItems(exemplarsObservableList);



    }

    public void handleButtonReviews(){
        try{

            Exemplar exemplar = tableViewExemplars.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReviewsWindow.fxml"));
            Parent root = loader.load();
            ReviewsController reviewsController = loader.getController();
            reviewsController.setCurrentExemplar(exemplar.getId());
            reviewsController.setService(service);
            reviewsController.setPrimaryStage(primaryStage);
            reviewsController.setCurrentUserId(currentUserId);


            Scene reviewsScene = new Scene(root);
            primaryStage.setTitle("Reviews");
            primaryStage.setScene(reviewsScene);
            primaryStage.show();}
        catch (Exception exception){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error" + exception);
            alert.showAndWait();
        }
    }
}
