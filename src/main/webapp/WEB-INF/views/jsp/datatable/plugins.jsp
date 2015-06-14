<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="app.entity.*,app.repository.*"%>
    
<%@ include file="/common/jsp/taglib.jsp" %>

<html>
    <head>
        <%@ include file="/common/jsp/header.jsp" %>

        <title>Applying JQuery DataTables plugin in the Java Server application</title>

        <script type="text/javascript">
	        $(document).ready(function () {
	        	$("#companies").dataTable({
	                "sPaginationType": "full_numbers",
	                "bJQueryUI": true,
	                "sDom": 'W<"clear">lfrtip'
	            })
	            .rowGrouping({sGroupBy: "letter", bHideGroupingColumn: false})
	        });
        </script>
    </head>
    <body id="dt_example">
        <div id="container">
        	<div id="links">
        	    <a href="/data/index">Client side processing</a> | Client side processing with additional plugins<br/>
        		<a href="/data/indexHtml">Server-side processing with matrix source</a>| <a href="/data/objectsHtml">Server-side processing with object source</a>
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
		            <tfoot>
		                <tr>
		                    <th>Company name</th>
		                    <th>Address</th>
		                    <th>Town</th>
		                </tr>
		            </tfoot>
		        </table>
		    </div>
        </div>
    </body>
</html>
