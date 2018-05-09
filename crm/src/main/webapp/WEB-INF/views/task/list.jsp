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
   		<jsp:param value="task_list" name="param"/>
   	</jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">计划任务</h3>

                    <div class="box-tools pull-right">
                        <a href="/task/add" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增任务</a>
                        <c:choose>
                        	<c:when test="${param.show == 'undone'}">
                        		<a href="/task/list" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示所有任务</a>
                        	</c:when>
                        	<c:otherwise>
                        		<a href="/task/list?show=undone" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示未完成任务</a>	
                        	</c:otherwise>
                        </c:choose>
                        
                    </div>
                </div>
                <div class="box-body">

                    <ul class="todo-list">
                    	<c:forEach items="${taskList}" var="task">
                    		<li class="${task.status == 1 ? 'done' : '' }">
	                            <input type="checkbox" class="taskStatus" rel="${task.id}" ${task.status == 1 ? 'checked' : ''}>
	                            <span class="text">${task.title}</span>
	                            <small class="label ${task.overTime ? 'label-danger' : 'label-success' }"><i class="fa fa-clock-o"></i> ${task.finishTime}</small>
	                            <div class="tools">
	                                <i class="fa fa-edit edit_task"></i>
	                                <i class="fa fa-trash-o del_task" rel="${task.id}"></i>
	                            </div>
	                        </li>
                    	</c:forEach>
                        
                    </ul>
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
		$(".del_task").click(function () {
        	var taskId = $(this).attr("rel");
            layer.confirm("确定要删除么？", function(){
        		window.location.href = "/task/del?taskId=" + taskId;
            })
        });
		
		$(".taskStatus").click(function(){
			var taskId = $(this).attr("rel");
			var checked = $(this)[0].checked; // 判断是打勾（true）还是取消（false）
			var status = 0;
			if(checked) {
				// 把状态改成已完成
				status = 1;
			} else {
				// 把状态改成未完成
				status = 0;
			}
			
			$.get("/task/status",{"taskId":taskId, "status": status}, function(data){
				if(data.state == 'success') {
					layer.msg("修改成功");
					// 刷新当前页面
					history.go(0);
				}
			});
		})
		
	})
</script>
</body>
</html>
    