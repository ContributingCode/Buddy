<%@ include file="header_template.html" %>
<!-- <ul class="thumbnails"> -->
<!--   <li class="span3"> -->
<!--     <div class="thumbnail"> -->
<!--       <img src="http://placehold.it/260x180" alt=""> -->
<!--       <h1>Organization a</h1> -->
<!--       <p>some texts from the result of charityNav html parser.</p> -->
<!--     </div> -->
<!--   </li> -->
<!-- </ul> -->

<c:forEach items="${Display}" var="entry">
	<ul class="cust">
	  <li class="cust-name">${entry.name} xyz </li>
	</ul>
</c:forEach> 

<%@ include file="footer_template.html" %>
