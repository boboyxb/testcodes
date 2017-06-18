<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var $orderItemDG;
    $(function () {
        $orderItemDG = $("#orderItemDataGrid");
        $orderItemDG.datagrid({
            url: '${pageContext.request.contextPath}/order/queryOrderItems?id='+${param.id},
            width: '100%',
            height: '100%',
            fitColumns:true,
            columns: [[
                {title: "id", field: "id",  align: 'center'},
                {title: "订单", field: "dingdan",  align: 'center',
                    formatter:function (value, row, index) {
                        return "${param.id}";
                }},
                {title: "数量", field: "count", align: 'center'},
                {title: "小结", field: "sub", align: 'center'},
                {title: "商品", field: "produc", align: 'center',
                    formatter:function (value, row, index) {
                        return row.product.name;
                    }
                }
            ]]
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'">
        <table id="orderItemDataGrid"></table>
    </div>
</div>

