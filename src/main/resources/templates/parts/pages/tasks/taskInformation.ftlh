<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Номер задачи" style="width:150px">${task.number}</label>
<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Название проекта" style="width:200px">${task.project}</label>
<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Ответственный разработчик" style="width:200px">${task.developer}</label>
<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Количество возвратов">Returns</label>
<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Количество возвратов" style="width:50px">${task.countReturn}</label>
<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Количество мгновенных возвратов">Reverts</label>
<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Количество мгновенных возвратов" style="width:50px">${task.countRevert}</label>
<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Количество дефектов">Defects</label>
<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Количество дефектов" style="width:50px">${task.countDefect}</label>
<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Помощь разработчика по задаче">Developer Help</label>
<label class="input-group-text" style="width:50px">
        <#if task.devDescription == -1>
                NO
        <#elseif task.devDescription == 1>
                YES
        <#else>
                -
        </#if>
</label>
<label class="input-group-text" data-bs-toggle="tooltip" data-bs-html="true" title="Дата добавления задачи в систему">Add Date</label>
<label class="input-group-text"  data-bs-toggle="tooltip" data-bs-html="true" title="Дата добавления задачи в систему" style="width:100px">${task.taskDate}</label>