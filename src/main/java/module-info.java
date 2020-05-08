module com.mycompany.maven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.xml.bind;
    requires java.prefs;
    requires java.sql;
    requires com.h2database;

    opens Controller to javafx.fxml;
    exports com.mycompany.maven;
     exports Model;  //para que JAXB pueda acceder a Connection y Connection wrapper
    
 

    opens Utils to java.xml.bind; //Para que JAXB pueda ejecutarse en XMLUtil
    opens controller to javafx.fxml;
    opens Model to java.xml.bind; //Para que JAXB pueda ejecutarse en ConnectionWrapper
 
   


}
