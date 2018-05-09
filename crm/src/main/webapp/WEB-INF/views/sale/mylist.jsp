<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp"%>
   	<jsp:include page="../include/sider.jsp">
   		<jsp:param value="work_record_my" name="param"/>
   	</jsp:include>
   
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">我的销售机会</h3>

                    <div class="box-tools pull-right">
                        <button type="button" id="addChance" class="btn btn-box-tool">
                            <i class="fa fa-plus"></i> 添加机会
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <td>机会名称</td>
                                <td>关联客户</td>
                                <td>机会价值</td>
                                <td>当前进度</td>
                                <td>最后跟进时间</td>
                                
                            </tr>
                            <c:forEach items="${page.items}" var="chance">
		                       	 <tr class="datarow" rel="${chance.id}">
			                       	<td>${chance.name}</td>
	                                <td>${chance.customer.custName}</td>
	                                <td><fmt:formatNumber value="${chance.worth}"></fmt:formatNumber></td>
	                                <td>${chance.process}</td>
	                                <td><fmt:formatDate value="${chance.lastTime}"  pattern='yyyy年MM月dd日'></fmt:formatDate></td>
                                 </tr>
                                 
                            </c:forEach>
                            
                        </thead>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
 <%@ include file="../include/footer.jsp"%>
</div>
<!-- ./wrapper -->

 <%@ include file="../include/js.jsp"%>
 <script>
	$(function(){
		$("#addChance").click(function(){
			window.location.href="/sale/add";
		});
		
		$(".datarow").click(function(){
			var id = $(this).attr("rel");
			window.location.href="/sale/detail?saleId="+id;
		});
		
	}) 
 </script>
 	
 
</body>
</html>
    