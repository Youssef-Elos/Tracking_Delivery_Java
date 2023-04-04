package ma.livreurtracking.fstt.livreurtrackingv1.model.store;

import java.time.LocalDate;

public class Order {
    private final String liv_name;
    //create class order witth attributes getter and setter and constructor
    private int id;
    private String date_debut;

    private String date_fin;
    private String status;
    private int quantity;
    private double price;
    private String address;
    private String type;
    private String username;
    private String productname;

    private float distance;



    public Order(int id, String status, double price, String address, String type, LocalDate date_debut , LocalDate date_fin , float distance, String liv_name) {
        this.id = id;
        this.date_debut = String.valueOf(date_debut);
        this.date_fin = String.valueOf(date_fin);
        this.status = status;
        this.price = price;
        this.address = address;
        this.type = type;
        this.distance = distance;
        this.liv_name = liv_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatedebut() {
        return date_debut;
    }
    public void setDateDebut(String date) {
        this.date_debut = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductName() {
        return productname;
    }

    public void setProductName(String productname) {
        this.productname = productname;
    }

    public void setDateFin(String date) {
        this.date_fin = date_fin;
    }
    public String getDateFin() {
        return date_fin;
    }

    public String getLivreurName() {
        return liv_name;
    }
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
    private LocalDate startDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
