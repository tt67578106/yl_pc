<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getNumber() + 1;
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>
 <c:choose>
	<c:when test="${not empty pageUrl }">
		<c:set var = "pageUrl" value = "${pageUrl }" />
	</c:when>
	<c:otherwise>
		<c:set var = "pageUrl" value = "0" />
	</c:otherwise>
</c:choose>
<div class="paging">
	 <c:if test="${page.totalPages ne 0}">
	 <c:choose>
		<c:when test="${page.hasPreviousPage()}">
			<a class="pre" href="/${pageUrl}_${current-1}/">&nbsp;</a>
		</c:when>
		<c:otherwise>
			<a class="pre disabled" href="javascript:void(0)">&nbsp;</a>
		</c:otherwise>
	</c:choose>
	<a class="page <c:if test="${current==1}">current</c:if>" href="/${pageUrl}_1/">1</a>
	<c:if test="${current>=4}"><span class="ellipsis">…</span></c:if>
    <c:forEach var="i" begin="${begin}" end="${end}">
    	<c:if test="${i ne 1 && i ne page.totalPages}">
       <a class="page <c:if test="${i == current}">current</c:if>" href="/${pageUrl}_${i}/">${i}</a>
    	</c:if>
    </c:forEach>
    <c:if test="${page.totalPages-current>=4}"><span class="ellipsis">…</span></c:if>
    <c:if test="${page.totalPages>1}">
    <a class="page <c:if test="${current==page.totalPages}">current</c:if>" href="/${pageUrl}_${page.totalPages}/">${page.totalPages}</a>
    </c:if>
    <c:choose>
		<c:when test="${page.hasNextPage() }">
			<a class="next" href="/${pageUrl}_${current+1}/">&nbsp;</a>
		</c:when>
		<c:otherwise>
			<a class="next disabled" href="javascript:void(0)">&nbsp;</a>
		</c:otherwise>
	</c:choose>
	</c:if>
</div>
