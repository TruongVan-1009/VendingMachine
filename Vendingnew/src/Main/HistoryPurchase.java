/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Dell
 */
public class HistoryPurchase extends DoUong {
    private String time;

    public HistoryPurchase( int id, String name, int price, String time ) {
        super( id,name, price);
        this.time = time;
    }

    
    public HistoryPurchase() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
