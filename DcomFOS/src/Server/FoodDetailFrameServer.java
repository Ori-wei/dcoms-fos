/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.CartItem;
import Client.Menu;
import FOSInterface.MenuInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class FoodDetailFrameServer extends UnicastRemoteObject implements MenuInterface {
    
    public FoodDetailFrameServer() throws RemoteException {
        super();
    }
    @Override
    public List<Menu> getFoodDetail(int foodID) throws RemoteException, SQLException {
        List<Menu> menuList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM food where FoodID = " + foodID)) {

            while (rs.next()) {
                Menu menu = new Menu(rs.getInt("FoodID"), rs.getString("FoodName"), rs.getDouble("Price"), rs.getString("Description"), rs.getString("Category"));
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(menuList);
        return menuList;
    }
    @Override
    public void viewMenu() throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Menu> getMenu() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CartItem> getCartItem(int userID) throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
