<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<c:url var="addUrl" value="/home/admin/credit-cards" />

<c:url var="addSearch" value="/home/admin/credit-cards" />

<c:url var="editUrl" value="/home/admin/credit-card?id=" />

<sf:form method="post"
	action="${pageContext.request.contextPath}/home/admin/credit-cards"
	modelAttribute="form">
	<div class="container mt-4"
		style="position: relative; min-height: 70vh">
		<h2 class="text-white fw-bold">Credit Cards</h2>

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
						<th scope="col">Card Number</th>
						<th scope="col">Card Limit</th>
						<th scope="col">CVV</th>
						<th scope="col">Issue Date</th>
						<th scope="col">Month Expiry</th>
						<th scope="col">Year</th>
						<c:if test="${sessionScope.user.roleId==1 }">
							<th scope="col">Action</th>
						</c:if>


					</tr>
				</thead>
				<tbody>


					<c:forEach items="${list}" var="credit_list" varStatus="u">

						<tr>

						<c:if test="${sessionScope.user.id==credit_list.userId || sessionScope.user.roleId == 1}">
							<td scope="row">${u.index+1}</td>
							<td scope="row">${credit_list.accounts.accountName}</td>
							<td scope="row">${credit_list.creditCardNumber}</td>
							<td scope="row">${credit_list.creditLimit}</td>
							<td scope="row">${credit_list.cvv}</td>
							<td scope="row">${credit_list.date}</td>
							<td scope="row">${credit_list.monthExpiry}</td>
							<td scope="row">${credit_list.year}</td>
							<c:if test="${sessionScope.user.roleId == 1}">
								<td><a class="text-dark fw-bold btn btn-outline-primary"
									href="${editUrl} ${credit_list.id}"><i
										class="fa fa-edit"></i></a></td>
							</c:if>
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
