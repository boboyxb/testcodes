<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var $alternateDG, $alternateDL;
    $(function () {
        $alternateDG = $("#alternateDataGrid");
        $alternateDL = $("#alternateDialog");
        $alternateDG.datagrid({
            url: '${pageContext.request.contextPath}/alternate/queryAll',
            width: '100%',
            height: '100%',
            columns: [[
                {title: "id", field: "id", width: 200, align: 'center'},
                {title: "地址", field: "imagePath", width: 200, align: 'center'},
                {title: "预览", field: " ",width:200,align:'center',
                    formatter:function (value, row, index) {
                        return "<img style='width:200px;height:100px;' src='${pageContext.request.contextPath}"+row.imagePath+"' />";
                    }
                },
                {
                    title: "操作", field: "options", width: 300, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class='del' onClick=\"delAlternate('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;";
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
            toolbar: '#alternateToolBars',
            onClickRow:function (i, row) {
                $('.edit').tooltip({
                    position: 'right',
                    trackMouse:true,
                    content: '<span style="color:#fff">'+row.id+','+row.name+','+row.imagePath+'</span>',
                    onShow: function () {
                        $(this).tooltip('tip').css({backgroundColor: '#666', borderColor: '#666'});
                    }
                });
            }
        });
    });

    //删除的操作
    function delAlternate(id) {
        $.messager.confirm("提示", "您确定要删除吗?", function (r) {
            if (r) {
                //发送异步请求删除数据
                $.post('${pageContext.request.contextPath}/alternate/delete', {"id": id}, function (msg) {
                    if(msg.success){
                        $.messager.show({
                            title: '删除提示',
                            msg: '删除成功',
                            timeout: '1000',
                        });
                        $alternateDG.datagrid('reload');
                    }else{
                        alert(msg.message);
                    }
                },"JSON")

            }
        });
    }
    //关闭对话框
    function alternateCloseDa() {
        $alternateDL.dialog('close', true);
    }
    //添加轮播图
    function addAlternate() {
        $alternateDL.dialog({
            width: 600,
            height: 300,
            title: "添加轮播图",
            iconCls: "icon-pictures",
            href: '${pageContext.request.contextPath}/alternate/alternateAdd.jsp',
            buttons: [{
                text: '提交',
                iconCls: 'icon-save',
                handler: addAlternateTO,
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: alternateCloseDa,
            }],
        });
    }
    //提交添加
    function addAlternateTO() {
        $("#addAlternate").form('submit', {
            url: '${pageContext.request.contextPath}/alternate/insert',
            success: function (data) {
                var data = eval('(' + data + ')');
                if (data.success){
                    $alternateDL.dialog('close', true);
                    $alternateDG.datagrid('reload');
                }else{
                    alert(data.message);
                }
            }
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'">
        <table id="alternateDataGrid"></table>
        <div id="alternateDialog"></div>
        <div id="alternateToolBars">
            <a onclick="addAlternate();" href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-add',plain:true">添加</a>
        </div>
    </div>
</div>