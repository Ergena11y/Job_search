<#import "/spring.ftl" as spring>
<#import "layout.ftlh" as main>
<@main.layout>

    <div class="row justify-content-center mt-5">
        <div class="col-md-6 col-lg-5">
            <div class="card border-0 shadow-sm text-center">
                <div class="card-body p-5">

                    <div class="display-1 fw-bold text-primary mb-3">
                        ${status!500}
                    </div>

                    <h1 class="h3 fw-bold mb-2">
                        <#if status?? && status == 404>
                            <@spring.message "error.404.title"/>
                        <#elseif status?? && status == 403>
                            <@spring.message "error.403.title"/>
                        <#elseif status?? && status == 400>
                            <@spring.message "error.generic"/>
                        <#elseif status?? && status == 500>
                            <@spring.message "error.500.title"/>
                        <#else>
                            <@spring.message "error.generic"/>
                        </#if>
                    </h1>

                    <p class="text-muted mb-4">
                        <#if status?? && status == 404>
                            <@spring.message "error.404.description"/>
                        <#elseif status?? && status == 403>
                            <@spring.message "error.403.description"/>
                        <#elseif status?? && status == 500>
                            <@spring.message "error.500.description"/>
                        <#else>
                            <@spring.message "error.generic"/>
                        </#if>
                    </p>

                    <a href="/" class="btn btn-primary">
                        <i class="bi bi-house me-1"></i><@spring.message "error.go.home"/>
                    </a>

                </div>
            </div>
        </div>
    </div>

</@main.layout>