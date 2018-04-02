

init();
function init() {
    getId();
    listMenu();
   

}

//orderListAll(key);
//listTableNO();
function searchOrderDetailByOrderId(orderId, tableId) {
    $.ajax({
        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/searchOrderDetailByOrderId",
        data: { 'orderId': orderId },
        success: function (data) {
            var mytable = "";
            var str = data.slice(1);
            var i, number = 0, totalPrice = 0;
            //  var id = "", name = "", num = "",price="",menuId="";
            var json = '[' + str + ']';
            obj = JSON.parse(json);
            var mytable = "";


            for (i = 0; i < obj.length; i++) {
                number++;
                totalPrice = totalPrice + obj[i].num * obj[i].price
                mytable += '<tr id="num"' + i + '><td>'
                    + number + '</td><td>'
                    + obj[i].name + '(' + obj[i].price + '฿)</td><td>X&nbsp;' + obj[i].num + '</td>'
                    + '<td>' + obj[i].num * obj[i].price + '฿</td><td><td><i class="la la-edit" onclick="editAmount(\'' + obj[i].menuId + '\',' + obj[i].id + ')"></i></td>'
                    + '</br>' + '</td></tr>';
            }
            var strTotal = '<tr><td></td><td ></td> <td>Total</td><td>' + totalPrice + '&nbsp;฿</td></tr>'
                + '<tr><td></td><td ></td><td ><button class="btn btn-success btn-xs" onclick="submitOrder(' + tableId + ')" >Submit</button>'
                + '</td> <td><button class="btn btn-danger btn-xs" onclick="deleteOrder(\'' + tableId + '\',' + orderId + ')" >Cancel Order</button></td></tr>'

            document.getElementById("myTable").innerHTML = mytable + strTotal;

        },
        error: function (e) {
            alert("Error");
        }
    });
}

function getId() {
    //  alert(idStd);
    $.ajax({
        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/getIdOrder",
        data: {
        },
        success: function (data) {
            swal("Welcome to Restaurant!", "...and click on order now on item to start your order!!");
            document.getElementById("orderId").value = data;
        },
        error: function (e) {
            alert("Error");
        }
    });

}
function sendOrder(orderId, tableId, totalPrice) {

    $.ajax({

        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/insertOrder",
        data: {
            'orderId': orderId,
            'tableId': tableId,
            'totalPrice': totalPrice,
        },
        success: function (data) {
            //  alert("SUCCESS");

        },
        error: function (e) {
            alert("Error");
        }
    });

}
function editAmount(menuId, orderId) {
    window.open("page/orderEdit.html?orderId=" + orderId + "&menuId=" + menuId);
}
function insertOrderDetail(orderId, menuId, amount) {

    $.ajax({

        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/insertOrderDetail",
        data: {
            'orderId': orderId,
            'menuId': menuId,
            'num': amount,
        },
        success: function (data) {

        },
        error: function (e) {
            alert("Error");
        }
    });
}
function deleteOrder(tableId, orderId) {
    $.ajax({

        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/deleteOrder",
        data: {
            'orderId': orderId,

        },
        success: function (data) {
            // location.reload();
            orderListAll(tableId);
            document.getElementById("rowListOrderDetail").style.display = "none";
            document.getElementById("orderListDiv").style.display = "block";
            swal("Cancel Already!", "", "success");
        },
        error: function (e) {
            alert("Error");
        }
    });



}
function listMenu() {

    $.ajax({
        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/listMenu",
        data: {
        },
        success: function (data) {
            var str = data.slice(1);
            var i, number = 0, totalPrice = 0;
            var myList;
            var json = '[' + str + ']';
            obj = JSON.parse(json);
            for (i = 0; i < obj.length; i++) {

                //   myList += '<option value="'+obj[i].id+'">'+obj[i].menuName + ':' + obj[i].price +' &nbsp;Baht</option>'
                //  myList += '<input type="checkbox" name="coffee" value="' + obj[i].id + '">' + obj[i].menuName + ':' + obj[i].price
                //     + ' &nbsp;Baht Num: <input id="orderId" type="number" value="1" ><br>'
                myList += '<div class="col-md-6 col-sm-6"> <div class="pricing-item">'
                    + ' <a href="#"> <img class="img-responsive "  src="' + obj[i].img + '" alt=""> </a>'
                    + '<div class="pricing-item-details">'
                    + ' <h3> <a href="#">' + obj[i].menuName + '</a> </h3>'
                    + ' <p>sed do eiusmod tempor incididunt utlabore et dolore magna aliqua.</p>'
                    + ' <a class="btn btn-danger" onclick="getMenu(\'' + obj[i].id + '\',\'' + obj[i].menuName + '\',\'' + obj[i].img + '\',' + obj[i].price + ')"><i class="la la-cart-plus"></i>Order now</a>'
                    + '<div class="clearfix"></div>'
                    + '</div> <!--price tag--><span class="hot-tag br-red">' + obj[i].price + '฿</span><div class="clearfix"></div></div> </div>'

            }

            var myListStr = myList.slice(9);
            document.getElementById("menuList").innerHTML = myListStr;
        },
        error: function (e) {
            alert("Error");
        }
    });
}

function listTableNO() {
    $.ajax({
        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/listTableNO",
        data: {
        },
        success: function (data) {
            var str = data.slice(1);
            var i, number = 0, totalPrice = 0;
            var myList, myListAll = '<option value="">Select Table</option>';
            var json = '[' + str + ']';
            var result = "";
            obj = JSON.parse(json);
            for (i = 0; i < obj.length; i++) {

                myList += '<option value="' + obj[i].id + '">' + obj[i].name + '</option>'
            }

            // document.getElementById("tableList").innerHTML = myListAll + myList;
        },
        error: function (e) {
            alert("Error");
        }
    });
}
function getTableId() {
    var id = document.getElementById("tableList");
    var tableId = id.options[id.selectedIndex].value;
    document.getElementById("tableId").value = tableId;

}

function getMenu(menuId, name, img, price) {

    amount = 1;

    listSer = $('#listSer').val();
    strCompar = '"menuId":' + menuId;
    var resSearch = listSer.search(strCompar);
    var data;
    var i, amountNow=1, priceNow;
    if (resSearch != -1) {
        // Duplicate;

        var strCut = listSer.slice(1);
        var json = '[' + strCut + ']';
        obj = JSON.parse(json);
        for (i = 0; i < obj.length; i++) {
            if (obj[i].menuId == menuId) {
                amountNow = obj[i].amount;
                priceNow = obj[i].price;

            }
        }
        pricePerUnit = priceNow / amountNow;
        amountAdd = amountNow + 1;
        priceNowNew = pricePerUnit * amountAdd;

        strRemove = ',{"menuId":' + menuId + ',"amount":' + amountNow + ',"price":' + priceNow + ',"img":"' + img + '","name":"' + name + '"}';
        strNew = ',{"menuId":' + menuId + ',"amount":' + amountAdd + ',"price":' + priceNowNew + ',"img":"' + img + '","name":"' + name + '"}';
        var res = listSer.replace(strRemove, strNew);
        data = res;
        document.getElementById("listSer").value = res;
        swal(" Order Now!", ""+ name +"("+price+" ฿)"+" X "+amountAdd, "success");
    } else {
        data = listSer + ',{"menuId":' + menuId + ',"amount":' + amount + ',"price":' + price + ',"img":"' + img + '",' + '"name":"' + name + '"}';
        document.getElementById("listSer").value = data;
        swal(" Order Now!", ""+ name +"("+price+" ฿)"+" X "+amountNow, "success");
    }

    var str = data.slice(1);
    var i, number = 0;
    var myList = "";
    var json = '[' + str + ']';
    obj = JSON.parse(json);
    var totalPrice = 0;

    for (i = 0; i < obj.length; i++) {
        number++
        totalPrice = totalPrice + obj[i].price;
        myList += '<tr><td>' + number + '</td> <td><img class="img-responsive " height="80" width="80" src="' + obj[i].img + '" alt=""></td>'
            + '<td>' + obj[i].amount + 'x ' + obj[i].name + ' <br/><button class="btn btn-danger btn-normal btn-xs"'
            + 'onclick="removeMenu(\'' + obj[i].menuId + '\',\'' + obj[i].amount + '\',\'' + obj[i].name + '\',\'' + obj[i].img + '\',' + obj[i].price + ')">Remove</button></td>'
            + ' <td>' + obj[i].price + ' ฿</td> </tr>';
    }
    var listTotalPrice = '<tr><td></td><td></td> <td>Total Price</td> <td>' + totalPrice + '฿</td></tr>'
        + '<tr><td></td><td></td> <td></td><td><button class="btn btn-danger btn-normal btn-sm" onclick="orderNow(' + totalPrice + ')">Order Now</button></td></tr>';
    document.getElementById("listOrder").innerHTML = myList + listTotalPrice;
    
    
}
function removeMenu(menuId, amount, name, img, price) {

    listSer = $('#listSer').val();
    strRemove = ',{"menuId":' + menuId + ',"amount":' + amount + ',"price":' + price + ',"img":"' + img + '","name":"' + name + '"}'
    var res = listSer.replace(strRemove, "");
    document.getElementById("listSer").value = res;
    data = $('#listSer').val();
    var str = res.slice(1);
    var i, number = 0;
    var myList = "";
    var json = '[' + str + ']';
    obj = JSON.parse(json);
    var totalPrice = 0;
    for (i = 0; i < obj.length; i++) {
        number++
        totalPrice = totalPrice + obj[i].price;
        myList += '<tr><td>' + number + '</td> <td><img class="img-responsive " height="80" width="80" src="' + obj[i].img + '" alt=""></td>'
            + '<td>' + obj[i].amount + 'x ' + obj[i].name + ' <br/><button class="btn btn-danger btn-normal btn-xs"'
            + 'onclick="removeMenu(\'' + obj[i].menuId + '\',\'' + obj[i].amount + '\',\'' + obj[i].name + '\',\'' + obj[i].img + '\',' + obj[i].price + ')">Remove</button></td>'
            + ' <td>' + obj[i].price + ' ฿</td> </tr>';
    }
    var listTotalPrice = '<tr><td></td><td></td> <td>Total Price</td> <td>' + totalPrice + '฿</td></tr>'
        + '<tr><td></td><td></td> <td></td><td><button class="btn btn-danger btn-normal btn-sm" onclick="orderNow(' + totalPrice + ')">Order Now</button></td></tr>';
    document.getElementById("listOrder").innerHTML = myList + listTotalPrice;
    swal("Remove Already!", "", "success");


}
function orderNow(totalPrice) {
    var data = document.getElementById("listSer").value;
    var orderId = document.getElementById("orderId").value;
    var tableId = document.getElementById("tableId").value;
    var str = data.slice(1);
    var json = '[' + str + ']';
    var i, myList = "";
    obj = JSON.parse(json);
    for (i = 0; i < obj.length; i++) {
        var menuId = obj[i].menuId;
        var amount = obj[i].amount;
        insertOrderDetail(orderId, menuId, amount)

    }

    sendOrder(orderId, tableId, totalPrice);
    swal("Already!", "and..please check your order!", "success");
    searchOrderDetailByOrderId(orderId, tableId);
    //hide block
    document.getElementById("rowListOrder").style.display = "none";
    document.getElementById("rowListOrderDetail").style.display = "block";
   



}
function submitOrder(tableId) {
    // location.reload();
    //   key=document.getElementById("tableId").value;
    orderListAll(tableId);
    // window.location.href = window.location.href + "?tableId="+tableId;
    document.getElementById("rowListOrderDetail").style.display = "none";
    document.getElementById("orderListDiv").style.display = "block";
    swal("Already!", "Thank you!", "success");

}

function orderListAll(tableId) {

    $.ajax({
        type: "POST",
        url: "http://192.168.43.94:8080/OrderFoodService/rest/customer/searchOrderByTableId",
        data: { 'tableId': tableId },
        success: function (data) {
            var mytable = "";
            var str = data.slice(1);
            var i, number = 0;
            var json = '[' + str + ']';

            obj = JSON.parse(json);

            for (i = 0; i < obj.length; i++) {
                number++;

                mytable += '<tr id="num"' + i + '><td>'
                    + number + '</td><td>'
                    + obj[i].orderDate + '</td><td>' + obj[i].orderId + '</td>'
                    + '<td>' + obj[i].statusName + '</td><td>'
                    + '<td><i class="la la-file" onclick="openBill()"></i></td></br>' + '</td></tr>';
            }

            document.getElementById("orderListAll").innerHTML = mytable;


        },
        error: function (e) {
            alert("Error");
        }
    });
}

function openBill() {
    alert("init");
}