<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Single Dev Statistic page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<#include "parts/support/headerMenu.ftlh">
<#include "parts/support/messageBlock.ftlh">

<div class="container-fluid mb-2">
    <h1>Single Dev Statistic</h1>
</div>

<div class="container-fluid mb-2">
    <#if developersList??>
        <form method="post" action="/statistics/devs">
<#--            <#include "parts/pages/index/filterDev.ftlh">-->

            <div class="col-4">
                <div class="row">
                    <div class="input-group input-group-sm mb-3">
                        <label class="input-group-text" for="devList">Developer</label>
                        <select class="form-select" id="devList" name="devList">
                            <#list developersList as developer>
                                <#if developer.dismiss == false>
                                    <option value="${developer.id}">${developer.fullName}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
            </div>

            <#include "parts/pages/index/filterDate.ftlh">
            <div class="row mb-2">
                <div class="input-group input-group-sm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary" style="width: 935px">Submit</button>
                </div>
            </div>
            <div class="row">
                <div class="row">
                    <div class="col-6 text-center">
                        <span>Developer <#if developer??>${developer.fullName}<#else> - </#if></span>
                    </div>
                </div>
                <div class="col-2">
                    <span class="input-group-text">Period</span>
                    <span class="input-group-text">Count Tasks</span>
                    <span class="input-group-text">Count Returns</span>
                    <span class="input-group-text">Count Re-Returns</span>
                    <span class="input-group-text">Count Defects</span>
                    <span class="input-group-text">Count Dev Help (HELP)</span>
                    <span class="input-group-text">Count Dev Help (NO HELP)</span>
                    <span class="input-group-text">Count Dev Help (NO NEED)</span>
                </div>
                <div class="col-2">
                    <label class="input-group-text"><#if taskStatisticBefore??>${taskStatisticBefore.dateStart} - ${taskStatisticBefore.dateEnd}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticBefore??>${taskStatisticBefore.countTask}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticBefore??>${taskStatisticBefore.countReturn}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticBefore??>${taskStatisticBefore.countReverts}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticBefore??>${taskStatisticBefore.countDefect}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticBefore??>${taskStatisticBefore.countHelpYes}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticBefore??>${taskStatisticBefore.countHelpNo}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticBefore??>${taskStatisticBefore.countHelpNone}<#else> - </#if></label>
                </div>
                <div class="col-2">
                    <label class="input-group-text"><#if taskStatisticAfter??>${taskStatisticAfter.dateStart} - ${taskStatisticAfter.dateEnd}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticAfter??>${taskStatisticAfter.countTask}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticAfter??>${taskStatisticAfter.countReturn}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticAfter??>${taskStatisticAfter.countReverts}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticAfter??>${taskStatisticAfter.countDefect}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticAfter??>${taskStatisticAfter.countHelpYes}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticAfter??>${taskStatisticAfter.countHelpNo}<#else> - </#if></label>
                    <label class="input-group-text"><#if taskStatisticAfter??>${taskStatisticAfter.countHelpNone}<#else> - </#if></label>
                </div>
            </div>
        </form>
    <#else>
        <div class="row">
            <div class="col-8 mb-2">
                No data here! Please
                <a href="/projects" style="text-decoration: none">
                    <button type="submit" class="btn btn-outline-primary btn-sm">Create Project</button>
                </a>
                , next
                <a href="/developers" style="text-decoration: none">
                    <button type="submit" class="btn btn-outline-primary btn-sm">Create Developers</button>
                </a>
                , and create any task
            </div>
        </div>
    </#if>

</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous">
</script>
</html>