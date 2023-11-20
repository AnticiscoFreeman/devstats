<#import "macros/common.ftlh" as common>
<@common.page title="Project ${project.name}">

    <#include "parts/support/headerMenu.ftlh">
    <#include "parts/support/messageBlock.ftlh">

    <div class="container-fluid mb-2">
        <h1>Project <span style="text-transform: uppercase">${project.name}</span></h1>
    </div>

    <#include "parts/support/helpBlock.ftlh">

    <div class="container-fluid mb-2">
        <div class="col-4">
            <div class="input-group input-group-sm mb-1">
                <a href="/projects">
                    <button type="submit" class="btn btn-outline-primary btn-sm">Back to Projects</button>
                </a>
            </div>
        </div>
    </div>

    <div class="container-fluid mb-2">
        <#if project.tasksList?has_content>
            <#list project.tasksList as task>
                <form
                        method="post"
                        action="/tasks/update/${task.id}"
                        onsubmit="return (confirm('Вы уверены, что хотите обновить задачу ${task.number}'));"
                >
                    <div class="input-group input-group-sm">
                        <#include "parts/pages/project/taskInformation.ftlh">
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

</@common.page>