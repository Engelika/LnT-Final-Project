package views;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {
    // declare attributes
    protected Stage stage;
    protected Scene scene;

    // layout manager
    protected BorderPane rootNode;
    protected VBox vbox;
    protected HBox buttonHBox;
    
    // components
    protected Label headerText;
    protected Button insertBtn, viewBtn, updateBtn, deleteBtn;

    protected void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 600, 400);
       
        //label
		
        headerText = new Label("PT Pudding Database");
        headerText.setStyle("-fx-font-family: 'Nunito'; -fx-font-size:30px; -fx-text-fill: #003366");
        
        //button
        insertBtn = new Button("Insert Menu Baru");
        insertBtn.setStyle("-fx-background-color: lightblue; -fx-border-color: white; "
                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
        
        viewBtn = new Button("View Menu");
        viewBtn.setStyle("-fx-background-color: lightpink; -fx-border-color: white; "
        		+ "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
        
        updateBtn = new Button("Update Menu");
        updateBtn.setStyle("-fx-background-color: lightgreen; -fx-border-color: white; "
        		+ "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
        
        deleteBtn = new Button("Delete Menu");
        deleteBtn.setStyle("-fx-background-color: red; -fx-border-color: white; "
                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
        
        EventHandler<MouseEvent> redirectInsert = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				InsertPage newpage = new InsertPage(stage); 
	            Scene newpageScene = newpage.getScene(); 
	            stage.setScene(newpageScene);
	            stage.show();
			}
        };
        insertBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectInsert);
        
        EventHandler<MouseEvent> redirectView = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				ViewPage newpage = new ViewPage(stage); 
	            Scene newpageScene = newpage.getScene(); 
	            stage.setScene(newpageScene);
	            stage.show();
			}
        };
        viewBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectView);
        
        EventHandler<MouseEvent> redirectUpdate = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				UpdatePage newpage = new UpdatePage(stage); 
	            Scene newpageScene = newpage.getScene(); 
	            stage.setScene(newpageScene);
	            stage.show();
			}
        };
        updateBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectUpdate);
        
        EventHandler<MouseEvent> redirectDelete = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DeletePage newpage = new DeletePage(stage); 
	            Scene newpageScene = newpage.getScene(); 
	            stage.setScene(newpageScene);
	            stage.show();
			}
        };
        deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectDelete);
    }
    
    

    protected void setLayout() {
        vbox = new VBox(20);
        vbox.getChildren().addAll(headerText, insertBtn, viewBtn, updateBtn, deleteBtn);        
        
        vbox.setAlignment(Pos.CENTER);
        rootNode.setTop(headerText);
        BorderPane.setAlignment(headerText, Pos.CENTER);   
        rootNode.setCenter(vbox);
    }

    // scene
    public Scene getScene() {
        return this.scene;
    }

    public HomePage(Stage stage) {
        init();
        setLayout();
        this.stage = stage;
        this.stage.setTitle("Home Page");
    }
}