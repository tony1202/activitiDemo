
<html lang="en">
<head>
    <script language="JavaScript" src="../../static/js/jquery.js"></script>
    <script language="JavaScript" src="../../static/js/jquery.blockUI.js"></script>
    <script language="JavaScript" src="../../static/js/navbar.js"></script>
    <link rel="stylesheet" type="text/css" href="../../static/css/public.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务管理</title>
</head>
<body>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="bottom"><div align="center"><img src="../../static/images/tb.gif" width="14" height="14" /></div></td>
		                <td width="94%" valign="bottom"><span class="STYLE1">个人任务管理列表</span></td>
		              </tr>
		            </table></td>
		            <td><div align="right"><span class="STYLE1">
		              </span></div></td>
		          </tr>
		        </table></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
		      <tr>
		        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">任务ID</span></div></td>
		        <td width="25%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">任务名称</span></div></td>
		        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">创建时间</span></div></td>
		        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">办理人</span></div></td>
		        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
		      </tr>
			  <#if list??&&(list?size>0)>
			  	<#list list as task>
		      		<tr>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center">${task.id}</div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${task.name}</div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${task.createTime?string('yyyy-MM-dd HH:mm:ss')}</div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${task.assignee}</div></td>
				        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
				        	<a href="workflowAction_viewTaskForm?taskId=${task.id}">办理任务</a>
							<a target="_blank" href="workflowAction_viewCurrentImage?taskId=${task.id}">查看当前流程图</a>
				        </div></td>
				    </tr> 
				</#list>
			  </#if>
		        
		      
		    </table></td>
		  </tr>
	</table>
</body>
</html>