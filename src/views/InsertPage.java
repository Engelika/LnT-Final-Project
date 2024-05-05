package views;

import controller.MenuController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Menu;


public class InsertPage {

	private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label kodeMenuLabel, namaMenuLabel, hargaMenuLabel, stokMenuLabel;
    private TextField kodeMenuField, namaMenuField, hargaMenuField, stokMenuField;
    private Button insertBtn, backBtn;
	
	public InsertPage(Stage stage) {
	     init();
	     setLayout();
	     setEventHandlers();
	     this.stage = stage;
	     this.stage.setTitle("Insert Page");
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
        namaMenuLabel = new Label("Nama Menu:");
        hargaMenuLabel = new Label("Harga Menu:");
        stokMenuLabel = new Label("Stok Menu:");

        kodeMenuField = new TextField();
        namaMenuField = new TextField();
        hargaMenuField = new TextField();
        stokMenuField = new TextField();

        insertBtn = new Button("Insert");
        backBtn = new Button("Back");
    }

    private void setLayout() {
        formGrid.add(kodeMenuLabel, 0, 0);
        formGrid.add(kodeMenuField, 1, 0);
        formGrid.add(namaMenuLabel, 0, 1);
        formGrid.add(namaMenuField, 1, 1);
        formGrid.add(hargaMenuLabel, 0, 2);
        formGrid.add(hargaMenuField, 1, 2);
        formGrid.add(stokMenuLabel, 0, 3);
        formGrid.add(stokMenuField, 1, 3);
        formGrid.add(backBtn, 0, 4);
        formGrid.add(insertBtn, 1, 4);

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
        
    	insertBtn.setOnAction(event -> {
            String kodeMenu = kodeMenuField.getText();
            String namaMenu = namaMenuField.getText();
            String hargaMenu = hargaMenuField.getText();
            String stokMenu = stokMenuField.getText();

            if (kodeMenu.isEmpty() || namaMenu.isEmpty() || hargaMenu.isEmpty() || stokMenu.isEmpty()) {
                System.out.println("Please fill in all fields.");
                return;
            }
            if (!isValidKodeMenu(kodeMenu)) {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Fail to insert menu");
	            alert.setHeaderText(null);
	            alert.setContentText("Wrong Kode Menu Format (PD-XXX)");
	            alert.showAndWait();
	            return;
        	}
            
            Menu newMenu = new Menu(kodeMenu, namaMenu, Integer.parseInt(hargaMenu), Integer.parseInt(stokMenu));
            boolean inserted = MenuController.insertMenu(newMenu);
            
            if (inserted) {
            	HomePage homepage = new HomePage(stage); 
	            Scene homepageScene = homepage.getScene(); 
	            stage.setScene(homepageScene);
	            stage.show();
            } else {
            	System.out.println("Fail to insert menu");
            	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Fail to insert menu");
	            alert.setHeaderText(null);
	            alert.setContentText("Something wrong happens.");
	            alert.showAndWait();
            }
        });
    }
    
    public static boolean isValidKodeMenu(String kodeMenu) {
        if (kodeMenu.length() != 6) {
            return false;
        }
        
        if (!kodeMenu.startsWith("PD-")) {
            return false;
        }
        for (int i = 3; i < kodeMenu.length(); i++) {
            if (!Character.isDigit(kodeMenu.charAt(i))) {
                return false;
            }
        }

        return true;
    }
    
	public Scene getScene() {
		return scene;
	}

}
    