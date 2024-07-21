<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<div class="container mt-2"
	style="position: relative; min-height: 70vh">
	<%@ include file="businessMessage.jsp"%>
	<div class="card text-center " style="width: 70%">
		<i class="fa fa-bank mt-3"></i>
		<div class="card-title text-dark fw-bolder  ">Credit Card
			Payment</div>
		<hr />
		<div class="card-body">
			<sf:form method="post"
				action="${pageContext.request.contextPath}/home/user/credit-card/payment"
				modelAttribute="form">
				<sf:hidden path="id" />
				<div class="form-group">
					<s:bind path="creditCardNumber">
						<label for="inputEmail4" class="form-label">From</label>
						<sf:select class="form-control" name="id"
							path="${status.expression}">
							<sf:option value="" label="---Select---" />
							<sf:options itemLabel="creditCardNumber" itemValue="creditCardNumber"
								items="${userAccounts}" />
						</sf:select>
						<font color="red" style="font-size: 13px"> <sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				<div class="form-group"></div>
				<div class="form-group">

					<label for="inputEmail4" class="form-label">Enter Amount</label>
					<sf:input path="${status.expression}" name="amount" type="number" placeholder="Enter Amount"
						class="form-control" />
					<font color="red" style="font-size: 13px"> <sf:errors
							path="${status.expression}" /></font>

				</div>


				<div class="container mt-3">
					<div align="left mb-3">
						<input id="login" type="submit" name="operation"
							class="btn btn-primary" value="Send" /> <input id="login"
							type="submit" name="operation" class="btn btn-primary"
							value="Reset" />

					</div>
				</div>

			</sf:form>
		</div>
	</div>
</div>

