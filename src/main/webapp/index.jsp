<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>比价网站</title>


    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">



</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1>手机比价网 <small>天猫vs京东</small></h1><br>
        <form class="form-inline" align="center" action="/crawler2/compareInfo" method="post">
            <div class="form-group">
                <label class="control-label">请输入你要查询的手机：</label>
                <input name="searchStr" type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-primary">查询</button>
        </form>
    </div>

</div>



<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

<script src="static/js/indexJs.js"></script>
</body>
</html>