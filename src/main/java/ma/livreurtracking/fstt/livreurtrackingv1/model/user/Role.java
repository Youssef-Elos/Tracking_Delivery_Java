package ma.livreurtracking.fstt.livreurtrackingv1.model.user;

public enum Role {
    ADMIN("Admin"), LIVREUR("Livreur"), CLIENT("Client");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getValue(){
        return role;
    }
}
