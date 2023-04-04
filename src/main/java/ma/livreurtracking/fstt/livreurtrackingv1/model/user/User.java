package ma.livreurtracking.fstt.livreurtrackingv1.model.user;

public class User {

    private int id;
    private String username;
    private String password;
    private String address;

    private String phone;
    private String role;

    public User() {
    }

    public User( String username, String password, String role , String phone, String address) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }




}
