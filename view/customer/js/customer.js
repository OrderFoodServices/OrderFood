getId();
listMenu();
listTableNO();
function searchOrderDetailByOrderId() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/OrderFoodService/rest/customer/searchOrderDetailByOrderId",
        data: { 'orderId': $('#orderId').val() },
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
                    + obj[i].name + '</td><td>' + obj[i].price + '</td><td>' + obj[i].num + '</td>'
                    + '<td>' + obj[i].num * obj[i].price + '</td><td>'
                    + '<i class="fa fa-trash" id=' + obj[i].id + ',' + obj[i].menuId + ' onclick="deleteList(' + obj[i].id + ',' + obj[i].menuId + ')"></i>'
                    + '</br>' + '</td></tr>';
            }
            document.getElementById("myTable").innerHTML = mytable;
            document.getElementById("totalPrice").value = totalPrice;
            document.getElementById("num").value = 1;

        },
        error: function (e) {
            alert("Error");
        }
    });
}

function openPage() {
    window.open("page/test.html");
}
function getId() {
    //  alert(idStd);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/OrderFoodService/rest/customer/getIdOrder",
        data: {
        },
        success: function (data) {
            //   alert(data);
            document.getElementById("orderId").value = data;
        },
        error: function (e) {
            alert("Error");
        }
    });

}

function doSent() {
    sendOrder();
}
function sendOrder() {

    $.ajax({

        type: "POST",
        url: "http://localhost:8080/OrderFoodService/rest/customer/insertOrder",
        data: {
            'orderId': $('#orderId').val(),
            'tableId': $('#tableId').val(),
            'totalPrice': $('#totalPrice').val(),
        },
        success: function (data) {
            alert("SUCCESS");
            location.reload();

        },
        error: function (e) {
            alert("Error");
        }
    });

}
function editList(idStd) {
    window.open("page/editStudent.html?idStd=" + idStd);
}
function insertOrderDetail() {
    $.ajax({

        type: "POST",
        url: "http://localhost:8080/OrderFoodService/rest/customer/insertOrderDetail",
        data: {
            'orderId': $('#orderId').val(),
            'menuId': $('#menuId').val(),
            'num': $('#num').val(),
        },
        success: function (data) {
            alert("SUCCESS");
            searchOrderDetailByOrderId();
            document.getElementById("menuId").value = "";
            document.getElementById("num").value = "";

        },
        error: function (e) {
            alert("Error");
        }
    });
}
function deleteList(orderId, menuId) {

    $.ajax({

        type: "POST",
        url: "http://localhost:8080/OrderFoodService/rest/customer/deleteOrderDetail",
        data: {
            'orderId': orderId,
            'menuId': menuId,

        },
        success: function (data) {
            alert("SUCCESS");
            searchOrderDetailByOrderId();

        },
        error: function (e) {
            alert("Error");
        }
    });



}
function listMenu() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/OrderFoodService/rest/customer/listMenu",
        data: {
        },
        success: function (data) {
            var str = data.slice(1);
            var i, number = 0, totalPrice = 0;
            var myList, myListAll= '<option value="">Select Menu</option>';
            var json = '[' + str + ']';
            obj = JSON.parse(json);
            for (i = 0; i < obj.length; i++) {
            
               myList += '<option value="'+obj[i].id+'">'+obj[i].menuName + ':' + obj[i].price +' &nbsp;Baht</option>'
            }
            document.getElementById("menuList").innerHTML=myListAll+myList;
        },
        error: function (e) {
            alert("Error");
        }
    });
}
function getMunuId() {
    var id = document.getElementById("menuList");
    var menuId = id.options[id.selectedIndex].value;
    document.getElementById("menuId").value=menuId;
   

}
function listTableNO(){
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/OrderFoodService/rest/customer/listTableNO",
        data: {
        },
        success: function (data) {
            var str = data.slice(1);
            var i, number = 0, totalPrice = 0;
            var myList, myListAll= '<option value="">Select Table</option>';
            var json = '[' + str + ']';
            var result="";
            obj = JSON.parse(json);
            for (i = 0; i < obj.length; i++) {
         
               myList += '<option value="'+obj[i].id+'">'+obj[i].name + '</option>'
            }

            document.getElementById("tableList").innerHTML=myListAll+myList;
        },
        error: function (e) {
            alert("Error");
        }
    });
}
function getTableId(){
    var id = document.getElementById("tableList");
    var tableId = id.options[id.selectedIndex].value;
    document.getElementById("tableId").value=tableId;

}