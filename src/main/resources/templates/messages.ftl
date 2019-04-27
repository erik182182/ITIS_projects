<!DOCTYPE html>
<html lang="en">
<head>
    <title>Диалоги</title>

</head>
<body>

<#list messages as message>
    <h2> ${message.user.firstName}:</h2>
    <h1>${message.text}</h1>
    <#if message.creationTime??>
        <h2> ${message.creationTime.dayOfMonth}-${message.creationTime.month}-${message.creationTime.year} ${message.creationTime.hour}:${message.creationTime.minute}</h2>
    </#if>
</#list>
<form method = "post">
    <br />

    <textarea rows = "5" cols = "50" name = "message">

         </textarea>

    <input type = "submit" value = "submit" />
</form>

</body>
</html>