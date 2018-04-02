init();
function init() {
    var urlParams = new URLSearchParams(window.location.search);
    var orderId = urlParams.get('orderId');
    var menuId = urlParams.get('menuId');
    var myStr = ""
    $.ajax({
        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/searchOrderByOrderIdAndMenuId",
        data: {
            'orderId': orderId,
            'menuId': menuId
        },
        success: function (data) {

            obj = JSON.parse(data);

            orderId_ = obj.id;
            manuId_ = obj.menuId;
            name = obj.name;
            num_ = obj.num;
            price = obj.price;

            // myStr = '<tr><td>1</td><td>'+name+'('+ price+')</td>'
            //     + '<td> <input type="number" class="form-control input-solid" id="solidInput" value="'+num+'"  style="width: 6em"></td></tr>'
            //     + '<tr><td></td><td></td><td></td> <td><button class="btn btn-success btn-sm" onclick="update(' + orderId + ',' + manuId+ ','+num + ')">Submit</button></td> </tr>';

            //  document.getElementById("listDetail").innerHTML =myStr;
            document.getElementById("orderId_").value = orderId_;
            document.getElementById("menuId_").value = manuId_;
            document.getElementById("menuName").value = name;
            document.getElementById("price").value = price;
            document.getElementById("num_").value = num_;
        },
        error: function (e) {
            alert("Error");
        }
    });


}
function updateData() {

    var orderId = $('#orderId_').val();
    var menuId = $('#menuId_').val();
    var num = $('#num_').val();
  
    $.ajax({
        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/updateOrderDetail",
        data: {
            'orderId': orderId,
            'menuId': menuId,
            'num': num
        },
        success: function (data) {
            alert("update success");
            searchOrderByOrderIdAndMenuId(orderId,menuId)
        },
        error: function (e) {
            alert("Error");
        }
    });

}
function searchOrderByOrderIdAndMenuId(orderId,menuId){
    alert("init");

    $.ajax({
        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/searchOrderByOrderIdAndMenuId",
        data: {
            'orderId': orderId,
            'menuId': menuId
        },
        success: function (data) {

            obj = JSON.parse(data);

            orderId_ = obj.id;
            manuId_ = obj.menuId;
            name = obj.name;
            num_ = obj.num;
            price = obj.price;
            alert(obj.num);

            myStr = '<tr><td>1</td><td>'+name+'('+ price+')</td>'
                + '<td>'+obj.num+'</td></tr>'
                + '<tr><td></td><td></td><td></td> <td></td> </tr>';

             document.getElementById("listDetail").innerHTML =myStr;
          
        },
        error: function (e) {
            alert("Error");
        }
    });


}