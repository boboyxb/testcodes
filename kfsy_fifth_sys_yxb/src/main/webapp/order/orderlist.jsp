<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var $orderDG, $orderDL;
    $(function () {
        $orderDG = $("#orderDataGrid");
        $orderDL = $("#orderDialog");
        $orderDG.datagrid({
            url: '${pageContext.request.contextPath}/order/queryAll',
            width: '100%',
            height: '100%',
            fitColumns:true,
            columns: [[
                {title: "id", field: "id",  align: 'center'},
                {title: "编号", field: "num",  align: 'center'},
                {title: "其他信息", field: "elseMessage", align: 'center'},
                {title: "状态", field: "status", align: 'center'},
                {title: "创建时间", field: "createTime", align: 'center'},
                {title: "总价", field: "total", align: 'center'},
                {
                    title: "收货地址", field: "  ", width: 300, align: 'center',
                    formatter: function (value, row, index) {
                        return  row.address.recevieAddress;
                    }
                },
                {
                    title: "操作", field: "options", width: 300, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class='detail' onClick=\"detailOrder('" + row.id + "')\"  href='javascript:;'>查看详细物品</a>";
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                $(".detail").linkbutton({
                    plain: true,
                    iconCls: 'icon-cart_magnify',
                });
                $(".edit").linkbutton({
                    plain: true,
                    iconCls: 'icon-edit',
                });
            },
            pagination: true,
            pageNumber: 1,
            pageSize: 2,
            pageList: [2, 4, 6, 8, 10],
            toolbar: '#orderToolBars',
        });
    });
    function detailOrder(id) {
        $orderDL.dialog({
            width: 600,
            height: 300,
            title: "订单详细信息",
            iconCls: "icon-man",
            href: '${pageContext.request.contextPath}/order/orderItemList.jsp?id=' + "\'" + id + "\'",
            buttons: [ {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: orderCloseDa,
            }],
        });
    }

    //关闭对话框
    function orderCloseDa() {
        $productDL.dialog('close', true);
    }
    //搜索的处理程序
    function searchOrder(value, name) {
        //发送ajax查询
        $orderDG.datagrid({
            url: '${pageContext.request.contextPath}/order/queryByUserId?id='+value,
        });
    }
    function searchPayed() {
        $orderDG.datagrid({
            url: '${pageContext.request.contextPath}/order/queryPayed',
        });
    }
    function searchUnPay() {
        $orderDG.datagrid({
            url: '${pageContext.request.contextPath}/order/queryUnPayed',
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="height: 50px;">
        <div style="text-align: center;margin: 10px 0px 10px 0px;">
            <input id="ssOrder" class="easyui-searchbox" style="width:300px"
                   data-options="prompt:'查询',menu:'#mmOrder',searcher:searchOrder"/>
            <div id="mmOrder" style="width:120px">
                <div data-options="name:'userId',iconCls:'icon-ok'">用户id</div>
            </div>
        </div>
    </div>
    <div data-options="region:'center'">
        <table id="orderDataGrid"></table>
        <div id="orderDialog"></div>
        <div id="orderToolBars">
            <a onclick="searchPayed();" href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-money_add',plain:true">已支付</a>
            <a onclick="searchUnPay();" href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-money_delete',plain:true">未支付</a>
        </div>
    </div>
</div>