<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.page .pagination-right{
cursor:pointer;
}
</style>
<div class="page">
	<div class="pagination-right">
		共${data.count}条记录 &nbsp;&nbsp;<span onclick="goPage('firstpage')">首页</span> &nbsp;&nbsp; <span
			onclick="goPage('lastpage')">上一页</span>  &nbsp;&nbsp;<span
			onclick="goPage('nextpage')">下一页 </span>  &nbsp;&nbsp;<span
			onclick="goPage('endpage')">末页</span>&nbsp;&nbsp; 到第 <input id="nowpage"
			class="input-mini" value="${data.cond.page }" onkeyup="checkPage()" /> /${data.pages } 页
		<input type="button" class="btn btn-small btn-primary" value="GO"
			onclick="changePage()" />
	</div>
	<script>
		function checkPage() {
			var nowpage = $("#nowpage").val().replace(/\D/gi, "");
			if (nowpage != "") {
				if (parseInt(nowpage) > ${data.pages }) {
					nowpage = ${data.pages };
				} else if (1 > parseInt(nowpage)) {
					nowpage = "1";
				}
			}
			$("#nowpage").val(nowpage);
		}
		function changePage() {
			$("#page").val($("#nowpage").val());
			document.forms[0].action=$("form[method='post']").attr("action")+"?is_page=yes";//假如不是翻页的，查询时候要清掉页数
			query();
		}
		function goPage(id) {
			var nowpage = parseInt($("#page").val());
			if (id == "lastpage") {
				if (nowpage > 1) {
					$("#page").val(nowpage - 1);
				}
			} else if (id == "nextpage") {
				if (nowpage < ${data.pages }) {
					$("#page").val(nowpage + 1);
				}
			} else if (id == "firstpage") {
				$("#page").val("1");
			} else if (id == "endpage") {
				$("#page").val("${data.pages }");
			}
			document.forms[0].action=$("form[method='post']").attr("action")+"?is_page=yes";//假如不是翻页的，查询时候要清掉页数
			query();
		}
	</script>
</div>