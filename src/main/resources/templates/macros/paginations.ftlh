<#macro pagination url page>

    <#if page.totalPages gt 4>
        <#assign
        totalPages = page.totalPages - 1
        currentPage = page.number

        state1 = (currentPage <= 1)?then([0, 1, -1, totalPages], [])
        state2 = (currentPage > 1 && currentPage < totalPages - 1)?then([0, -1, currentPage, -1, totalPages], [])
        state3 = (currentPage >= totalPages - 1 && currentPage <= totalPages)?then([0, -1, totalPages - 1, totalPages], [])
        paginationBody = state1 + state2 + state3
        >
    <#else>
        <#assign paginationBody = 0..page.totalPages - 1>
    </#if>

    <div class="container-fluid mb-2">
        <nav>
            <ul class="pagination pagination-sm">
                <li class="page-item <#if (page.number lte 0)>disabled</#if>">
                    <a class="page-link"
                       href="${url}?page=${page.number - 1}">
                        <span class="sr-only">Previous</span>
                    </a>
                </li>

                <#list paginationBody as p>
                    <#if p == page.number>
                        <li class="page-item active">
                            <a class="page-link">${p + 1}</a>
                        </li>
                    <#elseif p == -1>
                        <li class="page-item disabled">
                            <a class="page-link">...</a>
                        </li>
                    <#else>
                        <li class="page-item">
                            <a class="page-link" href="${url}?page=${p}">${p + 1}</a>
                        </li>
                    </#if>
                </#list>

                <#if (page.number lt page.totalPages - 1)>
                    <li class="page-item">
                        <a class="page-link" href="${url}?page=${page.number + 1}">
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </#if>

            </ul>
        </nav>
    </div>

</#macro>