package ma.livreurtracking.fstt.livreurtrackingv1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.livreurtracking.fstt.livreurtrackingv1.dao.conn.DatabaseConnection;
import ma.livreurtracking.fstt.livreurtrackingv1.model.store.Order;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.time.LocalDate;

public class OrderController {
    static Connection con = DatabaseConnection.getConnection();
    @FXML
    private TableColumn<Order, Integer> idColumn;
    @FXML
    private TableColumn<Order, String> statusColumn;
    @FXML
    private TableColumn<Order, String> livreurColumn;
    @FXML
    private TableColumn<Order, Float> distanceColumn;
    @FXML
    private TableColumn<Order, String> addressColumn;

    @FXML
    private TableColumn<Order, String> dateColumn;
    @FXML
    private ObservableList<Order> orderList;
    @FXML
    private TableView<Order> orderTable;

    public OrderController() {

    }


   /* public void initialize() {
        // Connect to the database
        orderList = FXCollections.observableArrayList();
        orderTable.setItems(orderList);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/glovo", "root", "")) {

            // Create a SQL query to retrieve all orders from the database and order by id
            String query = "SELECT ordr.id, ordr.status, ordr.price, ordr.address, ordr.type, ordr.date_debut, ordr.date_fin, ordr.distance, usr.username AS livreur_name"+
            "FROM ordr"+
            "JOIN usr ON ordr.liv_id = usr.id;";

            // Create a Statement object and execute the query to retrieve a ResultSet
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                // Convert the ResultSet into a JavaFX ObservableList of Order objects
                ObservableList<Order> orders = FXCollections.observableArrayList();
                while (rs.next()) {
                    int id = rs.getInt("order_id");
                    String status = rs.getString("status");

                    String address = rs.getString("address");
                    String type = rs.getString("type");
                    double price = rs.getDouble("price");
                    LocalDate startDate = rs.getDate("date_debut").toLocalDate();
                    LocalDate endDate = rs.getDate("date_fin").toLocalDate();
                    int distance = rs.getInt("distance");
                    String livreurName = rs.getString("livreur_name");
                    orders.add(new Order(id, status, price, address, type, startDate, endDate, distance, livreurName));
                }

                // Set the data source of the TableView to the ObservableList of Order objects
                orderTable.setItems(orderList);

                // Set the cell value factories for each column to extract the appropriate fields from each Order object
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
                addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
                livreurColumn.setCellValueFactory(new PropertyValueFactory<>("livreurName"));
                distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
                dateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    @FXML
    public void initialize() {
        // Connect to the database
        orderList = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/glovo", "root", "")) {

            // Create a SQL query to retrieve all orders from the database and order by id
            String query = "SELECT ordr.id, ordr.status, ordr.price, ordr.address, ordr.type, ordr.date_debut, ordr.date_fin, ordr.distance, usr.username AS livreur_name " +
                    "FROM ordr " +
                    "JOIN usr ON ordr.liv_id = usr.id " +
                    "ORDER BY ordr.id";

            // Create a Statement object and execute the query to retrieve a ResultSet
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                // Convert the ResultSet into a JavaFX ObservableList of Order objects
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String status = rs.getString("status");
                    String address = rs.getString("address");
                    String type = rs.getString("type");
                    double price = rs.getDouble("price");
                    LocalDate startDate = rs.getDate("date_debut").toLocalDate();
                    LocalDate endDate = rs.getDate("date_fin").toLocalDate();
                    int distance = rs.getInt("distance");
                    String livreurName = rs.getString("livreur_name");
                    orderList.add(new Order(id, status, price, address, type, startDate, endDate, distance, livreurName));
                }

                // Set the data source of the TableView to the ObservableList of Order objects
                orderTable.setItems(orderList);

                // Set the cell value factories for each column to extract the appropriate fields from each Order object
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
                addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
                livreurColumn.setCellValueFactory(new PropertyValueFactory<>("livreurName"));
                distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
                dateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public ObservableList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ObservableList<Order> orderList) {
        this.orderList = orderList;
    }

    public TableView<Order> getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(TableView<Order> orderTable) {
        this.orderTable = orderTable;
    }

}
