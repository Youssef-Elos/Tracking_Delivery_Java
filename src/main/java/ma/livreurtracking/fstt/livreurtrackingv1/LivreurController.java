package ma.livreurtracking.fstt.livreurtrackingv1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.livreurtracking.fstt.livreurtrackingv1.dao.conn.DatabaseConnection;
import ma.livreurtracking.fstt.livreurtrackingv1.model.Product;
import org.w3c.dom.events.MouseEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LivreurController {
    static Connection conn = DatabaseConnection.getConnection();
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;
    @FXML
    private TableColumn<User, String> addressColumn;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;
    @FXML
    private ObservableList<User> userListe;



    public LivreurController() {
    }

    // Method to initialize the controller
    @FXML
    public void initialize() {
        //connecte to database
        userListe = FXCollections.observableArrayList();
        userTable.setItems(userListe);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql:// localhost:3306/glovo", "root", "")) {
            // Create a statement

            // Execute a statement
            String query = "SELECT * FROM usr WHERE role = 'Livreur'";
            //create statement object and execute query to retrieve resultset
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                // Convert the ResultSet into a JavaFX ObservableList of ma.livreurtracking.fstt.livreurtrackingv1.model.Product objects
                ObservableList<User> userListe = FXCollections.observableArrayList();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    userListe.add(new User(id, username, phone, address));
                }
                // Set the data source of the TableView to the ObservableList of ma.livreurtracking.fstt.livreurtrackingv1.model.User objects
                userTable.setItems(userListe);
                // set the cell value factory to the PropertyValueFactory for each column
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
                phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
                addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //clear feilds methode
    @FXML
    private void clearFields() {
        nameTextField.clear();
        phoneTextField.clear();
        addressTextField.clear();

    }
    private List<User> getUserFromResultset(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String phone = rs.getString("phone");
            String address = rs.getString("address");

            User user = new User(id, username, phone, address);
            users.add(user);
        }
        return users;
    }

    public Integer getrandomid(){
        Random rand = new Random();
        int id = rand.nextInt(1000);
        return id;
    }
    @FXML
    private void handleAddButton(ActionEvent event){
        Integer id = getrandomid();
        String username = nameTextField.getText();
        String phone = phoneTextField.getText();
        String address = addressTextField.getText();
        String password = passwordField.getText();
        try {
            String query = "INSERT INTO usr (id, username, password, role, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, "Livreur");
            stmt.setString(5, phone);
            stmt.setString(6, address); // assuming 'Livreur' is the correct value for role
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                String selectQuery = "SELECT * FROM usr WHERE role = 'Livreur'";
                ResultSet rs = stmt.executeQuery(selectQuery);
                userListe.setAll(getUserFromResultset(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initialize();
    }
    @FXML
    private void handleDeleteButton(ActionEvent event){
        User user = userTable.getSelectionModel().getSelectedItem();
        if (user != null) {
            try {
                String query = "DELETE FROM usr WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, user.getId());
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 1) {
                    String selectQuery = "SELECT * FROM usr WHERE role = 'Livreur'";
                    ResultSet rs = stmt.executeQuery(selectQuery);
                    userListe.setAll(getUserFromResultset(rs));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        initialize();
    }
    @FXML
    private void haandleUpdateButton(ActionEvent event){
        User SelectedUser = userTable.getSelectionModel().getSelectedItem();
        if (SelectedUser != null){
            String username = nameTextField.getText();
            String phone = phoneTextField.getText();
            String address = addressTextField.getText();

            try {
                String query = "UPDATE usr SET username = ?, phone = ?, address = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, phone);
                stmt.setString(3, address);
                stmt.setInt(4, SelectedUser.getId());
                stmt.executeUpdate();

                //update the selected product in the table
                SelectedUser.setUsername(username);
                SelectedUser.setPhone(phone);
                SelectedUser.setAddress(address);
                userTable.refresh();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        initialize();
    }



    // Method to close the database connection
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
    @FXML
    private void userOnclick(){
        userTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Check if single click
                User selectedUser = userTable.getSelectionModel().getSelectedItem();
                if (selectedUser != null) {
                    // Fill the text fields with the values from the selected product
                    nameTextField.setText(selectedUser.getUsername());
                    phoneTextField.setText(selectedUser.getPhone());
                    addressTextField.setText(selectedUser.getAddress());
                }
            }
        });
    }
    @FXML
    private void clearFieldsOnclick(){
        nameTextField.clear();
        phoneTextField.clear();
        addressTextField.clear();
    }
    // User class with properties for each column
    public static class User {
        private final int id;
        private final StringProperty username;
        private final StringProperty phone;
        private final StringProperty address;

        public User(int id, String username, String phone, String address) {
            this.id = id;
            this.username = new SimpleStringProperty(username);
            this.phone = new SimpleStringProperty(phone);
            this.address = new SimpleStringProperty(address);
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username.get();
        }

        public String getPhone() {
            return phone.get();
        }

        public String getAddress() {
            return address.get();
        }

        public StringProperty nameProperty() {
            return username;
        }

        public StringProperty phoneProperty() {
            return phone;
        }

        public StringProperty addressProperty() {
            return address;
        }

        public void setUsername(String username) {
            this.username.set(username);
        }

        public void setPhone(String phone) {
            this.phone.set(phone);
        }

        public void setAddress(String address) {
            this.address.set(address);
        }
    }
}
