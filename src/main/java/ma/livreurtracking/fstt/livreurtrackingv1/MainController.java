package ma.livreurtracking.fstt.livreurtrackingv1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.livreurtracking.fstt.livreurtrackingv1.dao.user.LoginService;
import org.controlsfx.control.action.Action;
import ma.livreurtracking.fstt.livreurtrackingv1.AdashboardController;

import java.io.IOException;


public class MainController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private LoginService loginService = new LoginService();
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

/*    public void loginButtonOnAcrion(ActionEvent e){
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println("username: " + username + "password: " + password);
        loginMessageLabel.setText("test");
    }*/
    public void handleLoginButtonAction(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println("username: " + username + "password: " + password);
        boolean loginSuccessful = loginService.verifyLogin(username, password);
        if (loginSuccessful) {
            loginMessageLabel.setText("Login successful!");
            root = FXMLLoader.load(getClass().getResource("Adashboard.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            loginMessageLabel.setText("Invalid username or password.");
        }
    }
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }



    /*private void checkLogin(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println("username: " + username + "password: " + password);
        boolean loginSuccessful = loginService.verifyLogin(username, password);

    }*/

}
