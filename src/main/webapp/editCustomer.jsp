<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet/Controller?op=editCustomer" method="post">
    <input type="hidden" name="id" value="${c.id}"/>
    <table border="1">
        <tr>
            <td> 姓名 </td>
            <td> <input type="text" name="name" value="${c.name}"/> </td>
        </tr>
        <tr>
            <td> 性别 </td>
            <td>
            <input type="radio" name="gender" ${c.gender=='0'?'checked':''} value="0" /> 女
            <input type="radio" name="gender" ${c.gender=='1'?'checked':''} value="1"/> 男
            </td>
        </tr>
        <tr>
            <td> 出生日期</td>
            <td> <input type="text" name="birthday" value="${c.birthday}"/> </td>
        </tr>
        <tr>
            <td> 电话 </td>
            <td> <input type="text" name="cellphone" value="${c.cellphone }"/> </td>
        </tr>
        <tr>
            <td> 邮箱 </td>
            <td> <input type="text" name="email" value="${c.email}"/> </td>
        </tr>
        <tr>
            <td> 爱好: </td>
            <td>
               <input type="checkbox" name="hobbies" ${fn:contains(c.hobby, '吃饭')?'checked':''}  value="吃饭"/> 吃饭
               <input type="checkbox" name="hobbies" ${fn:contains(c.hobby, '睡觉')?'checked':''} value="睡觉"/> 睡觉
               <input type="checkbox" name="hobbies" ${fn:contains(c.hobby, '打泡泡')?'checked':''} value="打泡泡"/> 打泡泡
            </td>
        </tr>
        <tr>
            <td> 类型</td>
            <td>
                <input type="radio" name="type" ${c.type=='vip' ?'checked':''} value="vip"/> vip
                <input type="radio" name="type"  ${c.type!='vip' ?'checked':''} value="normal"/> 普通用户
            </td>
        </tr>
        <tr>
            <td> 描述 </td>
            <td>
                <textarea rows="3" cols="38" name="description" >
                ${c.description}
                </textarea>
            </td>
        </tr>
    </table>
    <input type="submit" value="提交"/>
</form>
</body>
</html>