<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
 <nav class="navbar navbar-expand-lg navbar-light bg-footer">

	<a class="navbar-brand container" href="<c:url value="/home"/>"> <b class="text-white"><i
			class="fa fa-bank" style="color: grey;"></i>CITI Banking Application</b>
	</a>

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav justify-content-end">

				<c:if test="${sessionScope.user != null}">
				
			</c:if>
				<c:if test="${sessionScope.user == null}">

				</c:if>
			</ul>

		</div>

	</div>
</nav> 

<nav class="navbar navbar-expand-lg navbar-secondary bg-header">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon">
		
		</span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="<c:url value="/home"/>">Home <span class="sr-only">(current)</span></a>
			</li>

			<c:if test="${sessionScope.user != null}">
				<c:if test="${sessionScope.user.roleId == 1}">
				<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> Account Type </a>
				<ul class="dropdown-menu">

					<li><a class="dropdown-item" href="<c:url value="/home/admin/account-type"/>">Add Account Type</a></li>

					<li><a class="dropdown-item" href="<c:url value="/home/admin/account-type/search"/>">Account Type List</a></li>
				</ul></li>
					<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/accounts"/>">Accounts List</a></li>
					<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> Credit Card </a>
				<ul class="dropdown-menu">

					<li><a class="dropdown-item" href="<c:url value="/home/admin/credit-card"/>">Add Credit Card</a></li>

					<li><a class="dropdown-item" href="<c:url value="/home/admin/credit-cards"/>">Credit Card List</a></li>
				</ul></li>
				<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> Loan </a>
				<ul class="dropdown-menu">

					<li><a class="dropdown-item" href="<c:url value="/home/admin/loan"/>">Add Loan</a></li>

					<li><a class="dropdown-item" href="<c:url value="/home/admin/loans"/>">Loan List</a></li>
				</ul></li>
				</c:if>
				<c:if test="${sessionScope.user.roleId == 2}">
					<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/user/account"/>">Add Beneficiary</a></li>
					<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/accounts"/>">Accounts</a></li>
					<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> Transaction </a>
				<ul class="dropdown-menu">

					<li><a class="dropdown-item" href="<c:url value="/home/user/account/deposit"/>">Deposit Amount</a></li>

					<li><a class="dropdown-item" href="<c:url value="/home/user/account/withdraw"/>">Withdraw Amount</a></li>
					
					<li><a class="dropdown-item" href="<c:url value="/home/user/account/transfer"/>">Transfer</a></li>
				</ul></li>

					<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> Credit Card </a>
				<ul class="dropdown-menu">

					<li><a class="dropdown-item" href="<c:url value="/home/user/credit-card/payment"/>">Transact Via Credit Card</a></li>

					<li><a class="dropdown-item" href="<c:url value="/home/user/credit-cards"/>">Credit Card List</a></li>
				</ul></li>
<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/user/loans"/>">Loan</a></li>
					
				</c:if>
				
			</c:if>
		</ul>

	</div>
	<ul class="navbar-nav justify-content-end">
		<c:if test="${sessionScope.user != null}">

<li class="nav-item "><a
				class="nav-link " href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> Hello,${sessionScope.user.firstName} (${sessionScope.user.roleName }) </a>
				<%-- <ul class="dropdown-menu">

					<li><a class="dropdown-item" href="<c:url value="/home/login/myprofile"/>">My Profile</a></li>

					<li><a class="dropdown-item" href="<c:url value="/home/login/changepassword"/>">Change Password</a></li>
					
				</ul> --%></li>
			

			<li class="nav-item "><a class="nav-link fw-bold text-dark"
				style="padding: 6px; color: black;"
				href="<c:url value="/home/login"/>"><i class="fa fa-sign-out"></i> Logout</a></li>
		</c:if>
		<c:if test="${sessionScope.user == null}">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/home/user"/>">Register</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/home/login"/>">Login</a></li>
			
		</c:if>
	</ul>
</nav>
