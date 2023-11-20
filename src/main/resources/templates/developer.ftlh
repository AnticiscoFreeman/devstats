<#import "macros/common.ftlh" as common>
<#import "macros/paginations.ftlh" as paginationBlock>

<@common.page title="Developer ${developer.fullName}">

    <#include "parts/support/headerMenu.ftlh">
    <#include "parts/support/messageBlock.ftlh">

    <div class="container-fluid mb-2">
        <h1>${developer.fullName}
            <#if developer.dismiss == true> (DISMISS)</#if></h1>
    </div>

    <#include "parts/support/helpBlock.ftlh">

    <div class="container-fluid mb-2">
        <div class="col-4">
            <div class="input-group input-group-sm mb-1">
                <a href="/developers">
                    <button type="submit" class="btn btn-outline-primary btn-sm">Back to Developers</button>
                </a>
                <#if account.adminRole == true>
                    <form method="post" action="/developer/${developer.id}/dismiss">
                        <div class="input-group input-group-sm mb-1">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit"
                                    class="btn btn-outline-<#if developer.dismiss == true>success<#else>danger</#if> btn-sm">
                                <#if developer.dismiss == true>UnDismiss<#else>Dismiss</#if>
                            </button>
                        </div>
                    </form>
                </#if>
            </div>
        </div>
    </div>

    <#if account.guestRole == false>
        <#include "parts/pages/developer/addNewTaskForm.ftlh">
    </#if>

    <@paginationBlock.pagination pageUrl developerTasks />

    <div class="container-fluid mb-2">
        <#if developerTasks.content?has_content>
            <div class="col-12">
                <#list developerTasks.content as task>
                    <div class="row text-center">
                        <form
                                method="post"
                                action="/developer/${developer.id}/updateTask/${task.id}"
                                onsubmit="return (confirm('Вы уверены, что хотите обновить задачу ${task.number}'));"
                        >
                            <div class="input-group input-group-sm mb-1">
                                <#include "parts/pages/developer/taskInformation.ftlh">
                                <#if account.guestRole == false>
                                    <#include "parts/support/taskUpdate.ftlh">
                                </#if>
                            </div>
                        </form>
                    </div>
                </#list>
            </div>
        <#else>
            <#include "parts/support/noDataBlock.ftlh">
        </#if>
    </div>

    <@paginationBlock.pagination pageUrl developerTasks />

</@common.page>