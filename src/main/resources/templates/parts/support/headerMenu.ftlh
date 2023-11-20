<div class="container-fluid mb-2">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbar">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/">Home</a>
                    </li>
                    <li class="nav-item <#if projectList?has_content>dropdown</#if>">
                        <#if projectList?has_content>
                            <a class="nav-link dropdown-toggle"
                               role="button"
                               data-bs-toggle="dropdown"
                               aria-expanded="false"
                               href="/projects">
                                Projects
                            </a>
                        <#else>
                            <a class="nav-link" aria-current="page" href="/projects">Projects</a>
                        </#if>
                        <#if projectList?has_content>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="/projects">All Projects</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <#list projectList as project>
                                    <li><a class="dropdown-item" href="/project/${project.id}">${project.name}</a></li>
                                </#list>
                            </ul>
                        </#if>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/developers">Developers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/tasks">Tasks</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle"
                           role="button"
                           data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Statistics
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/statistics/devs">Single Dev Statistic</a></li>
                            <li><a class="dropdown-item" href="/statistics/main">All Devs Statistic</a></li>
                            <li><a class="dropdown-item" href="/statistics/chart">Yearly Chart Statistic</a></li>
                        </ul>
                    </li>
                    <#if account.adminRole == true>
                        <li class="nav-item">
                            <a class="nav-link" href="/accounts">Accounts Setup</a>
                        </li>
                    </#if>
                </ul>
                <form method="post" action="/logout" class="d-flex">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button class="btn btn-outline-success" type="submit">Logout</button>
                </form>
            </div>
        </div>
    </nav>
</div>