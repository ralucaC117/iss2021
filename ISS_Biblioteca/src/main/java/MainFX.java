import controller.SignInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import repository.*;
import service.Service;

import java.io.IOException;

public class MainFX extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInWindow.fxml"));
            Parent root=loader.load();
            SignInController signInController = loader.getController();
            Service service = getService();
            signInController.setService(service);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Reservations System");
            signInController.setPrimaryStage(primaryStage);
            primaryStage.show();

        } catch (IOException exception) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error while starting app " + exception);
            alert.showAndWait();
        }
    }

    static Service getService() {
        SessionFactory sessionFactory = JdbcUtils.getSessionFactory();
        UsersRepository usersRepository = new UsersRepository(sessionFactory);
        OperatorsRepository operatorsRepository = new OperatorsRepository(sessionFactory);
        ExemplarsRepository exemplarsRepository = new ExemplarsRepository(sessionFactory);
        ReviewsRepository reviewsRepository = new ReviewsRepository();
        Service service = new Service(usersRepository, operatorsRepository, reviewsRepository, exemplarsRepository);
        return service;

    }
}
