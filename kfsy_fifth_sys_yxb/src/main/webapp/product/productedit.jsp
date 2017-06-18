<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="text-align: center;">
    <form id="editProductForm" method="post">
        <div style="margin-top: 5px;">
            药品id:<input  name="id" class="easyui-textbox" data-options="required:true,readonly:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            药品名称:<input  name="name" class="easyui-textbox" data-options="required:true,readonly:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            价格:<input  name="price" class="easyui-textbox" data-options="required:true,readonly:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            库存:<input  name="stock" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
    </form>
</div>
<script>
    $(function(){
        //构建子页面元素的操作
        $("#editProductForm").form('load','${pageContext.request.contextPath}/product/queryOne?id='+${param.id});
    });
</script>