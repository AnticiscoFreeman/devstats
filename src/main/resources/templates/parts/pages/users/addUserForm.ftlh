<div class="container-fluid mb-2">
    <div class="col-8 mb-2">
        <form method="post" action="/accounts/add">
            <div class="input-group input-group-sm mb-2">
                <label class="input-group-text">Registration New Account</label>
                <span class="input-group-text" style="width: 100px">Username</span>
                <input type="text" class="form-control" name="username" required>
                <span class="input-group-text" style="width: 100px">Password</span>
                <input type="password" class="form-control" name="password" required>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Create New User</button>
            </div>
        </form>
    </div>
</div>