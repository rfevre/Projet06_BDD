<!-- test.jsp -->
<!DOCTYPE HTML>
<HTML>
<HEAD>
   <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <TITLE>Page de test</TITLE>
</HEAD>
<BODY>

<center>
<h1>Page test</h1>
</center>

<br>
<br>

<jsp:useBean id="p" class="mvc.Message" scope="page" />
<%= p%>

<br>
<br>

</BODY>
</HTML>
