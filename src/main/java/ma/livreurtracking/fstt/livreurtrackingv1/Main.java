package ma.livreurtracking.fstt.livreurtrackingv1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ma.livreurtracking.fstt.livreurtrackingv1.dao.user.UserDao;
import ma.livreurtracking.fstt.livreurtrackingv1.dao.user.UserDaoImplementation;
import ma.livreurtracking.fstt.livreurtrackingv1.model.user.Role;
import ma.livreurtracking.fstt.livreurtrackingv1.model.user.User;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 520);
        /*stage.initStyle(StageStyle.TRANSPARENT);*/
        stage.setTitle("FooDelivery");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args)  {

        launch();
    }
}