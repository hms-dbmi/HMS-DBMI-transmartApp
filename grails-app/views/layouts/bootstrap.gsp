<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title><g:layoutTitle default="i2b2 tranSMART" /></title>

<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:400,100,300,500" />
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<style>
body {
	/*min-height: 2000px;*/
	padding-top: 70px;
}

.navbar-default {
	background-color: #A41034;
	border-color: #A41034;
}

.navbar-default .navbar-nav > li > a {
    color: white;
}

</style>
<g:layoutHead />
</head>
<body>

	<g:layoutBody />

    <script src="//code.jquery.com/jquery-2.1.3.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
	<script src="${resource(dir:'/assets/js',file:'jquery.backstretch.min.js')}"></script>
	<script src="${resource(dir:'/assets/js',file:'retina-1.1.0.min.js')}"></script>

	<script>
    $(document).ready(function() {
        $.backstretch('${resource(dir:'/assets/img/backgrounds',file:'1.jpg')}');
    });
    </script>
    
    <g:pageProperty name="page.javascript"/>

</body>
</html>
