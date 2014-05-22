<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>