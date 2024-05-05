package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import index.DatabaseConnection;
import index.DatabaseSingleton;
import model.Menu;

public class MenuController {

	public static DatabaseConnection db = DatabaseSingleton.getInstance();
	
	// Seeder
	public static void createDefaultMenu() {
		boolean result1 = insertMenu(new Menu("PD-123", "Pudding Caramel", 10000, 10));
		
		if(!result1) {
			System.out.println("Fail to insert data");
		}
		else {
			System.out.println("Menu inserted!");			
		}
		
	}
	
	// get Data
	public static List<Menu> getAllMenu(){
		String query = "SELECT * FROM menu";
		List<Menu> menu = new ArrayList<>();
		
        try {
            PreparedStatement stmt = db.connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String kodeMenu = resultSet.getString("kodeMenu");
                String namaMenu = resultSet.getString("namaMenu");
                String hargaMenu = resultSet.getString("hargaMenu");
                String stokMenu = resultSet.getString("stokMenu");

                Menu menu1 = new Menu(kodeMenu, namaMenu, Integer.parseInt(hargaMenu), Integer.parseInt(stokMenu));
                menu.add(menu1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return menu;
	}
	
	
	public static String getNamaMenu(String kodeMenu){
		String query = "SELECT namaMenu FROM menu WHERE kodeMenu = ?";
		String namaMenu = "";
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            statement.setString(1,  kodeMenu);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                namaMenu = resultSet.getString("namaMenu");
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return namaMenu;
	}
	
	// Insert Data
	public static boolean insertMenu(Menu menu) {
		if(menuExist(menu.getKodeMenu())) {
			return false;
		}
		String query = "INSERT INTO menu (kodeMenu, namaMenu, hargaMenu, stokMenu)"
					+ "VALUES (?,?,?,?)";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
					stmt.setString(1,  menu.getKodeMenu());
					stmt.setString(2,  menu.getNamaMenu());
					stmt.setInt(3,  menu.getHargaMenu());
					stmt.setInt(4,  menu.getStokMenu());
					
					int rowsAffected = stmt.executeUpdate();
					return rowsAffected > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean menuExist(String kodeMenu) {
		String query = "SELECT COUNT(*) FROM menu WHERE kodeMenu = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1,  kodeMenu);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Update Data
	public static boolean updateMenu(String kodeMenu, int hargaMenu, int stokMenu) {
		if (menuExist(kodeMenu)) {
			String query = "UPDATE menu SET hargaMenu = ?, stokMenu = ? WHERE kodeMenu = ?";			
			try {
				PreparedStatement statement = db.connection.prepareStatement(query);
				statement.setInt(1, hargaMenu);
				statement.setInt(2, stokMenu);
				statement.setString(3, kodeMenu);
				
				int rowsUpdated = statement.executeUpdate();
				return rowsUpdated > 0;
			} catch (Exception e) {
				e.printStackTrace();				
			}
		}
		return false;
	}
	
	public static List<String> getKodeMenu(){
		String query = "SELECT kodeMenu FROM menu";
		List<String> kodeMenus = new ArrayList<>();
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String kodeMenu = resultSet.getString("kodeMenu");
                kodeMenus.add(kodeMenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kodeMenus;
	}
	
	
	
	// Delete Data
	public static boolean deleteMenu(String kodeMenu) {
		String query = "DELETE FROM menu WHERE kodeMenu = ?";
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            statement.setString(1, kodeMenu);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
}


