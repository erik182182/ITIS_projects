<!DOCTYPE html>
<html lang="en">
<head>
    <title>Друзья</title>

</head>
<body>

<li>
    <#list friends as friend>
        <#if getUser.id!=friend.friend.id>
            <ul>${friend.friend.firstName} ${friend.friend.lastName} <a href="/wellness/myFriends/${friend.friend.id}">Посмотреть</a></ul>
        <#else>
            <ul>${friend.user.firstName} ${friend.user.lastName} <a href="/wellness/myFriends/${friend.user.id}">Посмотреть</a> </ul>
        </#if>
    </#list>

</li>

</body>
</html>