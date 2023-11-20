<label class="input-group-text" style="width:150px">${task.number}</label>
<label class="input-group-text" style="width:200px">${task.developer}</label>
<label class="input-group-text">Returns</label>
<label class="input-group-text" style="width:50px">${task.countReturn}</label>
<label class="input-group-text">Re-Returns</label>
<label class="input-group-text" style="width:50px">${task.countRevert}</label>
<label class="input-group-text">Defects</label>
<label class="input-group-text" style="width:50px">${task.countDefect}</label>
<label class="input-group-text">Developer Help</label>
<label class="input-group-text" style="width:50px">
    <#if task.devDescription == -1>
        NO
    <#elseif task.devDescription == 1>
        YES
    <#else>
        -
    </#if>
</label>
<label class="input-group-text">Add Date</label>
<label class="input-group-text" style="width:100px">${task.taskDate}</label>