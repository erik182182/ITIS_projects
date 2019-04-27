<!DOCTYPE html>
<html lang="en">
<head>
    <title>Друзья</title>

</head>
<body>

<#if friend??>
    <h1>
        ${friend.lastName} ${friend.firstName}
    </h1>
    <p>${friend.age}</p>
    <#if friend.photoSrc??>
        <img src="/wellness/${friend.photoSrc}">
    </#if>
</#if>

<#if friend.comments??>
    <h1>Комменты</h1>
    <#list friend.comments as comment>
        <h1>
        ${comment.id} ${comment.article.title}
        </h1>
        <p>${comment.article.text}</p>
        <p>${comment.text}</p>
    </#list>
</#if>

<#if friend.favoriteArticles??>
    <h1>Избранные</h1>
    <#list friend.favoriteArticles as article>
        <h1>
            <a href="/wellness/articles/${article.id}">
                ${article.id} ${article.title}
            </a>
        </h1>
        <p>${article.text}</p>
    </#list>
</#if>





</body>
</html>