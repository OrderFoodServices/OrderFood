//Aunchalee.k
package ku.kps.cs.ws.rest;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ku.kps.cs.rest.db.CustomerDB;
import ku.kps.cs.ws.model.Order;
import ku.kps.cs.ws.model.OrderDetail;
import java.util.Date;
import java.util.List;
import ku.kps.cs.ws.model.Menu;
import ku.kps.cs.ws.model.Table;

@Path("/customer")
public class CustomerResource {

    private static Logger log = Logger.getLogger("InfoLogging");
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
//	System.out.println(dateFormat.format(date)); //2016/11/16 12:08:4

    Timestamp dateTimeNow = new Timestamp(date.getTime());

    @POST
    @Path("/getIdOrder")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMaxIdOrder() {
        int maxId = CustomerDB.getMaxOrderId() + 1;
        return Response.status(200).entity(maxId).build();
    }

    @POST
    @Path("/insertOrder")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertOrder(@FormParam("orderId") int orderId, @FormParam("tableId") String tableId, @FormParam("totalPrice") float totalPrice) {

        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderDate(dateTimeNow);
        order.setTableId(tableId);
        order.setStatusId("1");
        order.setTotalPrice(totalPrice);
        int res = CustomerDB.insertOrder(order);

        String result = "SUCCESS";
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Path("/insertOrderDetail")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertOrderDetail(@FormParam("orderId") int orderId, @FormParam("menuId") String menuId, @FormParam("num") int num) {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setMenuId(menuId);
        orderDetail.setNum(num);
        int res = CustomerDB.insertOrderDetail(orderDetail);

        String result = "SUCCESS";

        return Response.status(Response.Status.OK).entity(result).build();
    }
    
    @POST
    @Path("/searchOrderDetailByOrderId")
    @Produces(MediaType.TEXT_PLAIN)
    public Response searchStudentById(@FormParam("orderId") int orderId) {
        String result = "";
        List<OrderDetail> orderDetailList = CustomerDB.searchOrderDetailByOrderId(orderId);

        for (OrderDetail orderDetail : orderDetailList) {
            result += ",{\"id\":" + "\"" + orderDetail.getOrderId() + "\"" + ", "
                      + "\"menuId\":" + "\"" + orderDetail.getMenuId() + "\"" + ", "
                    + "\"name\":" + "\"" + orderDetail.getMenuName() + "\"" + ", "
                       + "\"price\":" + "\"" + orderDetail.getPrice() + "\"" + ", "
                     + "\"num\":" + "\"" + orderDetail.getNum() + "\"" + "}";
                    
        }
        return Response.status(200).entity(result).build();
    }
    
    @POST
    @Path("/deleteOrderDetail")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOrderDetail(@FormParam("orderId") int orderId,@FormParam("menuId") String menuId){
        int res =CustomerDB.deleteOrderDetail(orderId, menuId);
         String result = "SUCCESS";
         return Response.status(Response.Status.OK).entity(result).build();
        
    }
    
    @POST
    @Path("/listMenu")
    @Produces(MediaType.TEXT_PLAIN)
    public Response listMenuAll() {
        String result = "";      
        List<Menu> menuList =CustomerDB.listMenuAll();
        for (Menu menu : menuList) {
            result += ",{\"id\":" + "\"" + menu.getMenuId()+ "\"" + ", "
                      + "\"menuName\":" + "\"" + menu.getMeNuName() + "\"" + ", "
                     + "\"price\":" + "\"" + menu.getPrice()+ "\"" + "}";                  
        }
        return Response.status(200).entity(result).build();
    }
    
    @POST
    @Path("/listTableNO")
    @Produces(MediaType.TEXT_PLAIN)
    public Response listTableNOAll() {
        String result = "";      
        List<Table> tableList =CustomerDB.listTableAll();
        for (Table table: tableList) {
            result += ",{\"id\":" + "\"" + table.getTableId()+ "\"" + ", "
                     + "\"name\":" + "\"" + table.getTableName()+ "\"" + "}";                  
        }
        return Response.status(200).entity(result).build();
    }
}
