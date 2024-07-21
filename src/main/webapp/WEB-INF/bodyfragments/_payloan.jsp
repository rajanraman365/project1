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
		<i class="fa fa-money"></i>
		<div class="card-title text-dark fw-bolder ">Pay Loan</div>
		<hr/>
		<div class="card-body">
		<sf:form method="post"
								action="${pageContext.request.contextPath}/home/user/loan/pay"
								modelAttribute="form">
<sf:hidden path="id"/>
								<div class="form-group">
									<s:bind path="totalAmount">
										<label for="inputEmail4" class="form-label">Total Amount</label>
										<sf:input path="${status.expression}"
											placeholder="Enter Account Type" class="form-control"  readonly="true"/>
										<font color="red" style="font-size: 13px"> <sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div>
								<div class="form-group">
									<s:bind path="monthlyPaymentAmount">
										<label for="inputEmail4" class="form-label">Monthly Payment</label>
										<sf:input path="${status.expression}"
											placeholder="Enter Account Type" class="form-control"  readonly="true"/>
										<font color="red" style="font-size: 13px"> <sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div>
								
								<div class="form-group">
									<label class="form-label">Enter Amount</label>
									<input type="number" name="amount" class="form-control">
								</div>
								
								
								<div class="container mt-3">
								<div align="left mb-3">
									<input id="login" type="submit" name="operation" class="btn btn-primary"
										value="Pay Loan" />
										
										
								</div>
								</div>
								
		</sf:form>						
		</div>
	</div>
</div>

