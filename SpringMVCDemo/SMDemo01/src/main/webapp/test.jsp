<%--
  Created by IntelliJ IDEA.
  User: AQvir
  Date: 2026/3/26
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%-- 方案1：CDN引入（推荐，零配置） --%>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <%-- 方案2：本地文件（确保路径正确） --%>
    <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script> --%>
    <script type="text/javascript">
        $(function(){
            $("#btn").click(function (){
                $.ajax({
                    url:"${pageContext.request.contextPath}/test10", // 给请求url也加根路径
                    contentType:"application/json; charset=utf-8",
                    data:'{"id":1,"name":"张三"}',
                    type:"post",
                    success:function (data) {
                        console.log(data);
                    }
                })
            })
        });
    </script>
</head>
<body>
<button type="button" id="btn" >ajax请求</button>
</body>
</html>