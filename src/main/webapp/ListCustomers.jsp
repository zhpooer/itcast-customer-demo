<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer List</title>
</head>
<body>
<style type="text/css">
    .odd{
        background-color: #c3f3c3;
    }
    .even{
       background-color: #f3c3f3;
    }
    body{
       text-align : center;
       font-size: 12px
    }
    table {
       font-size: 12px;
        width: 100%;
    }
</style>

<div>
    <a href="${pageContext.request.contextPath}/addCustomer.jsp">添加</a>
    <a href="javascript:delMulti();">删除</a>
</div>
<c:if test="${empty p.recorder}">
   没有客户信息
</c:if>
<c:if test="${!empty p.recorder}">
<form action="${pageContext.request.contextPath}/servlet/Controller?op=delMultiCustomer" method="post">
   <table border="1">
       <tr>
           <th> 选择 </th>
           <th> 姓名 </th>
           <th> 性别 </th>
           <th> 出生日期 </th>
           <th> 电话 </th>
           <th> 邮箱 </th>
           <th> 爱好 </th>
           <th> 类型 </th>
           <th> 描述 </th>
           <th> 操作 </th>
       </tr>
       <c:forEach items="${p.recorder}" var="c" varStatus="vs">
           <tr class="${(vs.index%2)==0?'odd':'even'}">
              <td>
                  <input type="checkbox" name="ids" value="${c.id}"/>
              </td>
              <td> ${c.name} </td>
              <td> ${c.gender} </td>
              <td> ${c.birthday} </td>
              <td> ${c.cellphone} </td>
              <td> ${c.email} </td>
              <td> ${c.hobby} </td>
              <td> ${c.type} </td>
              <td> ${c.description} </td>
              <td>
              <a href="${pageContext.request.contextPath}/servlet/Controller?op=EditCustomerUI&id=${c.id}">编辑</a>
              <a href="${pageContext.request.contextPath}/servlet/Controller?op=DeleteCustomer&id=${c.id}">删除</a>
              </td>
           </tr>
       </c:forEach>
   </table>
</form>
<a href="${pageContext.request.contextPath}/servlet/Controller?op=showAllCustomer&num=${p.pageNum-1<1?1:p.pageNum-1}">上一页</a>
 第${p.pageNum}页 &nbsp;&nbsp; 共${p.totalPage}页
<a href="${pageContext.request.contextPath}/servlet/Controller?op=showAllCustomer&num=${p.pageNum+1>p.totalPage?p.totalPage:p.pageNum+1}">下一页</a>
<br/>
<select name="num" onchange="selectPage(this);">
    <c:forEach begin="1" end="${p.totalPage}" var="i">
            <option value="${i}">第${i }页</option>
    </c:forEach>
</select>
</c:if>
<script type="text/javascript">
function selectPage(selector){
  window.location.href = "${pageContext.request.contextPath}/servlet/Controller?op=showAllCustomer&num=" + selector.value
}
function delMulti(){
	 // 首先判断用户哟没有选择要删除的记录
   var selected = false;
	 var ids = document.getElementsByName("ids");
	 for (var i=0;i<ids.length;i++){
		  if(ids[i].checked){
			  selected = true;
			  break;
		  }
	 }
	 if(selected){
	   var sure = window.confirm("are you sure?");
	   if(sure){
		   document.forms[0].submit();
	   }  
	 } else {
		 alert("please select");
	 }
}
</script>
</body>
</html>