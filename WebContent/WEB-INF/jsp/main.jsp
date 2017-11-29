<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="common/common.jsp"%>
        <title>Spring论坛</title>
    </head>
    <body>
        ${user.userName},欢迎您进入Spring论坛，您当前积分为 ${user.credits};
        <form method="post" id="realImageForm"  target="realImageIframe" enctype="multipart/form-data" action="<%=request.getContextPath()%>/common/fileUpload">
			<input id="image_storeName" name="storeMess"  class="text" type="hidden" value="storeId"/><!-- 商铺名称作为文件夹名称 -->
			<input type="file" class="text" name="image"/>
			<input type="hidden" id="edit_corporateIdcardBack" name="corporateIdcardBack" class="path"> <!-- 数据库保存地址 -->
			<input type="button" class="realImage_submit btn btn-xs spinner-up btn-success upload" value="上传" disabled><!--上传按钮-->
		</form>
        
        
        <script type="text/javascript">
      //图片上传
    	$('.realImage_submit').click(function(){
    		$(this).parent().find('#image_storeName').val('ceshi');
    		var btnObj = $(this);
    		var $realPathobj = $(this).parent().find('.path')[0]; 
    		var options = {
    			url : "<%=request.getContextPath()%>/common/fileUpload",
    			type : "post",
    			dataType:"json", 
    			processData: false,
    	        contentType: false, 
    			success : function(d) {
    				if(d&&d.respCode == '0000'){
    					$realPathobj.value = d.picAddr; 
    					btnObj.val("上传成功")
    				}else{
    					showMessage("图片上传失败");
    				}
    			},
    			error:function(){
    				showMessage("图片上传失败");
    			}
    		};
    		$(this).parent('#realImageForm').ajaxSubmit(options);
    	});
        </script>
    </body>
</html>