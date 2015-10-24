<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传页面</title>
</head>
<body>
	<h1>文件上传页面</h1>
	<form id="excelForm" action="<%=request.getContextPath()%>/basic/multiUpload.htm" method="post" enctype="multipart/form-data" >
		<input type="file" name="fileName" id="fileName"/><br/>
		<input type="file" name="fileName" id="fileName"/><br/>
		<input type="submit" name="submitBtn" value="点击提交"/>
	</form>
</body>

<script>
$(function(){
	$("input[type=file]").change(function(){$(this).parents(".uploader").find(".filename").val($(this).val());});
	$("input[type=file]").each(function(){
	if($(this).val()==""){
		$(this).parents(".uploader").find(".filename").val("No file selected...");}
	});

	$("input[name='fileUploadBtn']").click(function(){
		var excelFileName = document.getElementById("fileName").value;
		
		alert(excelFileName);

		if(excelFileName.length==0){
			alert("亲,请您选择一个非空文件上传吧！！！");
			return;
		}
		
		if(excelFileName.lastIndexOf(".xlsx")>-1 || excelFileName.lastIndexOf(".xls")>-1){
			$("#excelForm").submit();
		}else{
			alert("亲,请您仔细检查你的文件类型是不是以xlsx结尾！！！");
		}
	});
});

</script>


</html>