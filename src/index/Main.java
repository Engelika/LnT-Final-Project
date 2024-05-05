package index;

import controller.MenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.HomePage;

public class Main extends Application {

	public DatabaseConnection db = new DatabaseConnection();
	public MenuController menuController = new MenuController();
	
	public Main() {
		// TODO Auto-generated constructor stub
		db.migrateTable();
		MenuController.createDefaultMenu();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		HomePage homePage = new HomePage(stage);
		stage.setScene(homePage.getScene());
		stage.show();
	}

}
