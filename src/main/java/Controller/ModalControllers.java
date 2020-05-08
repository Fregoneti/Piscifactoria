package Controller;

import javafx.stage.Stage;

public abstract class ModalControllers extends Controllers{
    Controllers parentController;
    Stage stage;
    
    public Controllers getParentController() {
        return parentController;
    }

    public void setParentController(Controllers parentController) {
        this.parentController = (Controllers) parentController;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    abstract public void setParams(Object p);
    
}
