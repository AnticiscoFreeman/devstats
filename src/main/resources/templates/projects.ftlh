<#import "macros/common.ftlh" as common>

<@common.page title="Projects">

<#include "parts/support/headerMenu.ftlh">
<#include "parts/support/messageBlock.ftlh">

<div class="container-fluid mb-2">
    <h1>Projects Page</h1>
</div>

<#if account.adminRole == true>
    <div class="container-fluid mb-2">
        <div class="col-4">
            <form method="post" action="/projects">
                <div class="input-group input-group-sm">
                    <span class="input-group-text">New Project Name</span>
                    <input type="text" name="projectName" id="createProject" class="form-control"
                           placeholder="Enter Project Name" maxlength="50"  required>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-outline-success btn-sm">Create New Project</button>
                </div>
            </form>
        </div>
    </div>
</#if>

<div class="container-fluid mb-2">
    <#if projectList?has_content>
        <#list projectList as project>
            <div class="row">
                <div class="col mb-1">
                    <div class="input-group input-group-sm mb-1">
                        <label class="input-group-text" style="width:100px">Project Name</label>
                        <label class="input-group-text" style="width:300px">${project.name}</label>
                        <label class="input-group-text" style="width:100px">Count Tasks</label>
                        <label class="input-group-text" style="width:50px">${project.countTasks}</label>
                        <a href="/project/${project.id}">
                            <button type="submit" class="btn btn-outline-success btn-sm">Details</button>
                        </a>
                        <#if account.adminRole == true>
                            <form method="post" action="/projects/delete/${project.id}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-outline-danger btn-sm">
                                    Delete ${project.name}</button>
                            </form>
                        </#if>
                    </div>
                </div>
            </div>
        </#list>
    <#else>
        <#include "parts/support/noDataBlock.ftlh">
    </#if>
</div>

</@common.page>