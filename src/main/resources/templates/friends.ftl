<!DOCTYPE html>
<html lang="en">
<head>
    <title>Друзья</title>

</head>
<body>

<li>
    <#list friends as friend>
        <#if getUser.id!=friend.friend.id>
            <ul>${friend.friend.firstName} ${friend.friend.lastName} </ul>
        <#else>
            <ul>${friend.user.firstName} ${friend.user.lastName} </ul>
        </#if>
    </#list>

</li>

</body>
</html>