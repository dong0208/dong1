<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
    	<jsp:param value="customer_my" name="param"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">修改客户</h3>
                    <div class="box-tools pull-right">
                        <a class="btn btn-primary btn-sm" href="/customer/my/detail?id=${customer.id}"><i class="fa fa-arrow-left"></i> 返回列表</a>
                    </div>
                </div>
                <div class="box-body">
                    <form action="" id="editForm">
                        <div class="form-group">
                            <label>请输入原始密码</label>
                            <input type="hidden" name="accountId" value="${account.id}"/>
                            <input type="password" class="form-control" value=""  name="oldPassword">
                        </div>
                        <div class="form-group">
                            <label>请输入新密码</label>
                            <input type="password" class="form-control" value=""  name="newPassword">
                        </div>
                        <div class="form-group">
                            <label>修改联系方式</label>
                            <input type="text" class="form-control" value="${account. mobile}"  name="mobile">
                        </div>
                       
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary" id="editBtn">保存</button>
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
		var accountId = ${account.id};
		
 		$("#editBtn").click(function(){
 			$("#editForm").submit();
 		});
 		
 		$("#editForm").validate({
 			errorClass : 'text-danger',
			errorElement : 'span',
			rules : {
				oldPassword :{
					"required" : true
				},
				newPassword : {
					"required" : true
				}
			},
			messages :{
				oldPassword :{
					"required" : "请输入原始密码"
				},
				newPassword : {
					"required" : "请输入新密码"
				}
			},
			submitHandler : function(form){
				$.ajax({
					url:'/account/change/password',
					type:'post',
					data:$("#editForm").serialize(),
					beforeSend : function(){
						$("#editBtn").text("保存中...").attr("disabled","disabled");
					},
					success : function(data){
							window.location.href="/login";
					},
					error : function(){
						alert("系统异常");
					},
					complete : function(){
						$("#editBtn").text("保存").removeAttr("disabled");
					}
				});
				
			}
 		
 		});
 		
 	})
	
</script>

</body>
</html>