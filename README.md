Job Search Platform
Веб-приложение для поиска работы и сотрудников, аналог HeadHunter.
Построено на Java + Spring Boot с поддержкой ролей, чата и мультиязычности.
Функциональность

-Для соискателей (APPLICANT)

Регистрация и вход
Создание и редактирование резюме (с опытом работы и образованием)
Просмотр вакансий и отклик на них
Чат с работодателем через WebSocket

-Для работодателей (EMPLOYER)

Создание и управление вакансиями
Просмотр откликов на вакансии
Чат с соискателями

-Технологии
КатегорияТехнологииBackendJava 17, 
Spring Boot, 
Spring Security, 
Spring Data JPAБаза данныхPostgreSQL, 
Liquibase (миграции)ШаблоныFreeMarkerReal-timeWebSocket (STOMP)
Интернационализацияi18n (русский / английский)
СборкаMaven

Запуск проекта:

Требования
Java 17+
PostgreSQL
Maven

Шаги:

1-Клонировать репозиторий:

git clone https://github.com/Ergena11y/Job_search.git
cd Job_search

2-Создать базу данных PostgreSQL:

sqlCREATE DATABASE job_search;

3-Настроить application.properties:

propertiesspring.datasource.url=jdbc:h2:./db/job_search;AUTO_SERVER=TRUE
spring.datasource.username=твой_пользователь
spring.datasource.password=твой_пароль

application.yaml:

datasource:
    url: jdbc:h2:./db/job_search;AUTO_SERVER=TRUE
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    driver-class-name: org.h2.Driver


Запустить:

mvn spring-boot:run

Открыть в браузере: http://localhost:8090

Автор
Ergen — Java Backend Developer
Telegram: @Ervyn_x
