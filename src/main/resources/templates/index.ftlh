<#import "/spring.ftl" as spring>
<#import "./layout.ftlh" as main>
<@main.layout>

    <link rel="stylesheet" href="/static/css/landing.css">


    <div class="landing-hero">
        <div class="row w-100 align-items-center g-0">

            <div class="col-md-7 landing-hero__content">
                <p class="landing-hero__eyebrow">
                    <i class="bi bi-briefcase-fill me-1"></i> <@spring.message "index.hero.eyebrow"/>
                </p>
                <h1 class="landing-hero__title">
                    <@spring.message "index.hero.title"/>
                </h1>
                <p class="landing-hero__subtitle">
                    <@spring.message "index.hero.subtitle"/>
                </p>

                <div class="landing-hero__cta">
                    <#if user??>
                        <a href="<#if user.accountType == 'EMPLOYER'>/vacancies/create<#else>/vacancies</#if>"
                           class="btn-hero-primary">
                            <#if user.accountType == "EMPLOYER"><@spring.message "index.cta.create.vacancy"/><#else><@spring.message "index.cta.browse.vacancies"/></#if>
                        </a>
                        <a href="/profile" class="btn-hero-outline"><@spring.message "index.cta.my.profile"/></a>
                    <#else>
                        <a href="/auth/register" class="btn-hero-primary"><@spring.message "index.cta.register"/></a>
                        <a href="/auth/login"    class="btn-hero-outline"><@spring.message "nav.login"/></a>
                    </#if>
                </div>

                <div class="landing-stats">
                    <div class="stat-item">
                        <div class="stat-item__value"
                             data-counter="1000" data-suffix="+" data-duration="1400">0+</div>
                        <div class="stat-item__label"><@spring.message "index.stat.vacancies"/></div>
                    </div>
                    <div class="stat-item">
                        <div class="stat-item__value"
                             data-counter="500" data-suffix="+" data-duration="1200">0+</div>
                        <div class="stat-item__label"><@spring.message "index.stat.companies"/></div>
                    </div>
                    <div class="stat-item">
                        <div class="stat-item__value">24/7</div>
                        <div class="stat-item__label"><@spring.message "index.stat.access"/></div>
                    </div>
                </div>
            </div>

            <div class="col-md-5 landing-hero__illustration d-none d-md-block">
                <img src="/static/img/jobsearch-hero.png" alt="" class="img-fluid">
            </div>

        </div>
    </div>


    <section class="mb-5">
        <h2 class="section-title"><@spring.message "index.how.title"/></h2>
        <p class="section-subtitle"><@spring.message "index.how.subtitle"/></p>

        <div class="row g-4">
            <div class="col-md-4">
                <div class="step-card">
                    <div class="position-relative d-inline-block">
                        <div class="step-icon"><i class="bi bi-person-plus-fill"></i></div>
                        <div class="step-number">1</div>
                    </div>
                    <div class="step-card__title"><@spring.message "index.step1.title"/></div>
                    <p class="step-card__text">
                        <@spring.message "index.step1.text"/>
                    </p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="step-card">
                    <div class="position-relative d-inline-block">
                        <div class="step-icon"><i class="bi bi-file-earmark-person-fill"></i></div>
                        <div class="step-number">2</div>
                    </div>
                    <div class="step-card__title"><@spring.message "index.step2.title"/></div>
                    <p class="step-card__text">
                        <@spring.message "index.step2.text"/>
                    </p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="step-card">
                    <div class="position-relative d-inline-block">
                        <div class="step-icon"><i class="bi bi-chat"></i></div>
                        <div class="step-number">3</div>
                    </div>
                    <div class="step-card__title"><@spring.message "index.step3.title"/></div>
                    <p class="step-card__text">
                        <@spring.message "index.step3.text"/>
                    </p>
                </div>
            </div>
        </div>
    </section>


    <section class="mb-5">
        <h2 class="section-title"><@spring.message "index.for.whom.title"/></h2>
        <p class="section-subtitle"><@spring.message "index.for.whom.subtitle"/></p>

        <div class="row g-4">
            <div class="col-md-6">
                <div class="role-card role-card--applicant">
                    <i class="bi bi-person-workspace role-card__icon"></i>
                    <div class="role-card__title"><@spring.message "index.applicant.title"/></div>
                    <ul class="role-card__list">
                        <li><@spring.message "index.applicant.item1"/></li>
                        <li><@spring.message "index.applicant.item2"/></li>
                        <li><@spring.message "index.applicant.item3"/></li>
                        <li><@spring.message "index.applicant.item4"/></li>
                    </ul>
                    <#if user??>
                        <a href="/vacancies" class="btn btn-primary rounded-pill px-4">
                            <i class="bi bi-search me-1"></i><@spring.message "nav.vacancies"/>
                        </a>
                    <#else>
                        <a href="/auth/register" class="btn btn-primary rounded-pill px-4">
                            <i class="bi bi-rocket-takeoff me-1"></i><@spring.message "index.cta.start.search"/>
                        </a>
                    </#if>
                </div>
            </div>

            <div class="col-md-6">
                <div class="role-card role-card--employer">
                    <i class="bi bi-building role-card__icon"></i>
                    <div class="role-card__title"><@spring.message "index.employer.title"/></div>
                    <ul class="role-card__list">
                        <li><@spring.message "index.employer.item1"/></li>
                        <li><@spring.message "index.employer.item2"/></li>
                        <li><@spring.message "index.employer.item3"/></li>
                        <li><@spring.message "index.employer.item4"/></li>
                    </ul>
                    <#if user??>
                        <a href="/resumes" class="btn btn-warning rounded-pill px-4">
                            <i class="bi bi-people-fill me-1"></i><@spring.message "profile.resume.base"/>
                        </a>
                    <#else>
                        <a href="/auth/register" class="btn btn-warning rounded-pill px-4">
                            <i class="bi bi-plus-circle me-1"></i><@spring.message "index.cta.post.vacancy"/>
                        </a>
                    </#if>
                </div>
            </div>
        </div>
    </section>


    <#if !user??>
        <section class="landing-cta-bottom">
            <h2><@spring.message "index.cta.bottom.title"/></h2>
            <p><@spring.message "index.cta.bottom.subtitle"/></p>
            <div class="d-flex justify-content-center gap-3 flex-wrap">
                <a href="/auth/register" class="btn-hero-primary">
                    <i class="bi bi-person-plus me-1"></i><@spring.message "index.cta.register"/>
                </a>
                <a href="/vacancies" class="btn-hero-outline">
                    <i class="bi bi-eye me-1"></i><@spring.message "index.cta.browse.vacancies"/>
                </a>
            </div>
        </section>
    </#if>

    <script src="/static/js/landing.js" defer></script>

</@main.layout>
