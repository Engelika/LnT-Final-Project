package views;

import java.util.List;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Menu;

public class ViewPage {

	 // declare attributes
    protected Stage stage;
    protected Scene scene;

    protected BorderPane rootNode;
    protected VBox vbox;
    protected HBox buttonHBox;
    protected Button insertBtn, updateBtn, deleteBtn;
    protected TableView<Menu> tableView;
    protected ObservableList<Menu> menuList;

    protected void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 600, 400);
       
        List<Menu> allMenu = MenuController.getAllMenu();
        menuList = FXCollections.observableArrayList(allMenu);
        
        tableView = new TableView<>();
        
        TableColumn<Menu, String> kodeMenuColumn = new TableColumn<>("Kode Menu");
        kodeMenuColumn.setCellValueFactory(cellData -> cellData.getValue().kodeMenuProperty());
        
        TableColumn<Menu, String> namaMenuColumn = new TableColumn<>("Nama Menu");
        namaMenuColumn.setCellValueFactory(cellData -> cellData.getValue().namaMenuProperty());
        
        TableColumn<Menu, String> hargaMenuColumn = new TableColumn<>("Harga Menu");
        hargaMenuColumn.setCellValueFactory(cellData -> cellData.getValue().hargaMenuProperty());
        
        TableColumn<Menu, String> stokMenuColumn = new TableColumn<>("Stok Menu");
        stokMenuColumn.setCellValueFactory(cellData -> cellData.getValue().stokMenuProperty());
        
        tableView.getColumns().addAll(kodeMenuColumn, namaMenuColumn, hargaMenuColumn, stokMenuColumn);
        tableView.setItems(menuList);
       
        insertBtn = new Button("Insert Menu Baru");
        insertBtn.setStyle("-fx-background-color: lightblue; -fx-border-color: white; "
                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
        
        updateBtn = new Button("Update Menu");
        updateBtn.setStyle("-fx-background-color: lightgreen; -fx-border-color: white; "
        		+ "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
        
        deleteBtn = new Button("Delete Menu");
        deleteBtn.setStyle("-fx-background-color: red; -fx-border-color: white; "
                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
    };
        
    private void setEventHandlers() {
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
        vbox = new VBox(8);
        vbox.getChildren().addAll(tableView);
        
        buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(insertBtn, updateBtn, deleteBtn);

        rootNode.setCenter(vbox);
        rootNode.setBottom(buttonHBox);
    }
	
	public ViewPage(Stage stage) {
		init();
        setLayout();
        setEventHandlers();
        this.stage = stage;
        this.stage.setTitle("View Page");
	}

	public Scene getScene() {
		return scene;
	}
	
}
 