<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="inputAdminForm" method="post">
        <div style="margin-top: 70px;">
            id:<input name="id" class="easyui-textbox" data-options="readonly:true"/>
        </div>
        <div style="margin-top: 70px;">
            管理员名:<input name="name" class="easyui-textbox" data-options="required:true,iconCls:'icon-man'"/><br/>
        </div>
        <div style="margin-top: 70px;">
            管理员密码:<input name="password" class="easyui-textbox" data-options="required:true,iconCls:'icon-man'"/><br/>
        </div>
        <div style="margin-top: 70px;">
            盐:<input name="salt" class="easyui-textbox" data-options="readonly:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
            管理员等级:<input  name="level" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
    </form>
</div>
<script>
    $(function(){
        //构建子页面元素的操作
        $("#inputAdminForm").form('load','${pageContext.request.contextPath}/admin/queryById?id='+${param.id});
    });
</script>