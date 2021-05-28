<#import "/spring.ftl" as spring/>
<#macro page><!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>SarFood enjoy</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://yevgenysim.github.io/shopper/assets/fonts/feather/feather.css">
<link rel="stylesheet" href="http://astronautweb.co/wp-content/themes/astronaut-2014/css/font-awesome-4.7.0.css">
<link rel="stylesheet" href="/static/css/app.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/0.8.2/css/flag-icon.min.css" rel="stylesheet"/>
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
</head>

<body>
	<div class="wrapper">
		<#include "navbar.ftl">
		<div class="container text-center" style="padding-bottom: 6rem;">
			<#nested>
		</div>
		<#include "footer.ftl">
	</div>
</body>
	<script src="/static/js/app.js"></script>
	<script src="/static/js/jquery.maskedinput.js"></script>
	<script src="/static/js/postrequest.js"></script> 
	
	<script type="text/javascript">
			$(function() {
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				$(document).ajaxSend(function(e, xhr, options) {
					xhr.setRequestHeader(header, token);
				});
			});
		</script>
</html>
</#macro>