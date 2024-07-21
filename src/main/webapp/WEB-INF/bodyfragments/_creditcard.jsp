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
		<i class="fa fa-money mt-3"></i>
		<div class="card-title text-dark fw-bolder  ">Credit Card</div>
		<hr/>
		<div class="card-body">
		<sf:form method="post"
								action="${pageContext.request.contextPath}/home/admin/credit-card"
								modelAttribute="form">
<sf:hidden path="id"/>
								<div class="form-group">
									<s:bind path="accountId">
										<label for="inputEmail4" class="form-label">Associated Account</label>
										<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="---Select---" />
									<sf:options itemLabel="accountName"  itemValue="id"  items="${accountTypeList}" /></sf:select>
										<font color="red" style="font-size: 13px"> <sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div>
								<div class="row">
									<div class="col-lg-6"><div class="form-group">
									<s:bind path="creditLimit">
										<label for="inputEmail4" class="form-label">Credit Limit Amount</label>
										<sf:input type="number" path="${status.expression}"
											placeholder="Enter Credit Limit Amount" class="form-control" />
										<font color="red" style="font-size: 13px"> <sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div></div>
									<div class="col-lg-6"><div class="form-group">
									<s:bind path="date">
										<label for="inputEmail4" class="form-label">Issue Date</label>
										<sf:input id="datepicker" path="${status.expression}"
											class="form-control" />
										<font color="red" style="font-size: 13px"> <sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div></div>
								</div>
								
								<div class="row">
									<div class="col-lg-6"><div class="form-group">
									<s:bind path="creditCardNumber">
										<label for="inputEmail4" class="form-label">Credit Card Number</label>
										<sf:input path="${status.expression}"
											placeholder="Enter Credit Card No." class="form-control" />
										<font color="red" style="font-size: 13px"> <sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div></div>
									<div class="col-lg-6"><div class="form-group">
									<s:bind path="monthExpiry">
										<label for="inputEmail4" class="form-label">Expiry Month</label>
										<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="---Select---" />
									<sf:options items="${months}"/></sf:select>
										<font color="red" style="font-size: 13px"> <sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div></div>
								</div>
								
								<div class="row">
									<div class="col-lg-6"><div class="form-group">
									<s:bind path="year">
										<label for="inputEmail4" class="form-label">Expiry Year</label>
										<sf:select class="form-control" path="${status.expression}">
										<sf:option value="" label="---Select---" />
									<sf:options items="${years}"/></sf:select>
										<font color="red" style="font-size: 13px"> <sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div></div>
									<div class="col-lg-6"><div class="form-group">
									<s:bind path="cvv">
										<label for="inputEmail4" class="form-label">CVV</label>
										<sf:input placeholder="Enter CVV" path="${status.expression}"
											class="form-control" />
										<font color="red" style="font-size: 13px"> <sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div></div>
								</div>
								
								
								
								<div class="container mt-3">
								<div align="left mb-3">
									<input id="login" type="submit" name="operation" class="btn btn-primary"
										value="Save" />
										<input id="login" type="submit" name="operation" class="btn btn-primary"
										value="Reset" />
										
								</div>
								</div>
								
		</sf:form>						
		</div>
	</div>
</div>

