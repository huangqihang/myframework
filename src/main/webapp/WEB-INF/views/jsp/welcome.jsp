<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="dwr.engine.setActiveReverseAjax(true);">
	<h1>Java Chat - DWR</h1>

	<p>This is a very simple chat demo that uses reverse ajax to
		collect messages and server-side browser manipulation to update the
		pages with the results.</p>

	<p>
		Your Message: <input id="text"
			onkeypress="dwr.util.onReturn(event, sendMessage)" /> <input
			type="button" value="Send" onclick="sendMessage()" />
	</p>
	<hr />

	<ul id="chatlog" style="list-style-type: none;">
	</ul>

<script type='text/javascript' src='dwr/engine.js'> </script>
<script type='text/javascript' src='dwr/interface/JavaChat.js'> </script>
<script type='text/javascript' src='dwr/util.js'> </script>

<script type="text/javascript">
    function sendMessage() {
      JavaChat.addMessage(dwr.util.getValue("text"));
    }
</script>

</body>

</html>