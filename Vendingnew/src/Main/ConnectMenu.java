/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ConnectMenu {

    private Connection conn;
    private static String connectionUrl = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=Menu;"
            + "user=sa;password=12345;"
            + "encrypt = false;";
    
    public ConnectMenu(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionUrl);
        }catch (Exception e) {
            e.printStackTrace();

        }
            
    }
  
    public ArrayList<DoUong> getlistMenu(){
         ArrayList<DoUong> list = new ArrayList<>();
         String sql = "SELECT * FROM DoUong";
          try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DoUong s = new DoUong();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("nameD"));
                s.setPrice(rs.getInt("price"));
                s.setQuantity(rs.getInt("quantity"));
                list.add(s);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean  updateQuantity(DoUong du){
        String sql = "UPDATE DoUong SET quantity = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,du.getQuantity());
            ps.setInt(2, du.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; 
    }
    public boolean upDateDoanhThu(double mountMoneyInSert){
        String sql = "UPDATE DoanhThu SET total = total + ? ";
        DoanhThu t = new DoanhThu();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            t.setTotal(t.getTotal() + mountMoneyInSert);
            ps.setDouble(1,t.getTotal());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; 
    }
    
    public boolean addHistoryInSertMoney(double mount, String time){
        DetailMoneyInsert detail = new DetailMoneyInsert(mount,time);
         String sql = "INSERT INTO detailMoneyInsert(mount, time )" + "Values(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, detail.getDetail());
            ps.setString(2, detail.getTime());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addHistoryInSertPurchase(int id, String nameDU, int price, String time){
            HistoryPurchase hp = new HistoryPurchase(id,nameDU, price, time);
         String sql = "INSERT INTO LichSuMua (nameD, price, thoiGianMua) " +
                         "SELECT nameD, price, ? AS thoiGianMua " +
                         "FROM DoUong " +
                         "WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,  hp.getTime());
            ps.setInt(2, hp.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   
public static void main(String[] args) {
       ConnectMenu cnn= new ConnectMenu();
      
    }
}
