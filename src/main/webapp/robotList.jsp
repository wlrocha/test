<%@ include file="/taglibs.jsp"%>

<title><fmt:message key="robotList.title"/></title>

<button onclick="location.href='robotform.html'"style="float: right; margin-top: -30px; width: 100px">Add Robot</button>

<display:table name="robotList" class="table" requestURI="" id="robotList" export="true" pagesize="10">
    <display:setProperty name="export.pdf.filename" value="robots.pdf"/>
    <display:column property="id" sortable="true" href="robotform.html" media="html"
        paramId="id" paramProperty="id" titleKey="robot.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="robot.id"/>
    <display:column property="designation" sortable="true" titleKey="robot.designation" escapeXml="true"/>
    <display:column sortable="true" titleKey="robot.qualityCheckPassed" escapeXml="true">
        ${robotList.qualityCheckPassed?"yes":"no"}
    </display:column>
    <display:column sortable="true" titleKey="robot.dateBuild" escapeXml="true">
        <fmt:formatDate value="${robotList.dateOfBuild}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="owner.fullName" sortable="true" titleKey="robot.owner" escapeXml="true"/>
</display:table>

<script type="text/javascript">highlightTableRows("robotList");</script>
