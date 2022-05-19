module ru.nsu.fit.carfactory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.logging.log4j;


    opens ru.nsu.fit.carfactory to javafx.fxml;
    exports ru.nsu.fit.carfactory;
}