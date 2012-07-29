<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header_template.html"%>
<!-- <ul class="thumbnails"> -->
<!--   <li class="span3"> -->
<!--     <div class="thumbnail"> -->
<!--       <img src="http://placehold.it/260x180" alt=""> -->
<!--       <h1>Organization a</h1> -->
<!--       <p>some texts from the result of charityNav html parser.</p> -->
<!--     </div> -->
<!--   </li> -->
<!-- </ul> -->

<ul class="span12">
<c:forEach items="${displayList}" var="display">
      <h1><a href="${display.url}">${display.name}</a></h1>
      <p>Score: ${display.score}</p>
      <p>Location: ${display.location}</p>
      <p>Category: ${display.category}</p>
      <p>Description: ${display.description}</p>
</c:forEach>
</ul>

<%@ include file="footer_template.html"%>
