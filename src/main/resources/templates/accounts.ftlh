<#import "macros/common.ftlh" as common>
<@common.page title="Accounts">

<#include "parts/support/headerMenu.ftlh">
<#include "parts/support/messageBlock.ftlh">

<div class="container-fluid mb-2">
    <h1>Accounts page</h1>
</div>

<#include "parts/pages/users/addUserForm.ftlh">

<div class="container-fluid mb-2">
    <#if accountsList?has_content>
        <#include "parts/pages/users/userList.ftlh">
    <#else>
        <#include "parts/support/noDataBlock.ftlh">
    </#if>
</div>

</@common.page>