<#import "macros/common.ftlh" as common>
<@common.page title="Login">

<div class="container-fluid mb-2">
    <h1>Login</h1>
</div>

<div class="container-fluid mb-2">
    <div class="col-4 mb-2">
        <form method="post">
            <div class="input-group input-group-sm mb-2">
                <span class="input-group-text" style="width: 100px">Username</span>
                <input type="text" class="form-control" name="username" required>
            </div>
            <div class="input-group input-group-sm mb-2">
                <span class="input-group-text" style="width: 100px">Password</span>
                <input type="password" class="form-control" name="password" required>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>

</@common.page>