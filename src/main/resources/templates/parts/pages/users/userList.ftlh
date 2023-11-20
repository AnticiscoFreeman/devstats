<#list accountsList as account>
    <div class="col-8">
        <form method="post" action="/accounts/update">
            <div class="input-group input-group-sm">
                <label class="input-group-text">Id</label>
                <label class="input-group-text" style="width: 50px">${account.id}</label>
                <label class="input-group-text">Username</label>
                <input type="text"
                       class="form-control"
                       placeholder="username"
                       name="username"
                       value="${account.username}"
                       required>
                <label class="input-group-text">Set New Password</label>
                <input type="password"
                       class="form-control"
                       placeholder="Set New Password"
                       name="password">
                <label class="input-group-text">Name</label>
                <input type="text"
                       class="form-control"
                       placeholder="name"
                       name="name"
                       value="${account.name}"
                       required>
                <label class="input-group-text">Role</label>
                <label class="input-group-text">
                    <#list allRolesList as role>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input"
                                   type="radio"
                                   name="roles"
                                   id="${role}"
                                   value="${role}"
                                    ${account.roles?seq_contains(role)?string("checked", "")}>
                            <label class="form-check-label" for="${role}">${role}</label>
                        </div>
                    </#list>
                </label>
                <label class="input-group-text">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input"
                               type="checkbox"
                               id="deleteCheckbox"
                               name="deleteAccount">
                        <label class="form-check-label" for="deleteCheckbox">Delete?</label>
                    </div>
                </label>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="accountId" value="${account.id}"/>
                <button type="submit" class="btn btn-outline-success btn-sm">Update Account</button>
            </div>
        </form>
    </div>
</#list>