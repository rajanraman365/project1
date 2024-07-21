<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<footer
	style="bottom: 0; left: 0; position: relative; // changed to relative from fixed also works if position is not there font-size : small; width: 100%;"
	class="page-footer font-small blue bg-header">
	<!-- Copyright -->

	<div class="footer-copyright text-center py-3">
		<span id="myid"> </span> © Copyright: <a href="#" class="text-dark fw-bold">
			CITI Banking Application</a>
	</div>
	<!-- Copyright -->
</footer>
<!-- Footer -->
<script>
  var myid = document.getElementById("myid");
  var a = new Date().getFullYear();
  myid.innerHTML = a;
 </script>
