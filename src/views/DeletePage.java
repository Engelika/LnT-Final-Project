package views;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class DeletePage {

	private Stage stage;
	private Scene scene;
	private BorderPane rootNode;
	private GridPane formGrid;
	private Label kodeMenuLabel, namaMenuLabel;
	private ComboBox<String> kodeMenuComboBox;
	private TextField namaMenuField;
	private Button deleteBtn, backBtn;
	
	public DeletePage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Delete Page");
        init();
        setLayout();
        setEventHandlers();
	}

	private void init() {
		rootNode = new BorderPane();
	    scene = new Scene(rootNode, 400, 200);
	        
	    formGrid = new GridPane();
	    formGrid.setAlignment(Pos.CENTER);
	    formGrid.setHgap(10);
	    formGrid.setVgap(10);
	    formGrid.setPadding(new Insets(20));

	    kodeMenuLabel = new Label("Kode Menu:");
	    namaMenuLabel = new Label ("Nama Menu:");
	    
	    namaMenuField = new TextField();

	    kodeMenuComboBox = new ComboBox<>();

	    deleteBtn = new Button("Delete");
	    backBtn = new Button("Back");
	        
	        
	    List<String> kodeMenus = MenuController.getKodeMenu();
	    kodeMenuComboBox.setItems(FXCollections.observableArrayList(kodeMenus));
    }

	    private void setLayout() {
	        formGrid.add(kodeMenuLabel, 0, 0);
	        formGrid.add(kodeMenuComboBox, 1, 0);
	        formGrid.add(namaMenuLabel, 0, 1);
	        formGrid.add(namaMenuField, 1, 1);
	        formGrid.add(backBtn, 0, 2);
	        formGrid.add(deleteBtn, 1, 2);

	        rootNode.setCenter(formGrid);
	    }

	    private void setEventHandlers() {
	    	EventHandler<MouseEvent> redirectHome = new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					HomePage newpage = new HomePage(stage); 
		            Scene newpageScene = newpage.getScene(); 
		            stage.setScene(newpageScene);
		            stage.show();
				}
	        };
	        backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectHome);
	        
	    	kodeMenuComboBox.setOnAction(event -> {
    		    String selectedKodeMenu = kodeMenuComboBox.getValue();
    		    String namaMenu = MenuController.getNamaMenu(selectedKodeMenu);
    		    namaMenuField.setText(namaMenu);
    		});
	    	
	        deleteBtn.setOnAction(event -> {
	            String kodeMenu = kodeMenuComboBox.getValue();

	            boolean deleted = MenuController.deleteMenu(kodeMenu);

	            if (deleted) {
	            	HomePage homepage = new HomePage(stage); 
		            Scene homepageScene = homepage.getScene(); 
		            stage.setScene(homepageScene);
		            stage.show();
	            } else {
	            	Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Fail to delete");
		            alert.setHeaderText(null);
		            alert.setContentText("Something wrong happens.");
		            alert.showAndWait();
	            }
	        });
    }
        
	public Scene getScene() {
		// TODO Auto-generated method stub
		return scene;
	}

}
