<html lang="en">
<head>
    <script language="JavaScript" src="../../static/js/jquery.js"></script>
    <script language="JavaScript" src="../../static/js/jquery.blockUI.js"></script>
    <script language="JavaScript" src="../../static/js/navbar.js"></script>
    <link rel="stylesheet" type="text/css" href="../../static/css/public.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假任务办理</title>
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
		                <td width="94%" valign="bottom"><span class="STYLE1">请假申请的任务办理</span></td>
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
		  	<td>
		  		<form action="workflowAction_submitTask" method="POST">
			  		<div align="left" class="STYLE21">
			  			<!-- 任务ID -->
			  			<input type="hidden" name="taskId"/>
			  			<!-- 请假单ID -->
			  			<input type="hidden" name="id"/>
				 		请假天数:<input type="text" name="days" disabled="true" cssStyle="width: 200px;"/><br/>
				 		请假原因:<input type="text" name="content" disabled="true" cssStyle="width: 800px;"/><br/>
				 		请假备注:<input type="text" name="remark" disabled="true" cols="30" rows="2"/><br/>
				 		批&emsp;&emsp;注:<input type="text" name="comment" cols="50" rows="5"/><br/>
				 		<!-- 使用连线的名称作为按钮 -->
						<#if outcomeList??(outcomeList?size>0)>
							<#list outcomeList as outcome>
				 				<input type="submit" name="outcome" value="<s:property/>" class="button_ok"/>
							</#list>
						</#if>
			 		</div>
			 	</form>
		  	</td>
		  </tr>
	</table>
	<hr>
	<br>
	<#if commentList??(commentList?size>0)>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr>
			    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="6%" height="19" valign="bottom"><div align="center"><img src="../../static/images/tb.gif" width="14" height="14" /></div></td>
			                <td width="94%" valign="bottom"><span class="STYLE1">显示请假申请的批注信息</span></td>
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
			        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">时间</span></div></td>
			        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">批注人</span></div></td>
			        <td width="75%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">批注信息</span></div></td>
			      </tr>
				  <#list commentList as comment>
			      	<tr>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center">${comment.date?string('yyyy-MM-dd HH:mm:ss')}</div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${comment.userId}</div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${comment.fullMessage}</div></td>
				    </tr> 
				  </#list>
			      
			    </table></td>
			  </tr>
		</table>
	<#else >
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr>
			    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td height="24" bgcolor="#F7F7F7"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="6%" height="19" valign="bottom"><div align="center"><img src="../../static/images/tb.gif" width="14" height="14" /></div></td>
			                <td width="94%" valign="bottom"><span><b>暂时没有批注信息</b></span></td>
			              </tr>
			            </table></td>
			          </tr>
			        </table></td>
			      </tr>
			    </table></td>
			  </tr>
		</table>
	</#if>
		<%--  --%>
</body>
</html>