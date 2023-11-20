<div class="container-fluid mb-2">
    <div class="col-8">
        <form method="post" action="/developers/add">
            <div class="input-group input-group-sm mb-1">
                <label class="input-group-text" style="width: 75px">Name</label>
                <input type="text" class="form-control"
                       placeholder="Enter Dev Name"
                       maxlength="50"
                       name="name" required>
                <label class="input-group-text" style="width: 75px">Surname</label>
                <input type="text" class="form-control"
                       placeholder="Enter Dev Surname"
                       maxlength="50"
                       name="surname" required>
                <label class="input-group-text" for="typeList" style="width: 75px">Type</label>
                <select class="form-select" id="typeList" name="devType">
                    <#list developersType as type>
                        <option value="${type}">${type}</option>
                    </#list>
                </select>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-outline-primary btn-sm">Create New developer</button>
            </div>

        </form>
    </div>
</div>