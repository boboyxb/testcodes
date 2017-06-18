<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function(){
        //构建子页面元素的操作
        $.post('${pageContext.request.contextPath}/product/queryOne',{id:${param.id}},function (data) {
            console.log(data);
            $("#name").val(data.name);
            $("#unit").val(data.medicine.unit);
            $("#nationalDrugName").val(data.medicine.nationalDrugName);
            $("#ingredient").val(data.medicine.ingredient);
            $("#approvaldateOfNationalDrugName").val(data.medicine.approvaldateOfNationalDrugName);
            $("#description").val(data.medicine.description);
            $("#dosageAdministration").val(data.medicine.dosageAdministration);
            $("#sideEffects").val(data.medicine.sideEffects);
            $("#contraindications").val(data.medicine.contraindications);
            $("#precaution").val(data.medicine.precaution);
            $("#interaction").val(data.medicine.interaction);
            $("#depot").val(data.medicine.depot);
            $("#packing").val(data.medicine.packing);
            $("#expiryDate").val(data.medicine.expiryDate);
            $("#company").val(data.medicine.company);
        },"json");
    });
</script>
<div style="text-align: center;">
        <div style="margin-top: 5px;">
            药品名称:<input id="name" /><br/>
        </div>
        <div style="margin-top: 5px;">
            单位:<input  id="unit"/><br/>
        </div>
        <div style="margin-top: 5px;">
            国药准字号:<input id="nationalDrugName"/><br/>
        </div>
        <div style="margin-top: 5px;">
            国药准字号批准日期:<input id="approvaldateOfNationalDrugName"/><br/>
        </div>
        <div style="margin-top: 5px;">
            明书成分:<input id="ingredient"/><br/>
        </div>
        <div style="margin-top: 5px;">
            性状:<input id="description"/><br/>
        </div>
        <div style="margin-top: 5px;">
            用法和用量:<input id="dosageAdministration"/><br/>
        </div>
        <div style="margin-top: 5px;">
            不良反应:<input id="sideEffects"/><br/>
        </div>
        <div style="margin-top: 5px;">
            禁忌:<input  id="contraindications"/><br/>
        </div>
        <div style="margin-top: 5px;">
            注意事项:<input id="precaution"/><br/>
        </div>
        <div style="margin-top: 5px;">
            药物相互作用:<input id="interaction"/><br/>
        </div>
        <div style="margin-top: 5px;">
            贮藏:<input  id="depot"/><br/>
        </div>
        <div style="margin-top: 5px;">
            包装:<input id="packing"/><br/>
        </div>
        <div style="margin-top: 5px;">
            有效期:<input  id="expiryDate"/><br/>
        </div>
        <div style="margin-top: 5px;">
            生产企业:<input id="company"/><br/>
        </div>
</div>
