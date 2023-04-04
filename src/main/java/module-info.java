module ma.livreurtracking.fstt.livreurtrackingv1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens ma.livreurtracking.fstt.livreurtrackingv1 to javafx.fxml;
    exports ma.livreurtracking.fstt.livreurtrackingv1;
    opens ma.livreurtracking.fstt.livreurtrackingv1.model to javafx.base;


    opens ma.livreurtracking.fstt.livreurtrackingv1.model.store to javafx.base;




}