<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="uploadAlternateIMG" method="post" enctype="multipart/form-data">
        类别图片:<input class="easyui-filebox" name="typeIMG" style="width:300px">
        <input onclick="alternateUpUp()" type="button" value="上传" />
    </form>
    <script>
        //异步上传该图片
        function alternateUpUp() {
            $("#uploadAlternateIMG").form('submit',{
                url:'${pageContext.request.contextPath}/alternate/upload',
                success: function(data){
                    $("<img style='width:200px;height:100px;' src='${pageContext.request.contextPath}"+data+"?"+(new Date()).getTime()+"' />").appendTo("#alternateContainer");
                    $("#alternateImgPath").textbox('setValue',data);
                }
            })
        }
    </script>
    <div id="alternateContainer" style="width:100px;height: 100px">

    </div>

    <form id="addAlternate" method="post">
        <div style="margin-top: 10px;">
            图片路径:<input id="alternateImgPath" name="imagePath" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
    </form>
</div>


