package ma.livreurtracking.fstt.livreurtrackingv1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ma.livreurtracking.fstt.livreurtrackingv1.dao.conn.DatabaseConnection;
import org.w3c.dom.events.MouseEvent;
import ma.livreurtracking.fstt.livreurtrackingv1.model.Product;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdashboardController {
    static Connection con = DatabaseConnection.getConnection();
    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    private ObservableList<Product> productList;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Product> productTable;

    @FXML
    public TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, String> descriptionColumn;

    public void switchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void initialize(){

    }

/*    public void initialize() {
        // Connect to the database
        productList = FXCollections.observableArrayList();
        productTable.setItems(productList);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql:// localhost:3306/glovo", "root", "")) {

            // Create a SQL query to retrieve all products from the database
            String query = "SELECT * FROM product";

            // Create a Statement object and execute the query to retrieve a ResultSet
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                // Convert the ResultSet into a JavaFX ObservableList of ma.livreurtracking.fstt.livreurtrackingv1.model.Product objects
                ObservableList<Product> products = FXCollections.observableArrayList();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    products.add(new Product(id, name, description, price, quantity));
                }

                // Set the data source of the TableView to the ObservableList of ma.livreurtracking.fstt.livreurtrackingv1.model.Product objects
                productTable.setItems(products);

                // Set the cell value factories for each column to extract the appropriate fields from each ma.livreurtracking.fstt.livreurtrackingv1.model.Product object
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
                quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    @FXML
    private void clearFields() {
        nameTextField.clear();
        priceTextField.clear();
        descriptionTextField.clear();
        quantityTextField.clear();
    }

    private List<Product> getProductsFromResultSet(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");

            Product product = new Product(id, name, description, price, quantity);
            products.add(product);
        }
        return products;
    }

    @FXML
    private void handleAddButton(ActionEvent event) {
        String name = nameTextField.getText();
        double price = Double.parseDouble(priceTextField.getText());
        String description = descriptionTextField.getText();
        int quantity = Integer.parseInt(quantityTextField.getText());

        try {
            String query = "INSERT INTO product (name, description, price, quantity) VALUES ('" + name + "', '" + description + "', '" + price + "', '" + quantity + "')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);

            // Fetch all products from the database and set productList to the new list
            String selectQuery = "SELECT * FROM product";
            ResultSet rs = stmt.executeQuery(selectQuery);
            productList.setAll(getProductsFromResultSet(rs));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*initialize();*/
    }


    @FXML
    private void handleDeleteButton(ActionEvent event) {
        // Get the selected product from the table
        Product product = productTable.getSelectionModel().getSelectedItem();

        if (product != null) {
            // Remove the product from the list and database
            productList.remove(product);

            try {
                String query = "DELETE FROM product WHERE id = " + product.getId();
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        /*initialize();*/
    }

    @FXML
    private void handleUpdateButton(ActionEvent event) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            String name = nameTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            String description = descriptionTextField.getText();
            int quantity = Integer.parseInt(quantityTextField.getText());

            try {
                String query = "UPDATE product SET name=?, description=?, price=?, quantity=? WHERE id=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, description);
                stmt.setDouble(3, price);
                stmt.setInt(4, quantity);
                stmt.setInt(5, selectedProduct.getId());
                stmt.executeUpdate();

                // Update the selected product in the table
                selectedProduct.setName(name);
                selectedProduct.setDescription(description);
                selectedProduct.setPrice(price);
                selectedProduct.setQuantity(quantity);
                productTable.refresh();

                System.out.println("Product updated: " + selectedProduct);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        /*initialize();*/
    }
    @FXML
    private void productOnclick() {
        productTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Check if single click
                Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    // Fill the text fields with the values from the selected product
                    nameTextField.setText(selectedProduct.getName());
                    priceTextField.setText(String.valueOf(selectedProduct.getPrice()));
                    descriptionTextField.setText(selectedProduct.getDescription());
                    quantityTextField.setText(String.valueOf(selectedProduct.getQuantity()));
                }
            }
        });
    }

    @FXML
    private void page1(ActionEvent event) {
        loadpage("Products");
    }
    @FXML
    private void page2(ActionEvent event) {
        loadpage("Orders");
    }
    @FXML
    private void page3(ActionEvent event) {
        loadpage("Livreur");
    }
    @FXML
    private void loadpage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+ ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }



}
