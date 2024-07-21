<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<c:url var="addUrl" value="/home/user/loan" />

<c:url var="addSearch" value="/home/user/loans" />

<c:url var="editUrl" value="/home/user/loan?id=" />

<sf:form method="post"
	action="${pageContext.request.contextPath}/home/user/loans"
	modelAttribute="form">
	<div class="container mt-4"
		style="position: relative; min-height: 70vh">
		<h2 class="text-white fw-bold">Loan</h2>



		<div class="container mt-2" style="width: 70%">
			<b><%@ include file="businessMessage.jsp"%></b>
		</div>
		<sf:input type="hidden" path="pageNo" />
		<sf:input type="hidden" path="pageSize" />

		<sf:input type="hidden" path="listsize" />
		<sf:input type="hidden" path="total" />
		<sf:input type="hidden" path="pagenosize" />
		<div class="table-responsive text-white">
			<table
				class="table text-dark bg-white table-bordered shadow-sm table-hover mt-5">
				<thead class="bg-white text-dark">
					<tr>

						<th scope="col">S.No</th>
						<th scope="col">Account Name</th>

						<th scope="col">Loan Name</th>
						<th scope="col">Loan Amount</th>
						<th scope="col">Rate %</th>
						<th scope="col">Term Length</th>
						<th scope="col">SI</th>
						<th scope="col">Monthly Payment</th>
						<th scope="col">Total Amount</th>
						<th scope="col">Payable Amount</th>
						<th scope="col">Action</th>




					</tr>
				</thead>
				<tbody>


					<c:forEach items="${list}" var="loan_list" varStatus="u">

						<tr>

							<td scope="row">${u.index+1}</td>
							<td scope="row">${loan_list.account.accountName}</td>

							<td scope="row">${loan_list.loanName}</td>
							<td scope="row">${loan_list.originalLoanAmount}</td>
							<td scope="row">${loan_list.interestRate}</td>
							<td scope="row">${loan_list.termLength}</td>
							<td scope="row">${loan_list.simpleInterest}</td>
							<td scope="row">${loan_list.monthlyPaymentAmount}</td>
							<td scope="row">${loan_list.totalAmount}</td>
							<td scope="row">${loan_list.payableAmount}</td>

							<c:if test="${sessionScope.user.roleId == 2}">
								<td>
								<c:if test="${loan_list.payableAmount!=0 }">
								<a class="text-dark fw-bold btn btn-outline-primary"
									href="${editUrl}${loan_list.id}&accountId=${loan_list.accountId}">Pay</a>
									</c:if>
									<c:if test="${loan_list.payableAmount==0 }">
										<a class="text-dark fw-bold btn btn-warning " >LOAN PAID</a>
									</c:if>
									</td>
								
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<hr>
		<div class="clearfix">





			<nav aria-label="Page navigation example float-end">
				<ul class="pagination justify-content-end" style="font-size: 13px">
					<li class="page-item"><input type="submit" name="operation"
						class="page-link"
						<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
						value="Previous"></li>
					<c:forEach var="i" begin="1" end="${(listsize/10)+1}">
						<c:if test="${i== pageNo}">
							<li class="page-item active"><a class="page-link activate"
								href="${addSearch}?pageNo=${i}">${i}</a></li>
						</c:if>
						<c:if test="${i != pageNo}">
							<li class="page-item"><a class="page-link"
								href="${addSearch}?pageNo=${i}">${i}</a></li>
						</c:if>
					</c:forEach>
					<li class="page-item"><input type="submit" name="operation"
						class="page-link"
						<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
						value="Next"></li>
				</ul>
			</nav>
		</div>


	</div>


</sf:form>
