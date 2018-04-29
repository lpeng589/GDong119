<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>404</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
</head>
<body style="background-color:#F9F9F9">
<div>
  <div class="container-fluid" style="padding:0;height:100%;">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box" style="border:none">
          <div class="widget-content" style="border:none;padding-top:200px;">
            <div class="error_ex">
              <h1>导入成功</h1>
              <h3>${msg }<h3>
             <a class="btn btn-warning btn-big"  onclick="location='${url}'">返回</a> </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
