<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet/Controller?op=addCustomer" method="post">
    <table border="1">
        <tr>
            <td> 姓名 </td>
            <td> <input type="text" name="name"/> </td>
        </tr>
        <tr>
            <td> 性别 </td>
            <td>
            <input type="radio" name="gender" value="1" checked/>男
            <input type="radio" name="gender" value="0" checked/>女
            </td>
        </tr>
        <tr>
            <td> 出生日期</td>
            <td> <input type="text" name="birthday" value="1990-01-01"/> </td>
        </tr>
        <tr>
            <td> 电话 </td>
            <td> <input type="text" name="cellphone"/> </td>
        </tr>
        <tr>
            <td> 邮箱 </td>
            <td> <input type="text" name="email"/> </td>
        </tr>
        <tr>
            <td> 爱好: </td>
            <td>
               <input type="checkbox" name="hobbies" value="吃饭"/>吃饭
               <input type="checkbox" name="hobbies" value="睡觉"/>睡觉
               <input type="checkbox" name="hobbies" value="打豆豆"/>打豆豆
            </td>
        </tr>
        <tr>
            <td> 类型</td>
            <td>
                <input type="radio" name="type" value="vip"/> vip
                <input type="radio" name="type" value="normal" checked/> normal
            </td>
        </tr>
        <tr>
            <td> 描述 </td>
            <td>
                <textarea rows="3" cols="38" name="description">
                </textarea>
            </td>
        </tr>        
    </table>
    <input type="submit"/>
</form>
</body>
</html>