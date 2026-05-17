<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <link rel="preconnect" href="https://fonts.bunny.net" crossorigin>
    <link rel="dns-prefetch" href="https://fonts.bunny.net">
    <link href="https://fonts.bunny.net/css?family=Nunito+Sans" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/error_styles.css">

    <div class="error_block">
        <div class="main_block">
            <div class="picture">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 800 600" visibility="hidden" id="errorSvg">
                    <g>
                        <g id="planet">
                            <circle fill="none" stroke-width="3" stroke-miterlimit="10" cx="572.859" cy="108.803" r="90.788" class="dark"/>
                            <circle id="craterBig" fill="none" stroke-width="3" stroke-miterlimit="10" cx="548.891" cy="62.319" r="13.074" class="dark"/>
                            <circle id="craterSmall" fill="none" stroke-width="3" stroke-miterlimit="10" cx="591.743" cy="158.918" r="7.989" class="dark"/>
                        </g>
                    </g>
                </svg>
            </div>
            <div class="content">
                <h1>${status!500}</h1>
                <h2>${reason!'<@spring.message "error.generic"/>'}</h2>

                <#if details??>
                    <ul class="details">
                        <#if details.serverName??>
                            <li><span><@spring.message "error.host"/>:</span> <code>${details.serverName}</code></li>
                        </#if>
                        <#if details.requestURL??>
                            <li><span><@spring.message "error.uri"/>:</span> <code>${details.requestURL}</code></li>
                        </#if>
                        <#if details.serverPort??>
                            <li><span><@spring.message "error.port"/>:</span> <code>${details.serverPort}</code></li>
                        </#if>
                        <li><span><@spring.message "error.timestamp"/>:</span> <code>${.now}</code></li>
                    </ul>
                </#if>

                <a href="/" class="btn btn-primary mt-3"><@spring.message "error.go.home"/></a>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/gsap.min.js"
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="/static/js/error_js.js"></script>
</@main.layout>