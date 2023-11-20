<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Yearly Chart Statistic</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <#if isView == true>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link type="text/css" rel="StyleSheet" href="https://bootstraptema.ru/plugins/2016/shieldui/style.css"/>
        <script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
        <script src="https://bootstraptema.ru/plugins/2016/shieldui/script.js"></script>
    </#if>

</head>
<body>

<#include "parts/support/headerMenu.ftlh">
<#include "parts/support/messageBlock.ftlh">

<div class="container-fluid">
    <h1>Yearly Chart Statistic</h1>
    <div class="col">
        <div class="input-group input-group-sm mb-1">
            <#list availableYear as yearItem>
                <a href="/statistics/chart/${yearItem}" style="margin-left: 5px">
                    <button type="submit" class="btn btn-primary btn-sm">${yearItem}</button>
                </a>
            </#list>
        </div>
    </div>
</div>

<#if isView == true>
    <#include "parts/pages/graph/graphBlock.ftlh">
</#if>

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