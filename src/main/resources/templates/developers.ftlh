<#import "macros/common.ftlh" as common>
<#import "macros/paginations.ftlh" as paginationBlock>

<@common.page title="Developers">

<#include "parts/support/headerMenu.ftlh">
<#include "parts/support/messageBlock.ftlh">

<div class="container-fluid mb-2">
    <h1>Developers</h1>
</div>

<#if account.adminRole == true>
    <#include "parts/pages/developers/addDevelopForm.ftlh">
</#if>

    <@paginationBlock.pagination pageUrl developersList />

<div class="container-fluid mb-2">
    <#if developersList?has_content>
        <#list developersList.content as dev>
            <div class="row">
                <div class="col mb-1">
                    <div class="input-group">
                        <div class="input-group input-group-sm mb-1">
                            <span class="input-group-text" style="<#if dev.dismiss == true>background-color: gray</#if>">Developer</span>
                            <label class="input-group-text" style="width:300px; <#if dev.dismiss == true>background-color: gray</#if>">${dev.fullName}</label>
                            <label class="input-group-text" style="width:100px; <#if dev.dismiss == true>background-color: gray</#if>">${dev.type}</label>
                            <label class="input-group-text" style="width:100px; <#if dev.dismiss == true>background-color: gray</#if>">
                                <#if dev.dismiss == true>INACTIVE<#else>ACTIVE</#if>
                            </label>
                            <span class="input-group-text" style="<#if dev.dismiss == true>background-color: gray</#if>">Count tasks</span>
                            <label class="input-group-text" style="width:100px; <#if dev.dismiss == true>background-color: gray</#if>">${dev.countTasks}</label>
                            <a href="/developer/${dev.id}">
                                <button type="submit" class="btn btn-outline-success btn-sm">Details</button>
                            </a>
                            <#if account.adminRole == true>
                                <form method="post" action="/developers/delete/${dev.id}">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <button type="submit" class="btn btn-outline-danger btn-sm">Delete this Developer
                                    </button>
                                </form>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    <#else>
        <#include "parts/support/noDataBlock.ftlh">
    </#if>
</div>

    <@paginationBlock.pagination pageUrl developersList />

</@common.page>