<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>比价网站</title>
    <%
        //        获得的APP_PATH以斜线开始，不以斜线结束
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>

    <style>
        span.phone-title {
            color: black;
        }

        div.phone-price {
            color: red;
            font-size: 1.2em;
        }
    </style>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">
                            <img src="${APP_PATH}/static/image/brand.png" alt="Brand" class="img-rounded"
                                 height="35" width="35">
                        </a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="active" id="website-TM"><a href="#">天猫</a></li>
                            <li id="website-JD"><a href="#">京东</a></li>
                        </ul>
                        <form class="navbar-form navbar-right" action="/crawler2/compareInfo" method="post">
                            <div class="form-group">
                                <input type="text" name="searchStr" class="form-control" placeholder="Search" id="search-input">
                            </div>
                            <button type="submit" class="btn btn-primary">查询</button>
                        </form>
                    </div><!-- /.navbar-collapse -->
                </div>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="page-header">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr class="warning">
                        <td width="20%"></td>
                        <c:forEach var="compareInfo" items="${compareInfoList}">
                            <td>${compareInfo.websiteName}</td>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>最高价格</td>
                        <c:forEach var="compareInfo" items="${compareInfoList}">
                            <td>￥<fmt:formatNumber type="number" value="${compareInfo.maxPrice}" maxFractionDigits="2"/></td>
                        </c:forEach>
                    </tr>
                    <tr class="warning">
                        <td>最低价格</td>
                        <c:forEach var="compareInfo" items="${compareInfoList}">
                            <td>￥<fmt:formatNumber type="number" value="${compareInfo.minPrice}" maxFractionDigits="2"/></td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>平均价格</td>
                        <c:forEach var="compareInfo" items="${compareInfoList}">
                            <td>￥<fmt:formatNumber type="number" value="${compareInfo.averagePrice}" maxFractionDigits="2"/></td>
                        </c:forEach>
                    </tr>
                    <tr class="warning">
                        <td>方差</td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default" id="show-panel">
            </div>
        </div>
    </div>
</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#search-input").val("${searchStr}");
    });
</script>
<script src="${APP_PATH}/static/js/mainJs.js" charset="utf-8"></script>
</body>
</html>