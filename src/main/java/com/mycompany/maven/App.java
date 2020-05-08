package com.mycompany.maven;

import Controller.AppController;
import Controller.Controllers;
import Controller.Scenes;
import Utils.MapEntry;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public Stage mainStage;
    public AppController controller;
    public BorderPane rootLayout;

    
    
    @Override
    public void start (Stage stage) throws IOException{
        scene=new Scene(loadFXML("primary"),640, 480);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    
    /**

    @Override
    public void start(Stage stage) throws IOException {

        MapEntry<Parent, Controllers> m = AppController.loadFXML(Scenes.ROOT.getUrl());

        mainStage = stage;
        rootLayout = (BorderPane) m.getKey();
        scene = new Scene(rootLayout, 640, 480);
        stage.setScene(scene);

        controller =  (AppController) m.getValue();
        controller.setMainApp(this);
        controller.changeScene(Scenes.PRIMARY);
        stage.show();
    }

**/
    
    
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
   
    
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }

}
