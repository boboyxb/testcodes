<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="uploadProductIMG" method="post" enctype="multipart/form-data">
        缩略图:<input class="easyui-filebox" name="typeIMG" style="width:300px">
        <input onclick="productUpUp()" type="button" value="上传" />
    </form>
    <form id="uploadProductIMGs" method="post" enctype="multipart/form-data">
        详细图:<input name="typeIMGs" class="easyui-filebox" data-options="multiple:true" style="width:300px">
        <input onclick="uploadUps()" type="button" value="上传" />
    </form>
    <script>
        //异步上传缩略图
        function productUpUp() {
            $("#uploadProductIMG").form('submit',{
                url:'${pageContext.request.contextPath}/product/upload',
                success: function(data){
                    $("<img style='width:200px;height:100px;' src='${pageContext.request.contextPath}"+data+"?"+(new Date()).getTime()+"' />").appendTo("#productContainer");
                    $("#productImgPath").textbox('setValue',data);
                }
            })
        }
        //异步上传多张详细图片
        function uploadUps(){
            $("#uploadProductIMGs").form('submit',{
                url:'${pageContext.request.contextPath}/product/uploadFiles',
                success: function(data){
                    console.log(data);
                    $("#paths").textbox('setValue',data);
                },
            })
        }

    </script>
    <div id="productContainer" style="width:100px;height: 100px"></div>
    <form id="addProduct" method="post">
        <div style="margin-top: 5px;">
            缩略图路径:<input id="productImgPath" name="imagePath" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            多图路径:<input id="paths" name="paths" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            药品名称:<input  name="name" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            功能主治:<input  name="indication" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            规格:<input  name="standard" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            价格:<input  name="price" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            库存:<input  name="stock" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            种类:<select id="productTypes" name="type.id"></select>
        </div>
        <div style="margin-top: 5px;">
            单位:<input  name="medicine.unit" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            国药准字号:<input  name="medicine.nationalDrugName" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            国药准字号批准日期:<input  name="medicine.approvaldateOfNationalDrugName" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            明书成分:<input  name="medicine.ingredient" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            性状:<input  name="medicine.description" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            用法和用量:<input  name="medicine.dosageAdministration" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            不良反应:<input  name="medicine.sideEffects" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            禁忌:<input  name="medicine.contraindications" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            注意事项:<input  name="medicine.precaution" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            药物相互作用:<input  name="medicine.interaction" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            贮藏:<input  name="medicine.depot" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            包装:<input  name="medicine.packing" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            有效期:<input  name="medicine.expiryDate" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 5px;">
            生产企业:<input  name="medicine.company" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
    </form>
</div>
<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/type/queryAllTypes",{},function(result){
            $.each(result,function(i,type){
                var option=("<option value="+type.id+">"+type.name+"</option>");
                $("#productTypes").append(option);
            });
        },"JSON");
    })
</script>


