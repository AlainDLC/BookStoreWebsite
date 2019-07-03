<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	<div>
		<img src="../images/fight.png" />
	</div>
	<div>
		Welcome, <c:out value="${sessionScope.useremail}" /> | <a href="logout">Logout</a> <br> <br>
	</div>
	<div id="headermenu">
		<div>
			<a href="list_users">
		   		<img src="../images/dead.png" /><br>
			<br>Users
			</a>
		</div>
		
		<div>
			<a href="list_category">
				<img src="../images/cate.png" /><br>
			<br>Category
			</a>
		</div>

		<div>
			<a href="list_books">
				<img src="../images/books.png" /><br>
			<br>Books
		  </a>
		</div>
		
		<div >
			<a href="customer">
				<img src="../images/cust.png" /><br>
			<br>Customers
			</a>
		</div>
		
		<div>
			<a href="reviews">
				<img src="../images/review.png" /><br>
			<br>Review
			</a>
		</div>
		
		<div >
			<a href="orders">
				<img src="../images/order.png" /><br>
			<br>Order
			</a>
		</div>
	</div>
</div>