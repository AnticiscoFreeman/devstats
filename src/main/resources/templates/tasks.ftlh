<#import "macros/common.ftlh" as common>
<#import "macros/paginations.ftlh" as paginationBlock>

<@common.page title="Tasks">

    <#include "parts/support/headerMenu.ftlh">
    <#include "parts/support/messageBlock.ftlh">

    <div class="container-fluid mb-2">
        <h1>Tasks</h1>
    </div>

    <#include "parts/support/helpBlock.ftlh">
    <#include "parts/pages/tasks/filterPart.ftlh">

    <@paginationBlock.pagination pageUrl tasksList />

    <div class="container-fluid mb-2">
        <#if tasksList?has_content>
            <#list tasksList.content as task>
                <form method="post"
                      action="/tasks/update/${task.id}"
                      onsubmit="return (confirm('Вы уверены, что хотите обновить задачу ${task.number}'));">
                    <div class="input-group input-group-sm">
                        <#include "parts/pages/tasks/taskInformation.ftlh">
                        <#if account.guestRole == false>
                            <#include "parts/support/taskUpdate.ftlh">
                        </#if>
                    </div>
                </form>
            </#list>
        <#else>
            <#include "parts/support/noDataBlock.ftlh">
        </#if>
    </div>

    <@paginationBlock.pagination pageUrl tasksList />

</@common.page>