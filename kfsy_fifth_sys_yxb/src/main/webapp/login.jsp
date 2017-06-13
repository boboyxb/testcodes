
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/IconExtension.css" />
    <script src="${pageContext.request.contextPath}/easyui/js/jquery.min.js" ></script>
    <script src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js" ></script>
    <script src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js" ></script>
    <script src="${pageContext.request.contextPath}/easyui/js/form.validator.rules.js" ></script>
    <script>
        var $ff;
        $(function(){
            $ff=$("#ff");
        })
        function submitff(){
            $.messager.progress();
            $ff.form('submit', {
                    url:"${pageContext.request.contextPath}/admin/login",
                    onSubmit: function(){
                         var isValid = $(this).form('validate');
                         if (!isValid){
                         $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                         }
                        return isValid;	// 返回false终止表单提交
                    },
                    success: function(data){
                        var data = eval('(' + data + ')');
                        $.messager.progress('close');	// 如果提交成功则隐藏进度条
                        if (!data.success){
                            alert(data.message);
                        }else{
                            location.href="${pageContext.request.contextPath}/main/main.jsp";
                        }
                    }
        });

        }
    </script>
</head>
<body style="background-image: url('${pageContext.request.contextPath}/images/kuaifang_login.jpg');background-size: cover" >
    <div id="win" style="
        background: rgba(248,247,246,0.7);
        width: 300px;
        height:250px;
        margin-top:13%;
        margin-left:38%;
        vertical-align:middle;
        text-align: center;
        padding-top: 3%;
        padding-left: 3%;
        padding-right: 3%;
       ">
        <form id="ff" method="post">
            <div>
                用户名: <input class="easyui-textbox" data-options="iconCls:'icon-man'" style="width:200px;margin:5px;height:34px;padding:10px" name="name" />
            </div>
            <div>
                密码: <input class="easyui-passwordbox" prompt="Password" iconWidth="28" style="width:200px;margin:5px;height:34px;padding:10px" name="password">
            </div>
            <input type="button" onclick="submitff()" value="登陆" />
            <input type="reset" value="重置" />
        </form>
    </div>
</body>
</html>
