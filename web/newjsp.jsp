<%-- 
    Document   : newjsp
    Created on : Mar 31, 2015, 11:42:46 PM
    Author     : Vamshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="personBean" scope="request" class="org.mypackage.models.GmailApiQuickstart" />
        
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zap Gmail Counter - Report</title>
        <style type ="text/css">
            .editor-container {
                margin-left: 20%;
                margin-right: 20%;
                margin-top:  50px;
                border-radius: 10px 10px 10px 10px;
                padding-left: 5%;
                padding-right : 5%;
                padding-bottom: 6%;
                padding-top: 5%;
-moz-border-radius: 10px 10px 10px 10px;
-webkit-border-radius: 10px 10px 10px 10px;
border: 2px solid #141214;
                
            }
        </style>
        
    </head>
    <body>
        <div class ="editor-container">
        <h1>Report</h1>
         <jsp:getProperty name="personBean" property="name" /> 
  </div>
    </body>
</html>

