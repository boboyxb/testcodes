<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/IconExtension.css" />

    <script src="${pageContext.request.contextPath}/easyui/js/jquery.min.js" ></script>
    <script src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js" ></script>
    <script src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js" ></script>
    <script src="${pageContext.request.contextPath}/easyui/js/form.validator.rules.js" ></script>
    <script src="${pageContext.request.contextPath}/echar/echarts.js" ></script>
    <script>
        var $aa,$tt;
        $(function(){
            $aa = $("#menus");
            $tt = $("#tt");
            //初始化系统菜单
            $.post('${pageContext.request.contextPath}/menu/queryMenus',function(menus){
                $.each(menus,function(i,menu){
                    var content = "<div style='text-align: center;'>";
                    $.each(menu.menus,function(j,child){
                        content +="<div onclick=\"addTabs('"+child.name+"','"+child.iconCls+"','"+child.href+"')\" class='easyui-linkbutton' data-options=\"plain:true,iconCls:'"+child.iconCls+"'\" style='border:1px solid green; width:90%;margin: 5 0 5 0 ;'>"+child.name+"</div>";
                    })
                    content +="</div>";
                    $aa.accordion('add',{
                        title:menu.name,
                        iconCls:menu.iconCls,
                        content:content,
                    });
                });
            },"JSON");
        })
        //添加选项卡
        function  addTabs(title,iconCls,href){
            if(!$tt.tabs('exists',title)){
                if(title=='管理员列表'){
                    if("${sessionScope.admin.level}"==1){
                        $tt.tabs('add',{
                            title:title,
                            iconCls:iconCls,
                            href:"${pageContext.request.contextPath}"+href,
                            closable:true,
                        });
                    }else{
                        alert("您不是1级管理员,无法使用此功能");
                    }
                }else{
                    $tt.tabs('add',{
                        title:title,
                        iconCls:iconCls,
                        href:"${pageContext.request.contextPath}"+href,
                        closable:true,
                    });
                }

            }else{
                $tt.tabs('select',title)
            }
        }
    </script>
</head>
<body class="easyui-layout" >
<div data-options="region:'west',title:'菜单栏',split:false" style="width:200px;">
    <div id="menus" class="easyui-accordion" data-options="fit:true"></div>
</div>
<div data-options="region:'center',title:'快方送药后台管理系统',iconCls:'icon-house'" style="padding:5px;background:#eee;">
    <div id="tt" class="easyui-tabs" data-options="fit:true"></div>
</div>
</body>
</html>
