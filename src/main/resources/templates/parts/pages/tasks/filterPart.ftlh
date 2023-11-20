<div class="container-fluid mb-2">
    <div class="col-8">
        <form method="post" action="/tasks">
            <div class="input-group input-group-sm">
                <label class="input-group-text">Start Period</label>
                <input type="date" class="form-control" name="start">
                <label class="input-group-text">End Period</label>
                <input type="date" class="form-control" name="end">
                <select class="form-select" name="project">
                    <option value="0">All</option>
                    <#list projectList as project>
                        <option value=${project.id}>${project.name}</option>
                    </#list>
                </select>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-outline-primary btn-sm">Filter</button>
            </div>
        </form>
    </div>
</div>

<div class="container-fluid mb-2">
    <div class="col-4">
        <form method="post" action="/tasks/search">
            <div class="input-group input-group-sm">
                <label class="input-group-text">Find Task Name</label>
                <input type="text" class="form-control" name="searchTask">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-outline-primary btn-sm">Search</button>
            </div>
        </form>
    </div>
</div>