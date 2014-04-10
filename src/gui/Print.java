package gui;

import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;
import ascentricForm.AscentricForm;

import com.itextpdf.text.DocumentException;

public class Print {
	
	private ClientHolder client;
	private GridPane grid;
	private Button save;
	private Stage primaryStage;
	private Scene thisScene;

	public Print(ClientHolder client, final Stage primaryStage){
		
		this.client = client;
		this.primaryStage = primaryStage;
		save = new Button("Save Document");
		
		primaryStage.setTitle("Income Payment Instructions");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(save, 0, 1);
        
        Label tf = new Label("You have now completed filling in the document,"
        		+ "click save to produce a finished document and return to the home screen.");
        
        grid.add(tf, 0, 0);
        
        save.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
            	FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Form");
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fileChooser.getExtensionFilters().add(filter);
                File file = fileChooser.showSaveDialog(primaryStage);
                printDocument(file);
            }
        });
        
        thisScene = new Scene(grid, 900, 500);  
		
        primaryStage.setScene(thisScene);
	}
	
	protected void printDocument(File file) {
		AscentricForm af = new AscentricForm();
		try {
			af.fillIt(client, file);
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}