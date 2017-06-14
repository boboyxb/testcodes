<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var $adminDG, $adminDL;
    $(function () {
        $adminDG = $("#adminDataGrid");
        $adminDL = $("#adminDialog");
        $adminDG.datagrid({
            url: '${pageContext.request.contextPath}/admin/queryAll',
            width: '100%',
            height: '100%',
            columns: [[
                {title: "id", field: "id", width: 200, align: 'center'},
                {title: "姓名", field: "name", width: 200, align: 'center'},
                {title: "级别", field: "level", width: 200, align: 'center'},
                {
                    title: "操作", field: "options", width: 300, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class='del' onClick=\"delAdmin('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                "<a class='edit' onClick=\"editAdminRow('" + row.id + "')\"  href='javascript:;'>修改</a>";
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                $(".del").linkbutton({
                    plain: true,
                    iconCls: 'icon-remove',

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
            toolbar: '#adminToolBars',
            onClickRow:function (i, row) {
                $('.edit').tooltip({
                    position: 'right',
                    trackMouse:true,
                    content: '<span style="color:#fff">'+row.id+','+row.name+','+row.level+'</span>',
                    onShow: function () {
                        $(this).tooltip('tip').css({backgroundColor: '#666', borderColor: '#666'});
                    }
                });
            }
        });
    });

    //删除的操作
    function delAdmin(id) {
        $.messager.confirm("提示", "您确定要删除吗?", function (r) {
            if (r) {
                //发送异步请求删除数据
                $.post('${pageContext.request.contextPath}/admin/delete', {"id": id}, function (msg) {
                    if(msg.success){
                        $.messager.show({
                            title: '删除提示',
                            msg: '删除成功',
                            timeout: '1000',
                        });
                        $adminDG.datagrid('reload');
                    }else{
                        alert(msg.message);
                    }
                },"JSON")

            }
        });
    }
    //修改的操作
    function editAdminRow(id) {
        $adminDL.dialog({
            width: 600,
            height: 300,
            title: "管理员的详细信息",
            iconCls: "icon-man",
            href: '${pageContext.request.contextPath}/admin/adminEdit.jsp?id=' + "\'" + id + "\'",
            buttons: [{
                text: '保存',
                iconCls: 'icon-save',
                handler: saveAdmin,
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: adminCloseDa,
            }],
        });
    }
    //保存用户
    function saveAdmin() {
        $("#inputAdminForm").form('submit', {
            url: '${pageContext.request.contextPath}/admin/update',
            success: function (data) {
                var data = eval('(' + data + ')');
                if (data.success){
                    $adminDL.dialog('close', true);
                    $adminDG.datagrid('reload');
                }else{
                    alert(data.message);
                }
            }
        });
    }
    //关闭对话框
    function adminCloseDa() {
        $dl.dialog('close', true);
    }

    //添加管理员
    function addAdmin() {
        $adminDL.dialog({
            width: 600,
            height: 300,
            title: "添加管理员",
            iconCls: "icon-man",
            href: '${pageContext.request.contextPath}/admin/adminAdd.jsp',
            buttons: [{
                text: '提交',
                iconCls: 'icon-save',
                handler: addAdminTO,
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: adminCloseDa,
            }],
        });
    }
    //添加管理员
    function addAdminTO() {
        $("#addAdminForm").form('submit', {
            url: '${pageContext.request.contextPath}/admin/insert',
            success: function (data) {
                var data = eval('(' + data + ')');
                if (data.success){
                    $adminDL.dialog('close', true);
                    $adminDG.datagrid('reload');
                }else{
                    alert(data.message);
                }
            }
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'">
        <table id="adminDataGrid"></table>
        <div id="adminDialog"></div>
        <div id="adminToolBars">
            <a onclick="addAdmin();" href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-add',plain:true">添加</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-page_excel',plain:true">导出到excel</a>
        </div>
    </div>
</div>