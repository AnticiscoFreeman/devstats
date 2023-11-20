<div class="container text-center">
    <div class="row">
        <div class="col">
            <#if tasksBefore?has_content>
                <#list tasksBefore as taskBefore>
                    <div class="row">
                        <div class="col">
                            ${taskBefore.id} ${taskBefore.number} ${taskBefore.taskDate}
                        </div>
                    </div>
                </#list>
            <#else>
                <div class="row">
                    <div class="col-6 mb-2">
                        tasksBefore is Empty
                    </div>
                </div>
            </#if>
        </div>
        <div class="col">
            <#if tasksAfter?has_content>
                <#list tasksAfter as taskAfter>
                    <div class="row">
                        <div class="col">
                            ${taskAfter.id} ${taskAfter.number} ${taskAfter.taskDate}
                        </div>
                    </div>
                </#list>
            <#else>
                <div class="row">
                    <div class="col-6 mb-2">
                        tasksAfter is Empty
                    </div>
                </div>
            </#if>
        </div>
    </div>
</div>