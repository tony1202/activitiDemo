<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前流程图</title>
</head>
<body>
<!-- 1.获取到规则流程图 -->
<img style="position: absolute;top: 0px;left: 0px;" src="workflowAction_viewImage?deploymentId=${workflowBean.deploymentId}&imageName=${workflowBean.imageName}/>">

<!-- 2.根据当前活动的坐标，动态绘制DIV -->
<div style="position: absolute;border:1px solid red;top:${acs["Y"]}px;left: ${acs["X"]}px;width: ${acs["width"]}px;height:${acs["height"]}px;">
</div>
</body>
</html>