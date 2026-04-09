<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="display-6">База резюме</h2>
            <a href="/resumes/create" class="btn btn-primary">Разместить резюме</a>
        </div>


        <div class="card mb-4 shadow-sm">
            <div class="card-body">
                <form class="row g-3">
                    <div class="col-md-8">
                        <input type="text" class="form-control" placeholder="Поиск по должности или навыкам...">
                    </div>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-outline-secondary w-100">Найти</button>
                    </div>
                </form>
            </div>
        </div>


        <div class="row">

            <div class="col-12 mb-3">
                <div class="card shadow-sm h-100">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <h5 class="card-title text-primary">Java Developer (Middle)</h5>
                            <span class="badge bg-success">150 000 сом</span>
                        </div>
                        <h6 class="card-subtitle mb-2 text-muted">Иван Иванов • Опыт: 3 года</h6>
                        <p class="card-text">Ключевые навыки: Spring Boot, Hibernate, PostgreSQL, Docker, Git. Ищу работу в стабильной IT-компании.</p>

                        <div class="d-flex justify-content-between align-items-center mt-3">
                            <div class="btn-group">
                                <a href="#" class="btn btn-sm btn-outline-primary">Посмотреть полностью</a>
                                <button type="button" class="btn btn-sm btn-outline-success">Пригласить</button>
                            </div>
                            <small class="text-muted">Обновлено: 2 часа назад</small>
                        </div>
                    </div>
                </div>
            </div>


            <div class="col-12 mb-3">
                <div class="card shadow-sm h-100">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <h5 class="card-title text-primary">Frontend Developer (React)</h5>
                            <span class="badge bg-success">120 000 сом</span>
                        </div>
                        <h6 class="card-subtitle mb-2 text-muted">Мария Петрова • Опыт: 2 года</h6>
                        <p class="card-text">Стек: JavaScript (ES6+), React, Redux, TypeScript, Tailwind CSS. Есть опыт адаптивной верстки.</p>

                        <div class="d-flex justify-content-between align-items-center mt-3">
                            <div class="btn-group">
                                <a href="#" class="btn btn-sm btn-outline-primary">Посмотреть полностью</a>
                                <button type="button" class="btn btn-sm btn-outline-success">Пригласить</button>
                            </div>
                            <small class="text-muted">Обновлено: Вчера</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>