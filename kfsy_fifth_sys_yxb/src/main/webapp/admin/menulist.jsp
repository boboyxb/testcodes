<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var $dg, $dl;
    $(function () {
        $dg = $("#dataGrid");
        $dl = $("#dialog");
        $dg.datagrid({
            url: '${pageContext.request.contextPath}/user/queryAll',
            width: '100%',
            height: '100%',
            columns: [[
                {title: "编号", field: "id", width: 200, align: 'center'},
                {title: "姓名", field: "username", width: 200, align: 'center'},
                {title: "手机", field: "phone", width: 200, align: 'center'},
                {
                    title: "操作", field: "options", width: 300, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class='del' onClick=\"del('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                "<a class='edit' onClick=\"editRow('" + row.id + "')\"  href='javascript:;'>修改</a>";
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
            toolbar: '#toolbars',
            onClickRow:function (i, row) {
                $('.edit').tooltip({
                    position: 'right',
                    trackMouse:true,
                    content: '<span style="color:#fff">'+row.id+','+row.name+','+row.phone+'</span>',
                    onShow: function () {
                        $(this).tooltip('tip').css({backgroundColor: '#666', borderColor: '#666'});
                    }
                });
            }
        });
    });

    //删除的操作
    function del(id) {
        $.messager.confirm("提示", "您确定要删除吗?", function (r) {
            if (r) {
                //发送异步请求删除数据
                $.post('${pageContext.request.contextPath}/user/delete', {"id": id}, function (msg) {
                    $.messager.show({
                        title: '删除提示',
                        msg: '删除成功',
                        timeout: '1000',
                    });
                    $dg.datagrid('reload');
                })

            }
        });
    }
    //修改的操作
    function editRow(id) {
        $dl.dialog({
            width: 600,
            height: 300,
            title: "员工的详细信息",
            iconCls: "icon-man",
            href: '${pageContext.request.contextPath}/edit/userEdit.jsp?id=' + "\'" + id + "\'",
            buttons: [{
                text: '保存',
                iconCls: 'icon-save',
                handler: saveUser,
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: closeDa,
            }],
        });
    }
    //保存用户
    function saveUser() {
        $("#inputForm").form('submit', {
            url: '${pageContext.request.contextPath}/user/update',
            success: function () {
                $dl.dialog('close', true);
                $dg.datagrid('reload');
            }
        });
    }
    //关闭对话框
    function closeDa() {
        $dl.dialog('close', true);
    }

    //搜索的处理程序
    function search(value, name) {
        console.log(value);
        console.log(name);
        //发送ajax查询
        $dg.datagrid({
            url: '${pageContext.request.contextPath}/user/queryLike?type='+name+"&content="+value,
        });
    }
    //添加用户
    function addUser() {
        $dl.dialog({
            width: 600,
            height: 300,
            title: "添加用户",
            iconCls: "icon-man",
            href: '${pageContext.request.contextPath}/edit/userAdd.jsp',
            buttons: [{
                text: '提交',
                iconCls: 'icon-save',
                handler: addUserTO,
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: closeDa,
            }],
        });
    }
    //添加用户
    function addUserTO() {
        $("#addForm").form('submit', {
            url: '${pageContext.request.contextPath}/user/insert',
            success: function () {
                $dl.dialog('close', true);
                $dg.datagrid('reload');
            }
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="height: 80px;">
        <div style="text-align: center;margin: 10px 0px 10px 0px;">
            <input id="ss" class="easyui-searchbox" style="width:300px"
                   data-options="searcher:search,prompt:'模糊查询',menu:'#mm',searcher:search"/>
            <div id="mm" style="width:120px">
                <div data-options="name:'username',iconCls:'icon-ok'">用户名</div>
                <div data-options="name:'phone',iconCls:'icon-ok'">手机</div>
            </div>
        </div>
    </div>
    <div data-options="region:'center'">
        <table id="dataGrid"></table>
        <div id="dialog"></div>
        <div id="toolbars">
            <a onclick="addUser();" href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-add',plain:true">添加</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-page_excel',plain:true">导出到excel</a>
        </div>
    </div>
</div>