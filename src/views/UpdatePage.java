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
import model.Menu;

import java.util.List;

public class UpdatePage {

	private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label kodeMenuLabel, namaMenuLabel, hargaMenuLabel, stokMenuLabel;
    private ComboBox<String> kodeMenuComboBox;
    private TextField namaMenuField, hargaMenuField, stokMenuField;
    private Button updateBtn, backBtn;
	
	public UpdatePage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Update Page");
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
        hargaMenuLabel = new Label("Harga Menu:");
        stokMenuLabel = new Label("Stok Menu:");

        kodeMenuComboBox = new ComboBox<>();
        namaMenuField = new TextField();
        hargaMenuField = new TextField();
        stokMenuField = new TextField();

        updateBtn = new Button("Update");
        backBtn = new Button("Back");

        // Fetch all kodeMenu from the MenuController and populate the ComboBox
        List<String> kodeMenus = MenuController.getKodeMenu();
        kodeMenuComboBox.setItems(FXCollections.observableArrayList(kodeMenus));
    }

    private void setLayout() {
        formGrid.add(kodeMenuLabel, 0, 0);
        formGrid.add(kodeMenuComboBox, 1, 0);
        formGrid.add(namaMenuLabel, 0, 1);
        formGrid.add(namaMenuField, 1, 1);
        formGrid.add(hargaMenuLabel, 0, 2);
        formGrid.add(hargaMenuField, 1, 2);
        formGrid.add(stokMenuLabel, 0, 3);
        formGrid.add(stokMenuField, 1, 3);
        formGrid.add(backBtn, 0, 4);
        formGrid.add(updateBtn, 1, 4);

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
    	 
        updateBtn.setOnAction(event -> {
            String kodeMenu = kodeMenuComboBox.getValue();
            String hargaMenu = hargaMenuField.getText();
            String stokMenu = stokMenuField.getText();

            boolean updated = MenuController.updateMenu(kodeMenu, Integer.parseInt(hargaMenu), Integer.parseInt(stokMenu));

            if (updated) {
            	HomePage homepage = new HomePage(stage); 
	            Scene homepageScene = homepage.getScene(); 
	            stage.setScene(homepageScene);
	            stage.show();
            } else {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Update Error");
	            alert.setHeaderText(null);
	            alert.setContentText("Something wrong happens.");
	            alert.showAndWait();
            }
        });
}
    
	public Scene getScene() {
		return scene;
	}

}