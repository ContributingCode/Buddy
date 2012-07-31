<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header_template.html"%>

<div class="myResult">
   <div>
      <c:forEach items="${fbKeywords}" var="word">
         <div class="myKeyTile">
            <div class="myTileContent">
            <!-- class="label label-important" -->
               <span>${word.keyword}</span><br /> <br /> 
               <span class="label label-success">Score</span>
               <span class="badge badge-info">${word.score}</span>
            </div>
         </div>
      </c:forEach>
   </div>
</div>

<%@ include file="footer_template.html"%>
