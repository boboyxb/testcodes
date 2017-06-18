<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var $infoDG, $infoDL;
    $(function () {
        $infoDG = $("#infoDataGrid");
        $infoDL = $("#infoDialog");
        $infoDG.datagrid({
            url: '${pageContext.request.contextPath}/info/queryAll',
            width: '100%',
            height: '100%',
            fitColumns:true,
            columns: [[
                {title: "id", field: "id",  align: 'center'},
                {title: "评价星级", field: "score",  align: 'center'},
                {title: "评价时间", field: "createTime", align: 'center'},
                {title: "内容", field: "content", align: 'center'},
                {title: "评价者", field: "person", align: 'center',
                    formatter: function (value, row, index) {
                        return  row.user.name;
                    }},
                {title: "订单编号", field: "num", align: 'center',
                    formatter: function (value, row, index) {
                        return  row.order.num;
                    }},
            ]],
            pagination: true,
            pageNumber: 1,
            pageSize: 2,
            pageList: [2, 4, 6, 8, 10],
            toolbar: '#orderToolBars',
        });
    });
    //搜索的处理程序
    function searchInfo(value, name) {
        //发送ajax查询
        $infoDG.datagrid({
            url: '${pageContext.request.contextPath}/info/queryLike?type='+name+"&content="+value,
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="height: 50px;">
        <div style="text-align: center;margin: 10px 0px 10px 0px;">
            <input id="ssInfo" class="easyui-searchbox" style="width:300px"
                   data-options="prompt:'模糊查询',menu:'#mmInfo',searcher:searchInfo"/>
            <div id="mmInfo" style="width:120px">
                <div data-options="name:'content',iconCls:'icon-ok'">评论内容</div>
            </div>
        </div>
    </div>
    <div data-options="region:'center'">
        <table id="infoDataGrid"></table>
        <div id="infoDialog"></div>
    </div>
</div>