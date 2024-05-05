/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import Main.DoUong;
import Main.ConnectMenu;
import Main.HistoryPurchase;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */

public class ConnectAdmin {
   // public HashMap<Integer,Integer> khoTien = new HashMap<>();
    private Connection conn;
    private static String connectionUrl = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=Menu;"
            + "user=sa;password=12345;"
            + "encrypt = false;";
    
    public ConnectAdmin(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionUrl);
        }catch (Exception e) {
            e.printStackTrace();

        }
        
    }
     public  HashMap<Integer,Integer> inputKhoTien() {
         HashMap<Integer,Integer> khoTien = new HashMap<>();
        try {Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM KhoTien"); 

            while (resultSet.next()) {
                int denomination = resultSet.getInt("Denomination");
                int quantity = resultSet.getInt("Quantity");
                khoTien.put(denomination, quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khoTien;
    }
      public boolean updateDenominationHas(int denomination){
          HashMap<Integer,Integer> khoTien = inputKhoTien();
          String sql = "UPDATE KhoTien SET Quantity = Quantity + 1 WHERE Denomination = ?"; 
           try { 
             PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, denomination);
            return ps.executeUpdate() > 0;
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
           return false;
}
     
     
     
     
     
     
    public boolean updateProductAdmin(int id, String name, int price, int quantity){
        String sql = "UPDATE DoUong SET nameD = ?, price = ?, quantity = ? WHERE id = ?";
     try{
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, price);
        statement.setInt(3, quantity);
        statement.setInt(4, id);
        return statement.executeUpdate() > 0;
  
    } catch (SQLException ex) {
        ex.printStackTrace();
    }  
    return false;
    }
    public static void main(String[] args) {
        ConnectAdmin cnn= new ConnectAdmin();
      
    }
}
