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
		<div class="card-title text-dark fw-bolder  ">Loan</div>
		<hr />
		<div class="card-body">
			<sf:form method="post"
				action="${pageContext.request.contextPath}/home/admin/loan"
				modelAttribute="form">
				<sf:hidden path="id" />
				<div class="form-group">
					<s:bind path="accountId">
						<label for="inputEmail4" class="form-label">Associated
							Account</label>
						<sf:select class="form-control" path="${status.expression}">
							<sf:option value="" label="---Select---" />
							<sf:options itemLabel="accountName" itemValue="id"
								items="${accountTypeList}" />
						</sf:select>
						<font color="red" style="font-size: 13px"> <sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				<div class="row">
					<div class="form-group">
						<s:bind path="loanName">
							<label for="inputEmail4" class="form-label">Loan Name
								</label>
							<sf:input placeholder="Enter Loan Name" path="${status.expression}"
								class="form-control" />
							<font color="red" style="font-size: 13px"> <sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div></div>
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<s:bind path="originalLoanAmount">
								<label for="inputEmail4" class="form-label">Original
									Amount</label>
								<sf:input type="number" path="${status.expression}"
									class="form-control" />
								<font color="red" style="font-size: 13px"> <sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<s:bind path="interestRate">
								<label for="inputEmail4" class="form-label">Rate %</label>
								<sf:input type="number" path="${status.expression}"
									class="form-control" />
								<font color="red" style="font-size: 13px"> <sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group">
						<s:bind path="termLength">
							<label for="inputEmail4" class="form-label">Term Length
								(in years)</label>
							<sf:input type="number" path="${status.expression}"
								class="form-control" />
							<font color="red" style="font-size: 13px"> <sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div></div>
					
				</div>





				<div class="container">
					<div align="left mt-3">
						<input id="login" type="submit" name="operation"
							class="btn btn-primary" value="Save" /> <input id="login"
							type="submit" name="operation" class="btn btn-primary"
							value="Reset" />

					</div>
				</div>

			</sf:form>
		</div>
		<div class="container mt-3 mb-3">
					
						<c:if test="${si != null}">
							<div class="alert alert-danger text-center text-dark fw-bold">Simple Interest: ${si}</div>
						</c:if>
						<c:if test="${monthlyPayment != null}">
							<div class="alert alert-danger text-center text-dark fw-bold">Monthly Payment: ${monthlyPayment}</div>
						</c:if>
					</div>
	</div>
</div>

