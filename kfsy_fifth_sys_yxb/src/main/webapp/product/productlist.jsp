<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var $productDG, $productDL;
    $(function () {
        $productDG = $("#productDataGrid");
        $productDL = $("#productDialog");
        $productDG.datagrid({
            url: '${pageContext.request.contextPath}/product/queryAll',
            width: '100%',
            height: '100%',
            fitColumns:true,
            columns: [[
                {title: "id", field: "id",  align: 'center'},
                {title: "药品名称", field: "name",  align: 'center'},
                {title: "功能主治", field: "indication", align: 'center'},
                {title: "规格", field: "standard", align: 'center'},
                {title: "价格", field: "price", align: 'center'},
                {title: "类别", field: "type", align: 'center',
                    formatter:function (value, row, index) {
                        return row.type.name;
                    }
                },
                {title: "库存", field: "stock", width: 50, align: 'center'},
                {title: "缩略图预览", field: " ",width:200,align:'center',
                    formatter:function (value, row, index) {
                        return "<img style='width:200px;height:100px;' src='${pageContext.request.contextPath}"+row.imagePath+"' />";
                    }
                },
                {
                    title: "操作", field: "options", width: 300, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class='del' onClick=\"delProduct('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                "<a class='detail' onClick=\"detailProduct('" + row.id + "')\" href='javascript:;'>详情</a>&nbsp;&nbsp;"+
                                "<a class='edit' onClick=\"editStockRow('" + row.id + "')\"  href='javascript:;'>修改</a>";
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                $(".del").linkbutton({
                    plain: true,
                    iconCls: 'icon-remove',
                });
                $(".detail").linkbutton({
                    plain: true,
                    iconCls: 'icon-application_view_detail',
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
            toolbar: '#productToolBars',
        });
    });

    //删除的操作
    function delProduct(id) {
        $.messager.confirm("提示", "您确定要删除吗?", function (r) {
            if (r) {
                //发送异步请求删除数据
                $.post('${pageContext.request.contextPath}/product/delete', {"id": id}, function (msg) {
                    if(msg.success){
                        $.messager.show({
                            title: '删除提示',
                            msg: '删除成功',
                            timeout: '1000',
                        });
                        $productDG.datagrid('reload');
                    }else{
                        alert(msg.message);
                    }
                },"JSON")

            }
        });
    }

    //修改的操作
    function editStockRow(id) {
        $productDL.dialog({
            width: 600,
            height: 300,
            title: "管理员的详细信息",
            iconCls: "icon-man",
            href: '${pageContext.request.contextPath}/product/productedit.jsp?id=' + "\'" + id + "\'",
            buttons: [{
                text: '保存',
                iconCls: 'icon-save',
                handler: saveStock,
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: productCloseDa,
            }],
        });
    }
    //保存库存
    function saveStock() {
        $("#editProductForm").form('submit', {
            url: '${pageContext.request.contextPath}/product/alterStock',
            success: function (data) {
                var data = eval('(' + data + ')');
                if (data.success){
                    $productDL.dialog('close', true);
                    $productDG.datagrid('reload');
                }else{
                    alert(data.message);
                }
            }
        });
    }


    //展示详情
    function detailProduct(id) {
        $productDL.dialog({
            width: 600,
            height: 300,
            title: "药品详情",
            iconCls: "icon-application_view_detail",
            href: '${pageContext.request.contextPath}/product/productdetail.jsp?id=' + "\'" + id + "\'",
            buttons: [{
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: productCloseDa,
            }],
        });
    }
    //关闭对话框
    function productCloseDa() {
        $productDL.dialog('close', true);
    }
    //添加药品
    function addProduct() {
        $productDL.dialog({
            width: 600,
            height: 300,
            title: "添加药品",
            iconCls: "icon-pictures",
            href: '${pageContext.request.contextPath}/product/productAdd.jsp',
            buttons: [{
                text: '提交',
                iconCls: 'icon-save',
                handler: addProductTO,
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: productCloseDa,
            }],
        });
    }
    //提交添加
    function addProductTO() {
        $("#addProduct").form('submit', {
            url: '${pageContext.request.contextPath}/product/insert',
            success: function (data) {
                var data = eval('(' + data + ')');
                if (data.success){
                    $productDL.dialog('close', true);
                    $productDG.datagrid('reload');
                }else{
                    alert(data.message);
                }
            }
        });
    }
    //搜索的处理程序
    function searchProduct(value, name) {
        //发送ajax查询
        $productDG.datagrid({
            url: '${pageContext.request.contextPath}/product/queryLikeName?name='+value,
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="height: 50px;">
        <div style="text-align: center;margin: 10px 0px 10px 0px;">
            <input id="ss" class="easyui-searchbox" style="width:300px"
                   data-options="prompt:'模糊查询',menu:'#mm',searcher:searchProduct"/>
            <div id="mm" style="width:120px">
                <div data-options="name:'name',iconCls:'icon-ok'">药品名</div>
            </div>
        </div>
    </div>
    <div data-options="region:'center'">
        <table id="productDataGrid"></table>
        <div id="productDialog"></div>
        <div id="productToolBars">
            <a onclick="addProduct();" href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-add',plain:true">添加</a>
        </div>
    </div>
</div>