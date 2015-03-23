$(function(){
		var setting = {
				view: {
					selectedMulti: false
				},
				check: {
					enable: true
				},
				data: {
					key : {
						checked : 'permitted',
						url : null
					},
					simpleData: {
						pIdKey : 'parentId',
						enable: true
					}
				}
			};
	    var resources = JSON.parse($('#resources').html());
	    resources[0]['nocheck'] = true;
	    $.fn.zTree.init($("#resource_tree"), setting, resources);
	    var resource_tree = $.fn.zTree.getZTreeObj("resource_tree");
	    resource_tree.expandAll(true);
	    
	    $('#save').bind('click', function(event){
	    	var permission_resources = $.fn.zTree.getZTreeObj("resource_tree").getCheckedNodes(true);
	    	$.each(permission_resources, function(index, item) {
	    		$('form').append("<input name=\"resources\" type=\"hidden\" value=\"" + item.id + "\" />");
	    	});
	    	$.ajax({
	    		type : "POST",
	    		data:$('form').serialize(),
	    		error : function() {
	    			alert('出现错误，请稍后重试，或联系管理员.');
	    		},
	    		success : function() {
	    			alert('修改成功，重新登录后生效.');
	    			window.close();
	    		}
	    	});
	    	return false;
	    });
	});