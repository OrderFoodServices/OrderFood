//Aunchalee
package ku.kps.cs.rest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import ku.kps.cs.ws.model.Order;
import ku.kps.cs.ws.model.OrderDetail;

public class CustomerDB {

    private static Logger log = Logger.getLogger("InfoLogging");

    public static Connection getDBConnection() throws ClassNotFoundException, SQLDataException, SQLException {
        String dbUrl = "jdbc:mysql://192.168.43.186/order_food";
        String dbClass = "com.mysql.jdbc.Driver";
        String userName = "root", password = "PASSWORD";
        Class.forName(dbClass);
        Connection con = DriverManager.getConnection(dbUrl, userName, password);
        return con;
    }

    public static int insertOrder(Order order) {
        int res = 0;
        try {
            int orderId = order.getOrderId();
            Timestamp orderDate = order.getOrderDate();
            String tableId = order.getTableId();
            String statusId = order.getStatusId();
            float totalPrice = order.getTotalPrice();

            String query = "INSERT INTO order_food.order(OrderId,OrderDate,TableId,StatusId,TotalPrice) VALUES(?,?,?,?,?)";
            Connection conn = getDBConnection();
            PreparedStatement prepareStmt = conn.prepareStatement(query);
            prepareStmt.setInt(1, orderId);
            System.out.println(orderDate);
            prepareStmt.setTimestamp(2, orderDate);
            prepareStmt.setString(3, tableId);
            prepareStmt.setString(4, statusId);
            prepareStmt.setFloat(5, totalPrice);
            res = prepareStmt.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;

    }

    public static int insertOrderDetail(OrderDetail orderDetail) {
        int res = 0;
        try {
            int orderId = orderDetail.getOrderId();
            String manuId = orderDetail.getMenuId();
            int num = orderDetail.getNum();
            String query = "INSERT INTO order_food.order_detail(OrderId,MenuId,num) VALUES(?,?,?)";
            Connection conn = getDBConnection();
            PreparedStatement prepareStmt = conn.prepareStatement(query);
            prepareStmt.setInt(1, orderId);
            prepareStmt.setString(2, manuId);
            prepareStmt.setInt(3, num);
            res = prepareStmt.executeUpdate();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;

    }

    public static int getMaxOrderId() {
        int maxId = -1;
        try {
            Connection con = getDBConnection();
            Statement stmt = con.createStatement();
            ResultSet idMax = stmt.executeQuery("select max(OrderId) from order_food.order");
            while (idMax.next()) {
                maxId = idMax.getInt(1);
            }
            System.out.println(maxId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return maxId;
        }

    }

    public static List<OrderDetail> searchOrderDetailByOrderId(int orderId) {
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        String query = "Select order_detail.OrderId,order_detail.num,menu.name,menu.price,order_detail.MenuId\n"
                + " FROM order_detail INNER JOIN menu where order_detail.MenuId=\n"
                + " menu.MenuId and order_detail.OrderId='" + orderId + "'";
        try {

            Connection con = getDBConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(rs.getInt("OrderId"));
                orderDetail.setMenuId(rs.getString("MenuId"));
                orderDetail.setMenuName(rs.getString("name"));
                orderDetail.setNum(rs.getInt("num"));
                orderDetail.setPrice(rs.getFloat("price"));
                orderDetailList.add(orderDetail);

            }

            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return orderDetailList;
        }
    }

    public List<Map<String, Object>> searchOrderDetailByOrderId2(int orderId) {

        List<Map<String, Object>> list = null;

        String query = "Select * FROM order_detail where OrderId='" + orderId + "'";
        try {

            Connection con = getDBConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //   list = rs.list();

            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return list;
        }

    }

}
