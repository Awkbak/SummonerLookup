/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icanstilldothis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Awkbak
 */
public class ICanStillDoThis extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ChickenAlfredo.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.setMinHeight(320);
        stage.setMaxHeight(320);
        stage.setMinWidth(740);
        stage.setMaxWidth(740);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
