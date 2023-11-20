<div class="container-fluid mb-2">
    <div class="col-6">
        <form method="post" action="/developer/${developer.id}">
            <div class="input-group input-group-sm mb-1">
                <span class="input-group-text">Task #</span>
                <input type="text" class="form-control"
                       placeholder="Enter Task Num"
                       name="number"
                       maxlength="20"
                       required>
                <label class="input-group-text" for="projects" style="width: 75px">Project</label>
                <select class="form-select" name="project">
                    <#list projectList as project>
                        <option value="${project.id}">${project.name}</option>
                    </#list>
                </select>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-outline-primary btn-sm">Add New Task</button>
            </div>
        </form>
    </div>
</div>