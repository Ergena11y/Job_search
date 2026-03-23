INSERT INTO  users (name, surname, age, email, password, phone_number, account_type)
values ('Tony', 'Saprano', 32, 'tonysap@gamil.com', 'trrrrwerrsap34', '+996777888',
        'APPLICANT'),
    ('ООО ТехКомпания', 'Somthing', 23, 'employer@techcorp.com', 'hardpassword',
     '+996555999', 'EMPLOYER');

INSERT INTO categories (name, parent_id)
VALUES ('IT', NULL),
       ('Backend разработка', 1),
       ('Frontend разработка', 1),
       ('Финансы', NULL),
       ('Бухгалтерия', 4);

INSERT INTO resumes (name, salary, is_active, created_date, update_time, category_id, applicant_id)
VALUES ('Java Backend разработчик', 80000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1),
       ('Fullstack разработчик', 100000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 1);

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id,
                       created_date, update_time)
VALUES ('Junior Java разработчик', 'Разработка backend сервисов на Java/Spring', 2, 70000, 0, 2, TRUE, 2,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Middle Frontend разработчик', 'Разработка UI на React/TypeScript', 3, 90000, 2, 4, TRUE, 2,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES (1, 1, TRUE),
       (2, 2, FALSE);

INSERT INTO contact_types (type)
VALUES ('Телефон'),
       ('Email'),
       ('LinkedIn'),
       ('GitHub');

INSERT INTO contacts_info (type_id, resume_id, contact_value)
VALUES (1, 1, '+996777888'),
       (2, 1, 'tonysap@gamil.com'),
       (4, 1, 'github.com/SAPRANO');

INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree)
VALUES (1, 'MIT', 'Computer since', '2014-09-01', '2018-06-30', 'Бакалавр');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES (1, 2, 'ООО СофтЛаб', 'Junior Java Developer',
        'Разработка REST API, участие в code review');

INSERT INTO messages (responded_applicant_id, content, time_stamp)
VALUES (1, 'Здравствуйте! Мы рассмотрели ваше резюме и хотим пригласить вас на собеседование.', CURRENT_TIMESTAMP),
       (1, 'Спасибо! Буду рад прийти на собеседование.', CURRENT_TIMESTAMP);