<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var $userDG, $userDL;
    $(function () {
        $userDG = $("#userDataGrid");
        $userDL = $("#userDialog");
        $userDG.datagrid({
            url: '${pageContext.request.contextPath}/user/queryAll',
            width: '100%',
            height: '100%',
            columns: [[
                {title: "id", field: "id", align: 'center'},
                {title: "姓名", field: "name", align: 'center'},
                {title: "手机", field: "phone", align: 'center'},
                {title: "注册时间", field: "registDate", align: 'center'},
                {title: "状态", field: "status", align: 'center'},
                {
                    title: "操作", field: "options", width: 300, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class='del' onClick=\"delUser('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;";
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
            toolbar: '#userToolBars',
        });
    });
    //删除的操作
    function delUser(id) {
        $.messager.confirm("提示", "您确定要删除吗?", function (r) {
            if (r) {
                //发送异步请求删除数据
                $.post('${pageContext.request.contextPath}/user/delete', {"id": id}, function (msg) {
                    if(msg.success){
                        $.messager.show({
                            title: '删除提示',
                            msg: '删除成功',
                            timeout: '1000',
                        });
                        $userDG.datagrid('reload');
                    }else{
                        alert(msg.message);
                    }
                },"JSON")
            }
        });
    }
    //搜索的处理程序
    function searchUser(value, name) {
        //发送ajax查询
        $userDG.datagrid({
            url: '${pageContext.request.contextPath}/user/queryLike?type='+name+"&content="+value,
        });
    }

</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="height: 50px;">
        <div style="text-align: center;margin: 10px 0px 10px 0px;">
            <input id="ss" class="easyui-searchbox" style="width:300px"
                   data-options="prompt:'模糊查询',menu:'#mm',searcher:searchUser"/>
            <div id="mm" style="width:120px">
                <div data-options="name:'name',iconCls:'icon-ok'">用户名</div>
                <div data-options="name:'phone',iconCls:'icon-ok'">手机号</div>
            </div>
        </div>
    </div>
    <div data-options="region:'center'">
        <table id="userDataGrid"></table>
        <div id="userDialog"></div>
        <div id="userToolBars">
            <a href="${pageContext.request.contextPath}/user/down" class="easyui-linkbutton" data-options="iconCls:'icon-page_excel',plain:true">导出到excel</a>
        </div>
    </div>
</div>