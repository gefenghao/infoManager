<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="easyui-1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui-1.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui-1.5/demo.css">
    <script type="text/javascript" src="easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="easyui-1.5/jquery.min.js"></script>
	<script type="text/javascript" src="easyui-1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</head>
<body  class="easyui-layout" style="width:99%;height:98%">
		<div data-options="region:'center',title:'主页'" >
		    <div class="easyui-tabs" style="width:99.98%;height:99.98%">
		       <div class="easyui-tab" data-options="title:'上传数据',fit:true" style="width:99.98%;height:99.98%">
	                <div class="easyui-panel" style="width:99.98%;height:98%">
						<form id="ff" enctype="multipart/form-data" method="post" action="excel/readExcel.do" style="align:center;padding:99.98px">
							<div style="margin-bottom:20px;align:center">
							    <input type="hidden" name="ignoreRows" value="1"/>
								<input class="easyui-textbox" name="filepath" id="filepath" style="width:50%;align:center" data-options="label:'上传文件地址:',required:true,width:'150px'">
								<div style="display:inline-block">
								    <input type="file" name="file" id="file" style="opacity:0;z-index:1000"/>
							    </div>
								<div style="display:inline-block;z-index:900;margin-left:-230px;">
									<a class="easyui-linkbutton" id="choooseFile">请选择</a>
								</div>
							</div>
							<div style="margin-bottom:20px;padding-top:50px">
								<a id="save" href="#" class="easyui-linkbutton" iconCls="icon-save" style="width:150px;height:40px">提 &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交1111</a>
							</div>
						</form>
					</div>
		       </div>
		       <div class="easyui-tab" data-options="title:'查询推广数据',fit:true" style="width:99.98%;height:99.98%">
		      
		           <div class="easyui-layout" data-optoins="fit:true" style="width:99.98%;height:99.98%">
		               <div data-options="region:'north'"  style="height:200px;width:99.98%">
		            	<div class="easyui-panel" style="width:99.98%;height:99.98%">
							<form id="ff2" method="post" style="align:center;padding:20px;">
								<div style="padding:10px 10px;align:center;width:46%;float:left">
									<input class="easyui-textbox" name="name" id="name" style="width:50%;align:center" data-options="label:'姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：',required:true,width:'150px'">
								</div>
								<div style="padding:10px 10px;align:center;width:46%;float:left">
									<input class="easyui-textbox" name="addr" id="addr" style="width:50%;align:center" data-options="label:'地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：',required:true,width:'150px'">
								</div>
								<div style="padding:10px 10px;align:center;width:46%;float:left">
									<select class="easyui-combobox" name="province" id="province" data-options="label:'省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：',required:true,width:'340px'">
									</select>
								</div>
								
								<div style="padding:10px 10px;align:center;width:46%;float:left">
									<select id="city" class="easyui-combobox" name="city"  data-options="label:'市&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：',required:true,width:'340px'">   
									          
									</select>  
								</div>
								
								<div style="padding:10px 10px;align:center;width:46%;float:left">
									<input class="easyui-datebox" style="width:220px" name="startDate" id="startDate" style="width:50%;align:center" data-options="label:'交易日期:',required:true">
									至
									<input class="easyui-datebox" style="width:130px" name="endDate" id="endDate" style="width:50%;align:center" data-options="required:true,width:'70px'">
								</div>
								
								<div style="padding:10px 10px;align:center;float:left;">
									<a id="save2" href="#" class="easyui-linkbutton" iconCls="icon-save" style="width:120px;height:30px">查&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;询</a>
									<a id="save3" href="#" class="easyui-linkbutton" iconCls="icon-save" style="width:120px;height:30px;margin-left:20px;">统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计</a>
									 
									<div id="queryTotalAmount" style="float:right;padding-left:10px;margin-top:5px;display:hidden"><label>统计总额：</label><span id="total"></span></div>
								</div>
							</form>
						</div>
		           </div>
		               <div data-options="region:'center',fit:false" style="width:70%">
		               	<table class="easyui-datagrid"
									data-options="method:'get',border:false,singleSelect:true,
									fit:true,fitColumns:false" id="recordGrid">
						  <thead>
							<tr>
								<th data-options="field:'id'" style="width:10%">ID</th>
								<th data-options="field:'name'" style="width:10%">姓名</th>
								<th data-options="field:'amount',align:'center'"  style="width:10%">金额</th>
								<th data-options="field:'addr',align:'left'" style="width:30%">地址</th>
								<th data-options="field:'tradeDate'"  style="width:20%">日期</th>
								<th data-options="field:'province',align:'center'" style="width:10%">省份</th>
								<th data-options="field:'city',align:'center'" style="width:10%">市区</th>
							</tr>
						 </thead>
					   </table>
		           </div>
		           </div>
		       </div>
		</div>
</body>
</html>
