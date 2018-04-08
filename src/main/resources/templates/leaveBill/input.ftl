
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假管理</title>
</head>
<body>
 	<form action="leaveBillAction_save" method="POST">
 		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="bottom"><div align="center"><img src="../../static/images/tb.gif" width="14" height="14" /></div></td>
		                <td width="94%" valign="bottom">
		                	<span class="STYLE1">
		                			新增/修改请假申请
		                	</span>
		                	</td>
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
		    	<div align="left" class="STYLE21">
					<input name="id" type="hidden">
					<input name="user.id" type="hidden">
			 		请假天数:<input name="days" cssStyle="width: 200px;"/><br/>
			 		请假原因:<input name="content" cssStyle="width: 800px;"/><br/>
			 		备&emsp;&emsp;注:<input name="remark" cols="50" rows="5"/><br/>
			 		<input type="submit" value="提交" class="button_ok"/>
				</div>
		    </td>
		  </tr>
	</table>
	 	
	</form>
</body>
</html>