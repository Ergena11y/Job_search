<#import "/spring.ftl" as spring>
<#import "./layout.ftlh" as main>
<@main.layout>

    <link rel="stylesheet" href="/static/css/landing.css">


    <div class="landing-hero">
        <div class="row w-100 align-items-center g-0">

            <div class="col-md-7 landing-hero__content">
                <p class="landing-hero__eyebrow">
                    <i class="bi bi-briefcase-fill me-1"></i> Платформа поиска работы
                </p>
                <h1 class="landing-hero__title">
                    Найди работу <span>мечты</span><br>или лучший талант
                </h1>
                <p class="landing-hero__subtitle">
                    Тысячи вакансий и резюме в одном месте. Соискатели находят работу быстрее,
                    работодатели — нужных людей.
                </p>

                <div class="landing-hero__cta">
                    <#if user??>
                        <a href="<#if user.accountType == 'EMPLOYER'>/vacancies/create<#else>/vacancies</#if>"
                           class="btn-hero-primary">
                            <#if user.accountType == "EMPLOYER">Создать вакансию<#else>Смотреть вакансии</#if>
                        </a>
                        <a href="/profile" class="btn-hero-outline">Мой профиль</a>
                    <#else>
                        <a href="/auth/register" class="btn-hero-primary">Зарегистрироваться бесплатно</a>
                        <a href="/auth/login"    class="btn-hero-outline">Войти</a>
                    </#if>
                </div>

                <div class="landing-stats">
                    <div class="stat-item">
                        <div class="stat-item__value"
                             data-counter="1000" data-suffix="+" data-duration="1400">0+</div>
                        <div class="stat-item__label">Вакансий</div>
                    </div>
                    <div class="stat-item">
                        <div class="stat-item__value"
                             data-counter="500" data-suffix="+" data-duration="1200">0+</div>
                        <div class="stat-item__label">Компаний</div>
                    </div>
                    <div class="stat-item">
                        <div class="stat-item__value">24/7</div>
                        <div class="stat-item__label">Доступ</div>
                    </div>
                </div>
            </div>

            <div class="col-md-5 landing-hero__illustration d-none d-md-block">
                <img src="/static/img/jobsearch-hero.png" alt="" class="img-fluid">
            </div>

        </div>
    </div>


    <section class="mb-5">
        <h2 class="section-title">Как это работает</h2>
        <p class="section-subtitle">Три шага до новой работы или лучшего кандидата</p>

        <div class="row g-4">
            <div class="col-md-4">
                <div class="step-card">
                    <div class="position-relative d-inline-block">
                        <div class="step-icon"><i class="bi bi-person-plus-fill"></i></div>
                        <div class="step-number">1</div>
                    </div>
                    <div class="step-card__title">Создай аккаунт</div>
                    <p class="step-card__text">
                        Зарегистрируйся как соискатель или работодатель. Займёт меньше минуты.
                    </p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="step-card">
                    <div class="position-relative d-inline-block">
                        <div class="step-icon"><i class="bi bi-file-earmark-person-fill"></i></div>
                        <div class="step-number">2</div>
                    </div>
                    <div class="step-card__title">Заполни профиль</div>
                    <p class="step-card__text">
                        Добавь резюме с опытом и образованием — или создай вакансию с описанием и зарплатой.
                    </p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="step-card">
                    <div class="position-relative d-inline-block">
                        <div class="step-icon"><i class="bi bi-chat"></i></div>
                        <div class="step-number">3</div>
                    </div>
                    <div class="step-card__title">Откликайся и общайся</div>
                    <p class="step-card__text">
                        Откликнись на вакансию и общайся напрямую с работодателем через встроенный чат.
                    </p>
                </div>
            </div>
        </div>
    </section>


    <section class="mb-5">
        <h2 class="section-title">Для кого платформа</h2>
        <p class="section-subtitle">Одно решение — для обеих сторон</p>

        <div class="row g-4">
            <div class="col-md-6">
                <div class="role-card role-card--applicant">
                    <i class="bi bi-person-workspace role-card__icon"></i>
                    <div class="role-card__title">Соискателям</div>
                    <ul class="role-card__list">
                        <li>Просматривай тысячи актуальных вакансий</li>
                        <li>Создавай и редактируй резюме онлайн</li>
                        <li>Откликайся в один клик</li>
                        <li>Общайся с работодателями в чате</li>
                    </ul>
                    <#if user??>
                        <a href="/vacancies" class="btn btn-primary rounded-pill px-4">
                            <i class="bi bi-search me-1"></i>Вакансии
                        </a>
                    <#else>
                        <a href="/auth/register" class="btn btn-primary rounded-pill px-4">
                            <i class="bi bi-rocket-takeoff me-1"></i>Начать поиск
                        </a>
                    </#if>
                </div>
            </div>

            <div class="col-md-6">
                <div class="role-card role-card--employer">
                    <i class="bi bi-building role-card__icon"></i>
                    <div class="role-card__title">Работодателям</div>
                    <ul class="role-card__list">
                        <li>Публикуй вакансии без лишних шагов</li>
                        <li>Просматривай базу резюме соискателей</li>
                        <li>Одобряй или отклоняй кандидатов</li>
                        <li>Ведите переговоры прямо на платформе</li>
                    </ul>
                    <#if user??>
                        <a href="/resumes" class="btn btn-warning rounded-pill px-4"
                           style="color:#0d47a1; font-weight:700;">
                            <i class="bi bi-people-fill me-1"></i>База резюме
                        </a>
                    <#else>
                        <a href="/auth/register" class="btn btn-warning rounded-pill px-4"
                           style="color:#0d47a1; font-weight:700;">
                            <i class="bi bi-plus-circle me-1"></i>Разместить вакансию
                        </a>
                    </#if>
                </div>
            </div>
        </div>
    </section>


    <#if !user??>
        <section class="landing-cta-bottom">
            <h2>Готов начать?</h2>
            <p>Регистрация бесплатна и занимает меньше минуты</p>
            <div class="d-flex justify-content-center gap-3 flex-wrap">
                <a href="/auth/register" class="btn-hero-primary">
                    <i class="bi bi-person-plus me-1"></i>Зарегистрироваться
                </a>
                <a href="/vacancies" class="btn-hero-outline">
                    <i class="bi bi-eye me-1"></i>Смотреть вакансии
                </a>
            </div>
        </section>
    </#if>

    <script src="/static/js/landing.js" defer></script>

</@main.layout>
