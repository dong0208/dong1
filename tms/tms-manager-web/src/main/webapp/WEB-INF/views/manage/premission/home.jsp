<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-系统管理-权限管理</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
               权限管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">权限列表</h3>
                    <div class="box-tools">
                        <a href="/manage/premission/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增权限</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table tree">
                        <thead>
                            <tr>
                                <th>权限名称</th>
                                <th>权限代号</th>
                                <th>资源URL</th>
                                <th>类型</th>
                                <th>#</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${premissionList}" var="premission">
                                <c:choose>
                                    <c:when test="${premission.parentId == 0}">
                                        <tr>
                                            <td>${premission.premissionName}</td>
                                            <td>${premission.premissionCode}</td>
                                            <td>${premission.premissionUrl}</td>
                                            <td>${premission.premissionType}</td>

                                            <td>
                                                <a href="">修改</a>
                                                <a href="">删除</a>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="treegrid-${premission.id} treegrid-expanded treegrid-parent-${premission.parentId}">
                                        <tr>
                                            <td>${premission.premissionName}</td>
                                            <td>${premission.premissionCode}</td>
                                            <td>${premission.premissionUrl}</td>
                                            <td>${premission.premissionType}</td>
                                            <td>
                                                <a href="">修改</a>
                                                <a href="">删除</a>
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script>
    $(function () {
        $('.tree').treegrid();
    });
</script>
</body>
</html>
