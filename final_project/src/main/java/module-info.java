module com.test.plantscollection {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.test.plantscollection to javafx.fxml;
    exports com.test.plantscollection;
}