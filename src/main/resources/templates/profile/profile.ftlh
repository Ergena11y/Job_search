<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-4">
                <div class="card text-center shadow-sm">
                    <div class="card-body">
                        <img src="/images/avatar-placeholder.png" class="rounded-circle mb-3" width="150" alt="Avatar">
                        <h4>${user.name}</h4>
                        <p class="text-muted">${user.email}</p>
                        <form action="/profile/profile-edit" method="post">
                            <input type="hidden" name="userId" value="${user.id}">
                            <a href="/profile/edit?userId=${user.id}" class="btn btn-outline-primary btn-sm">Редактировать профиль</a>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-header bg-white">
                        <h5>Активность</h5>
                    </div>
                    <div class="card-body">
                        <p>Здесь будут отображаться ваши вакансии или отклики.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>