<#import "macros/common.ftlh" as common>

<@common.page title="Home">

<#include "parts/support/headerMenu.ftlh">
<#include "parts/support/messageBlock.ftlh">

<div class="container-fluid mb-2">
    <h1>Hello, ${account.name}</h1>
</div>

</@common.page>