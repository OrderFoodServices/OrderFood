//Aunchalee
package ku.kps.cs.rest.db;

import com.mysql.jdbc.Driver;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import ku.kps.cs.ws.model.Menu;
import ku.kps.cs.ws.model.Order;
import ku.kps.cs.ws.model.OrderDetail;
import ku.kps.cs.ws.model.Table;

public class CustomerDB {

    private static Logger log = Logger.getLogger("InfoLogging");

    public static Connection getDBConnection() throws NamingException, SQLException {
//        String dbUrl = "jdbc:mysql://192.168.43.186/order_food";
//        String dbClass = "com.mysql.jdbc.Driver";
//        String userName = "root", password = "PASSWORD";
//        Class.forName(dbClass);
//        Connection con = DriverManager.getConnection(dbUrl, userName, password);
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jdbc/MyServices");
        Connection con = ds.getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return orderDetailList;
        }
    }

    public static int deleteOrder(int orderId) {
        int res = 0;
        try {
            String query = "DELETE order_food.order , order_detail  FROM order_food.order  INNER JOIN order_detail  \n"
                    + "WHERE order_food.order.orderId= order_detail. orderId and order_food.order.orderId ='" + orderId + "'";
            Connection conn = getDBConnection();
            PreparedStatement prepareStmt = conn.prepareStatement(query);
            res = prepareStmt.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;

    }

    public static List<Menu> listMenuAll() {
        List<Menu> menuList = new ArrayList<Menu>();
        String query = "SELECT * FROM menu";
        try {
            Connection con = getDBConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMenuId(rs.getString("MenuId"));
                menu.setMeNuName(rs.getString("name"));
                menu.setPrice(rs.getFloat("price"));
                menu.setImg(rs.getString("img"));
                menuList.add(menu);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return menuList;
        }

    }

    public static List<Table> listTableAll() {
        List<Table> tableList = new ArrayList<Table>();
        String query = "SELECT * FROM table_number";
        try {
            Connection con = getDBConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Table table = new Table();
                table.setTableId(rs.getString("TableId"));
                table.setTableName(rs.getString("name"));
                tableList.add(table);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return tableList;
        }

    }

    public static List<Order> searchOrderBytableId(String tableId) {
        log.info("init methodDB");
        List<Order> orderList = new ArrayList<Order>();
        String query = "SELECT order_food.order.OrderId,\n"
                + "order_food.order.OrderDate,order_food.order.TableId,order_food.order.TotalPrice\n"
                + ",order_food.status.StatusName FROM order_food.`order`\n"
                + "INNER JOIN status ON order_food.order.StatusId =status.StatusId \n"
                + "and(order_food.order.StatusId='1' or '2' or '4') and TableId='" + tableId + "'";
        try {

            Connection con = getDBConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                order.setOrderDate(rs.getTimestamp("OrderDate"));
                order.setStatusName(rs.getString("StatusName"));
                order.setTableId(rs.getString("TableId"));
                order.setTotalPrice(rs.getFloat("TotalPrice"));
                orderList.add(order);

            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return orderList;
        }
    }

    public static List<OrderDetail> searchOrderByOrderIdAndMunuId(int orderId, String menuId) {

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        String query = "Select order_detail.OrderId,order_detail.num,menu.name,menu.price,order_detail.MenuId\n"
                + "FROM order_detail INNER JOIN menu where order_detail.MenuId=\n"
                + "menu.MenuId and order_detail.OrderId='" + orderId + "' and order_detail.MenuId='" + menuId + "'";
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return orderDetailList;
        }
    }

    public static int updateOrderDetail(int orderId, String menuId, int num) {
        int res = 0;
        try {
            String query = "UPDATE order_food.order_detail set order_food.order_detail.num ='" + num + "' \n"
                    + "where order_detail.OrderId='" + orderId + "' and order_detail.MenuId='" + menuId + "';";
            Connection conn = getDBConnection();
            PreparedStatement prepareStmt = conn.prepareStatement(query);
            res = prepareStmt.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

}
