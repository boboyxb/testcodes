<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var $typeDG, $typeDL;
    $(function () {
        $typeDG = $("#typeDataGrid");
        $typeDL = $("#typeDialog");
        $typeDG.datagrid({
            url: '${pageContext.request.contextPath}/type/queryAllType',
            width: '100%',
            height: '100%',
            columns: [[
                {title: "id", field: "id", width: 200, align: 'center'},
                {title: "名称", field: "name", width: 200, align: 'center'},
                {title: "地址", field: "href", width: 200, align: 'center'},
                {title: "预览", field: " ",width:200,align:'center',
                    formatter:function (value, row, index) {
                        return "<img style='width:80px;height:80px;' src='${pageContext.request.contextPath}"+row.href+"' />";
                    }
                },
                {
                    title: "操作", field: "options", width: 300, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class='del' onClick=\"delType('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;";
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                $(".del").linkbutton({
                    plain: true,
                    iconCls: 'icon-remove',
                });
            },
            pagination: true,
            pageNumber: 1,
            pageSize: 2,
            pageList: [2, 4, 6, 8, 10],
            toolbar: '#typeToolbars',
            onClickRow:function (i, row) {
                $('.edit').tooltip({
                    position: 'right',
                    trackMouse:true,
                    content: '<span style="color:#fff">'+row.id+','+row.name+','+row.href+'</span>',
                    onShow: function () {
                        $(this).tooltip('tip').css({backgroundColor: '#666', borderColor: '#666'});
                    }
                });
            }
        });
    });
    //删除的操作
    function delType(id) {
        $.messager.confirm("提示", "您确定要删除吗?", function (r) {
            if (r) {
                //发送异步请求删除数据
                $.post('${pageContext.request.contextPath}/type/delete', {"id": id}, function (msg) {
                    if(msg.success){
                        $.messager.show({
                            title: '删除提示',
                            msg: '删除成功',
                            timeout: '1000',
                        });
                        $typeDG.datagrid('reload');
                    }else{
                        alert(msg.message);
                    }
                },"JSON")

            }
        });
    }
    //关闭对话框
    function closeTypeDa() {
        $typeDL.dialog('close', true);
    }
    //添加分类
    function addType() {
        $typeDL.dialog({
            width: 600,
            height: 300,
            title: "添加分类",
            iconCls: "icon-man",
            href: '${pageContext.request.contextPath}/type/typeAdd.jsp',
            buttons: [{
                text: '提交',
                iconCls: 'icon-save',
                handler: addTypeTO,
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: closeTypeDa,
            }],
        });
    }
    //提交添加
    function addTypeTO() {
        $("#addType").form('submit', {
            url: '${pageContext.request.contextPath}/type/insert',
            success: function (data) {
                var data = eval('(' + data + ')');
                if (data.success){
                    $dl.dialog('close', true);
                    $dg.datagrid('reload');
                }else{
                    alert(data.message);
                }
            }
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'">
        <table id="typeDataGrid"></table>
        <div id="typeDialog"></div>
        <div id="typeToolbars">
            <a onclick="addType();" href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-add',plain:true">添加</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-page_excel',plain:true">导出到excel</a>
        </div>
    </div>
</div>