
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>

<c:if test="${success != null}">
<div class="alert alert-secondary text-center">	${success}</div>
</c:if>
<c:if test="${error != null}">
	<div class="alert alert-danger text-center">${error}</div>
</c:if>
<c:if test="${empId != null}">
	<div style="color: red;font-size: 12px;" role="alert">${empId}</div>
</c:if>
<c:if test="${msg != null}">
	<div class="alert alert-danger text-center">${msg}</div>
</c:if>