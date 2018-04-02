//Aunchalee.k
package ku.kps.cs.ws.rest;

import ku.kps.cs.rest.db.*;
import java.util.List;
import ku.kps.cs.ws.model.Order;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ku.kps.cs.ws.model.OrderDetail;

@Path("/shopkeeper")
public class ShopKeeperResource {

    private static Logger log = Logger.getLogger("InfoLogging");

    @POST
    @Path("/searchOrder")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getOrder(@FormParam("OrderDate") String orderDate, @FormParam("StatusID") String statusID) {
        if (orderDate.trim().length() == 0 && statusID.trim().length() == 0) {
            return Response.serverError().entity("Input Order Date , Status ID or both!").build();
        }

        String result = "";
        String rec = "";
        List<Order> orderList = ShopKeeperDB.searchOrder(orderDate, statusID);
        for (Order order : orderList) {
            result += ",{\"orderID\":" + "\"" + order.getOrderId() + "\"" + ","
                    + "\"orderDate\":" + "\"" + order.getOrderDate() + "\"" + ","
                    + "\"tableID\":" + "\"" + order.getTableId() + "\"" + ","
                    + "\"statusID\":" + "\"" + order.getStatusId() + "\"" + "}";
        }
        return Response.status(200).entity(result).build();
    }

    @POST
    @Path("/insertOrder")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertOrder(@FormParam("Orderid") String orderID, @FormParam("MenuID") String menuID,
            @FormParam("Num") String num, @FormParam("Total Price") String totalPrice,
            @FormParam("Amount") String amount, @FormParam("Price") String price) {
        OrderDetail orD = new OrderDetail();
        orD.setOrderId(orderID);
        orD.setMenuId(menuID);
        orD.setNum(0);
        orD.setTotalPrice(0);
        orD.setAmount(0);
        orD.setPrice(0);

        int res = ShopKeeperDB.insertOrder(orD);
       // String result = "Add Order ID ="+ orerId+"("
     return Response.status(Response.Status.OK).entity(menuID/*temporary value  change later*/).build();
    }
 
  /*  @POST
    @Path("/viewOrder")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getOrderDetail(@FormParam("OrderID")String orderID){
        if(orderID.trim().length()==0){
            return Response.serverError().entity("Order Id is Blank!").build();
            
        }
        String result = "";
        String res = ""2;
        
        List<OrderDetail> orDList = ShopKeeperDB.getOrderDetail(orderID);
        for (OrderDetail odD:orDList) {
            result +=",{\"orderId\":" + "\""+odD.getOrderId()+"\""+", "
                    +"\""+;
        }
    }*/
}
