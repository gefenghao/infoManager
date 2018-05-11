$(function(){
	//查看
	$.prototype.serializeObject = function() {  
	    var a, o, h, i, e;  
	    a = this.serializeArray();  
	    o = {};  
	    h = o.hasOwnProperty;  
	    for (i = 0; i < a.length; i++) {  
	        e = a[i];  
	        if (!h.call(o, e.name)) {  
	            o[e.name] = e.value;  
	        }  
	    }  
	    return o;  
	};
	$("#ff").form({
		 onSubmit: function(){    
		        debugger;    
		 },    
		 success:function(res){
			var res1=eval("("+res+")")
     		if(res1.result=="01"){
     			$.messager.alert('提示','上传成功');	
     		}else{
     			$.messager.alert('警告','上传失败');	
     		}
     		
     	},
     	error:function(result){
     		$.messager.alert('警告','上传失败');	
     	}  
	});
	$('#save').bind('click', function(){  
		$("#ff").form("submit");
//        $.ajax({
//        	url:"excel/readExcel.do?",
//        	type:'get',
//        	dataType:'json',
//        	data:{
//        		filepath:$("#filepath").val(),
//        		ignoreRows:1
//        	},
//        	success:function(res){
//        		if(res.result=="01"){
//        			$.messager.alert('提示','上传成功');	
//        		}else{
//        			$.messager.alert('警告','上传失败');	
//        		}
//        		
//        	},
//        	error:function(result){
//        		$.messager.alert('警告','上传失败');	
//        	}
//        }); 
    });
	var opts={
			url:"iRecord/listIRecord.do?",
			pagePosition:"bottom",
			pageNumber:'1',
			pageSize:20,
			pageList:[10,20,50],
			queryParams:{
				name:"",
				addr:"",
				tradeDate:"",
				sfSd:""
			},
		   pagination:true
		}
	Date.prototype.format = function(fmt) { 
	     var o = { 
	        "M+" : this.getMonth()+1,                 //月份
	        "d+" : this.getDate(),                    //日
	        "h+" : this.getHours(),                   //小时
	        "m+" : this.getMinutes(),                 //分
	        "s+" : this.getSeconds(),                 //秒
	        "q+" : Math.floor((this.getMonth()+3)/3), //季度
	        "S"  : this.getMilliseconds()             //毫秒
	    }; 
	    if(/(y+)/.test(fmt)) {
	            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	    }
	     for(var k in o) {
	        if(new RegExp("("+ k +")").test(fmt)){
	             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	         }
	     }
	    return fmt; 
	}  
	$("#recordGrid").datagrid(opts);
//	$("#recordGrid").datagrid("getColumnOption","tradeDate").formatter=function(value,row,index){
//		var d=new Date();
//		d.setTime(value)
//		return d.format("yyyy-MM-dd");
//	}
	//查询按钮事件
	//交易日期
	$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+m+"-"+'dd';
	}
	$("#save2").bind("click",function(){
		var formData=$("#ff2").serializeObject();
		
		var opts={
				url:"iRecord/listIRecord.do?",
				pagePosition:"bottom",
				pageNumber:'1',
				pageSize:20,
				pageList:[10,20,50],
				queryParams:formData,
			    pagination:true
			}
		$("#recordGrid").datagrid(opts);
	});
	$.ajax({
		url:"iRecord/queryProvince.do",
		dataType:"json",
		success:function(data){
			var arr=[]
			for(var i in data){
				arr.push({province:data[i]});
			}
			$("#province").combobox({
			    data:arr,
				valueField: 'province',
				textField: 'province',
				onSelect:function(record){
					$("#city").combobox('clear');
					$.ajax({
						url:"iRecord/queryCity.do?province="+record.province,
						dataType:"json",
						success:function(data){
							var arr=[]
							for(var i in data){
								arr.push({city:data[i]});
							}
							$("#city").combobox({
								data:arr
							})
						}
					});
				}
			});
		}
	})
	
	$("#city").combobox({
		valueField: 'city',
		textField: 'city',
		onSelect:function(record){
	    
		}
	});
	$("#queryTotalAmount").hide();
	//点击统计事件
	$("#save3").bind("click",function(){
		var formData=$("#ff2").serializeObject();
	
		$.ajax({
        	url:"iRecord/queryTotalAmount.do?",
        	type:'get',
        	dataType:'json',
        	data:formData,
        	success:function(res){
        		$("#queryTotalAmount").show();
        		res["total"]?null:res["total"]=0.0;
        		$("#total").text(res["total"]);
        	},
        	error:function(result){
        		$.messager.alert('警告','统计失败');	
        	}
        }); 
	});
	$("#file").bind("change",function(){
		$("#filepath").textbox("setValue",$("#file").val())
	})
	$("#choooseFile").bind("click",function(){
		$("#file").trigger("click");
	});
	

})