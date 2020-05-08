package Controller;

import Controller.Controllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

public class AboutController extends Controllers implements Initializable{

    @FXML
    private Hyperlink repo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    public void back(){
        if(this.app.controller.backScene!=null){
            this.app.controller.changeScene(this.app.controller.backScene);
        }
    }
    
}
