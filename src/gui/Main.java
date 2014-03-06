/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ascentricGui.Page1;

/**
 *
 * @author charliebrodie
 */
public class Main extends Application{
    
    private Stage primaryStage;
    private Scene firstScene;
    
    public static void main(String[] args){
        launch(args);
    }
    
    private double fieldWidth = 100d;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("PDF Filler 0.01");
        primaryStage.setMinWidth(500);
        primaryStage.initStyle(StageStyle.DECORATED);
        
        GridPane grid = new GridPane();
        Scene firstScene = new Scene(grid, 300, 300, Color.WHITE);
        
        grid.setAlignment(Pos.CENTER);
        //sets the horizontal and vertical gaps around the border of the application
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text sceneTitle = new Text("Welcome to the PDF Filler mark 0.01");
        sceneTitle.setId("sceneTitle");
        sceneTitle.setLayoutX(20);
        grid.add(sceneTitle, 0, 0, 3, 1);

        Label formType = new Label("Choose Form Type");
        grid.add(formType, 0, 3);
        
        //List of form types
        ObservableList<String> formTypes =
            FXCollections.observableArrayList(
                    "Ascentric",
                    "Standard Life",
                    "Scot Prov"
            );
        
        ComboBox<String> comboBox = 
        		new ComboBox<String>(formTypes);
        comboBox.setPrefWidth(fieldWidth);
        grid.add(comboBox, 1, 3);
        
        Button go = new Button("Go");
        go.setPrefWidth(fieldWidth);
        grid.add(go, 2, 3);
        
        setUpFormFiller(go);
               
        primaryStage.show();
        this.firstScene = firstScene;
        
        primaryStage.setScene(firstScene);
//        firstScene.getStylesheets().add(
//                Page1.class//This must be the class that the stylesheet is styling
//                .getResource("gui/Style.css").toExternalForm());
    }   
    
	public void setUpFormFiller(Button btn){
        Page1 client = new Page1();
        final Text actionTarget = new Text(); 
       btn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                Page1 client = new Page1();
                client.start(primaryStage, firstScene);
            }
        });
    }
}