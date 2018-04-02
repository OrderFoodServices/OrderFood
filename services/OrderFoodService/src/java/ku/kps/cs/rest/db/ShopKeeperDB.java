package ku.kps.cs.rest.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ku.kps.cs.ws.model.Order;
import ku.kps.cs.ws.model.OrderDetail;

public class ShopKeeperDB {

    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String dbUrl = "jdbc:mysql://192.168.88.247/test";
        String dbClass = "com.mysql.jdbc.Driver";
        String userName = "root", password = "password";
        Class.forName(dbClass);
        Connection con = DriverManager.getConnection(dbUrl, userName, password);

        return con;
    }

    public static List<Order> searchOrder(String orderDate, String statusID) {
        List<Order> orderList = new ArrayList<Order>();
        String query = "Select * FROM order where orderID=" + orderDate + "'" + "statusID=" + statusID + "'";
        try {
            Connection con = getDBConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getString("orderID"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setTableId(rs.getString("tableID"));
                order.setStatusId(rs.getString("statusID"));

            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            return orderList;
        }
    }

    public static int insertOrder(OrderDetail orD) {
        int res = 0;
        try {
            String orId = orD.getOrderId();
            String menuId = orD.getMenuId();
            int num = orD.getNum();
            float totPrice = orD.getTotalPrice();
            int amount = orD.getAmount();
            int price = orD.getPrice();

            String query = "INSERT INTO orderdetails(orderID,menuID,num,totalPrice,amount,price)"
                    + "            VALUES(?,?,?,?,?,?)";
            Connection conn = getDBConnection();
            PreparedStatement prepareStmt = conn.prepareStatement(query);
            prepareStmt.setString(1, orId);
            prepareStmt.setString(2, menuId);
            prepareStmt.setInt(3, num);
            prepareStmt.setFloat(4, totPrice);
            prepareStmt.setInt(5, amount);
            prepareStmt.setInt(6, num);
            res = prepareStmt.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static List<OrderDetail> getOrderDetail(String orderID) {
        List<OrderDetail> orDList = new ArrayList<OrderDetail>();
        String query = "Select * FROM orderdetails where Order-ID ='"
                + orderID + "'";
        try {
            Connection con = getDBConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                OrderDetail orD = new OrderDetail();
                orD.setOrderId(rs.getString("orderID"));
                orD.setMenuId(rs.getString("menuID"));
                orD.setNum(rs.getInt("num"));
                orD.setTotalPrice(rs.getFloat("totalPrice"));
                orD.setAmount(rs.getInt("amount"));
                orD.setPrice(rs.getInt("price"));
                orDList.add(orD);

            }
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return orDList;
        }
    }

}
