<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<c:url var="addUrl" value="/home/admin/account-type" />

<c:url var="addSearch" value="/home/accounts" />

<c:url var="editUrl" value="/home/accounts?id=" />

<sf:form method="post"
	action="${pageContext.request.contextPath}/home/accounts"
	modelAttribute="form">
	<div class="container mt-4"
		style="position: relative; min-height: 70vh">
		<h2 class="text-white fw-bold">Accounts</h2>
		<c:if test="${sessionScope.user.roleId==2 }">
		<div class="container" align="right">
	 		<button class="btn btn-sm btn-light fw-bold text-dark"><i class="fa fa-money"></i> Total Amount: ${totalAmount}</button>
		</div>
</c:if>
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

						<c:if test="${sessionScope.user.roleId == 1}">
							<th scope="col">Select All</th>
						</c:if>
						<th scope="col">S.No</th>
						<th scope="col">Account Name</th>
						<th scope="col">Account No</th>
						<th scope="col">Account Type</th>
						<th scope="col">Account Balance</th>
						<th scope="col">Entry Date</th>
						<th scope="col">Status</th>



					</tr>
				</thead>
				<tbody>


					<c:forEach items="${list}" var="acc_list" varStatus="u">

						<tr>
						<c:if test="${sessionScope.user.id==acc_list.userId || sessionScope.user.roleId == 1}">
							<c:if test="${acc_list.status eq 'OPEN' &&  sessionScope.user.id==acc_list.userId || sessionScope.user.roleId == 1}">
							<c:if test="${sessionScope.user.roleId == 1}">
								<td><input type="checkbox" class="case" name="ids"
									value="${acc_list.id}"></td>
							</c:if>
							<td scope="row">${u.index+1}</td>
							<td scope="row">${acc_list.accountName}</td>
							<td scope="row">${acc_list.accountNumber}</td>
							<td scope="row">${acc_list.accountType}</td>
							<td scope="row">
							<c:if test="${acc_list.amount==null}">
							0.0 &#x20B9;
							</c:if><c:if test="${acc_list.amount!=null}">
							${acc_list.amount} &#x20B9;
							</c:if></td>
							<td scope="row">${acc_list.createdDatetime}</td>
							<td scope="row"><i class="fa fa-circle"></i> <span class="fw-bold text-primary">${acc_list.status}</span></td>
							</c:if>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<hr>
		<div class="clearfix">

			<c:if test="${sessionScope.user.roleId == 1}">
				<input type="submit" name="operation"
					class="btn btn-sm btn-danger float-start"
					<c:if test="${listsize == 0}">disabled="disabled"</c:if>
					value="Close">


			</c:if>



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
