<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
        isClient = user.isClient()
    >
<#else>
    <#assign
        name = "guest"
        isAdmin = false
        isClient = false
    >
</#if>