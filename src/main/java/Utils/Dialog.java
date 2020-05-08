package Utils;

import javafx.scene.control.Alert;


public class Dialog {
    public static void showError(String title, String header,String description){
        showDialog(Alert.AlertType.ERROR, title, header, description);
    }
    public static void showWarning(String title, String header,String description){
        showDialog(Alert.AlertType.WARNING, title, header, description);
    }
    public static void showConfirm(String title, String header,String description){
        showDialog(Alert.AlertType.CONFIRMATION, title, header, description);
    }
    public static void showDialog(Alert.AlertType type, String title, String header,String description){
        Alert alert =new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
        
    }
}
