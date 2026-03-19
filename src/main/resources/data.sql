CREATE TABLE IF NOT EXISTS users (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(50)  NOT NULL,
    surname      VARCHAR(50)  NOT NULL,
    age          INT   NOT NULL,
    email        VARCHAR(50) NOT NULL UNIQUE,
    password     VARCHAR(50) NOT NULL,
    phone_number VARCHAR(11) ,
    avatar       VARCHAR(150),
    account_type VARCHAR(20)  NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
    id  INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    parent_id INT REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS resumes (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100)   NOT NULL,
    salary       DECIMAL(10, 2) NOT NULL,
    is_active    BOOLEAN        NOT NULL DEFAULT TRUE,
    created_date TIMESTAMP      NOT NULL,
    update_time  TIMESTAMP      NOT NULL,
    category_id  INT REFERENCES categories (id),
    applicant_id INT REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS vacancies (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(45)   NOT NULL,
    description  text,
    category_id  INT REFERENCES categories (id),
    salary       DECIMAL(10, 2) NOT NULL,
    exp_from     INT  NOT NULL,
    exp_to       INT  NOT NULL,
    is_active    BOOLEAN    NOT NULL DEFAULT TRUE,
    author_id    INT REFERENCES users (id),
    created_date TIMESTAMP   NOT NULL,
    update_time  TIMESTAMP   NOT NULL
);

CREATE TABLE IF NOT EXISTS responded_applicants (
    id  INT AUTO_INCREMENT PRIMARY KEY,
    resume_id    INT     REFERENCES resumes (id),
    vacancy_id   INT     REFERENCES vacancies (id),
    confirmation BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS contact_types (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS contacts_info (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    type_id   INT REFERENCES contact_types (id),
    resume_id INT REFERENCES resumes (id),
    value     VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS education_info (
    id   INT AUTO_INCREMENT PRIMARY ,
    resume_id   INT REFERENCES resumes (id),
    institution VARCHAR(200),
    program     VARCHAR(200),
    start_date  DATE,
    end_date    DATE,
    degree      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS work_experience_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    resume_id    INT REFERENCES resumes (id),
    years  INT,
    company_name     VARCHAR(90),
    position         VARCHAR(60),
    responsibilities text
    );

CREATE TABLE IF NOT EXISTS messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    responded_applicant_id INT REFERENCES responded_applicants (id),
    content  TEXT,
    time_stamp   TIMESTAMP NOT NULL
    );


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

INSERT INTO contacts_info (type_id, resume_id, value)
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