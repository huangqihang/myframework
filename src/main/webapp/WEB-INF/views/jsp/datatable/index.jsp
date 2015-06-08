<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"
    import="app.model.*,app.repository.*"%>

<%@ include file="/common/taglib.jsp" %>

<html>
    <head>
    	<%@ include file="/common/header.jsp" %>
    	
        <title>Applying JQuery DataTables plugin in the Java Server application</title>

        <script type="text/javascript">
        $(document).ready(function () {
        	$(document).ready(function () {
                $("#companies").dataTable({
                    "sPaginationType": "full_numbers",
                    "bJQueryUI": true
                });
            });
        });
        </script>
    </head>
    <body id="dt_example">
        <div id="container">
        	<div id="links">
        	    Client side processing | <a href="/data/plugins">Client side processing with additional plugins</a><br/>
        		<a href="/data/indexHtml">Server-side processing with matrix source</a> | <a href="/data/objectsHtml">Server-side processing with object source</a>
        		<br/>
        	</div>
            <div id="demo_jui">
		        <table id="companies" class="display">
		            <thead>
		                <tr>
		                    <th>Company name</th>
		                    <th>Address</th>
		                    <th>Town</th>
		                </tr>
		            </thead>
		            <tbody>
		            	<c:forEach items="${companies}" var="c">
						  <tr>
						    <td>${c.name}</td>
						    <td>${c.address}</td>
						    <td>${c.town}</td>
						  </tr>
		            	</c:forEach>
		            </tbody>
		        </table>
		    </div>
        </div>
    </body>
</html>
