<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="uploadTypeIMG" method="post" enctype="multipart/form-data">
        类别图片:<input class="easyui-filebox" name="typeIMG" style="width:300px">
        <input onclick="typeUpUp()" type="button" value="上传" />
    </form>
    <script>
        //异步上传该图片
        function typeUpUp() {
            $("#uploadTypeIMG").form('submit',{
                url:'${pageContext.request.contextPath}/type/upload',
                success: function(data){
                    $("<img style='width:80px;height:80px;' src='${pageContext.request.contextPath}"+data+"?"+(new Date()).getTime()+"' />").appendTo("#containerType");
                    $("#imgTypePath").textbox('setValue',data);
                }
            })
        }
    </script>
    <div id="containerType" style="width:100px;height: 100px">

    </div>

    <form id="addType" method="post">
        <div style="margin-top: 70px;">
            类别名称:<input name="name" class="easyui-textbox" data-options="required:true,iconCls:'tag_orange'"/><br/>
        </div>
        <div style="margin-top: 10px;">
            图片路径:<input id="imgTypePath" name="href" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
    </form>
</div>


