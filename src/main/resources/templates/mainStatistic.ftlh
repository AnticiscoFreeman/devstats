<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Main Statistic</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>

<div class="container-fluid page_header">
    <#include "parts/support/headerMenu.ftlh">
    <#include "parts/support/messageBlock.ftlh">

    <div class="container-fluid">
        <h1>Yearly Chart Statistic</h1>
    </div>
</div>

<table class="table table-bordered table-hover table-sm table-fixed">
    <thead>
    <tr>
        <th scope="col" colspan="8">
            <form method="get" action="/statistics/main/filter">
                <div class="input-group input-group-sm" style="width:700px">
                    <label class="input-group-text">Current year</label>
                    <select class="form-select form-select-sm" name="selectYear">
                        <option value="${selectedYear}" selected>${selectedYear}</option>
                        <#list availableYear as year>
                            <option value="${year}">${year}</option>
                        </#list>
                    </select>
                    <label class="input-group-text">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio"
                                   name="devFilter"
                                   id="devCheckAll"
                                   value="allDevs"
                                   <#if onlyActiveDevs = false>checked</#if>>
                            <label class="form-check-label" for="devCheckAll">All developer</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio"
                                   name="devFilter"
                                   id="devCheckActive"
                                   value="activeDevs"
                                   <#if onlyActiveDevs = true>checked</#if>>
                            <label class="form-check-label" for="devCheckActive">Active developer</label>
                        </div>
                    </label>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-outline-success btn-sm">Select</button>
                </div>
            </form>
        </th>
    </tr>
    <tr class="text-center">
        <th scope="col">Dev</th>
        <th scope="col">Count Task</th>
        <th scope="col">No Defect Task</th>
        <th scope="col">Return Task</th>
        <th scope="col">Revert Task</th>
        <th scope="col">Count defects</th>
        <th scope="col">Count Helps</th>
        <th scope="col">Count No Helps</th>
    </tr>
    </thead>
    <tbody class="table-group-divider text-center">
    <#list monthStatistics as stat>
        <tr>
            <td colspan="8"> ${stat.period}</td>
        </tr>
        <#if stat.taskStatistics?has_content>
            <#list stat.taskStatistics as stat>
                <tr>
                    <td>${stat.developerName}</td>
                    <td>${stat.countTask}</td>
                    <td>${stat.countNoDefectTask}</td>
                    <td>${stat.countReturn}</td>
                    <td>${stat.countReverts}</td>
                    <td>${stat.countDefect}</td>
                    <td>${stat.countHelpYes}</td>
                    <td>${stat.countHelpNo}</td>
                </tr>
            </#list>
        <#else>
            <tr>
                <td colspan="8">No data</td>
            </tr>
        </#if>
    </#list>
    </tbody>
</table>

</body>

<style>
    .page_header {
        position: fixed;
        z-index: 1;
        top: 0;
        width: 100%;
        height: 120px;
        background-color: white;
    }

    .table {
        position: relative;
        margin-top: 120px;
    }

    .table-fixed thead {
        position: sticky;
        top: 120px;
        background-color: #fff;
    }

    .table-fixed thead td {
        background-color: #fff;
    }

    .table-fixed thead:before {
        content: '';
        position: absolute;
        background: #ddd;
        display: block;
        width: 100%;
        height: 100%;
        z-index: -1;
    }

</style>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous">
</script>
</html>