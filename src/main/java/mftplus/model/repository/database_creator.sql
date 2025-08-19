create table persons
(
    id         number primary key,
    name       nvarchar2(20) not null,
    family     nvarchar2(20) not null,
    birth_date date          not null,
    role       nvarchar2(20) default 'customer',
    status     number(1)     default 1
);

create sequence person_seq start with 1 increment by 1;

--
create table cars
(
    id       number primary key,
    person_id references persons,
    name     nvarchar2(20)                 not null,
    brand    nvarchar2(20)                 not null,
    man_date DATE          DEFAULT SYSDATE NOT NULL,
    color    nvarchar2(20) default 'Black',
    status   number(1)     default 1
);

create sequence cars_seq start with 1 increment by 1;


create table educations
(
    id              number primary key,
    person_id references persons,
    university      nvarchar2(30) not null,
    education_grade nvarchar2(20) not null,
    average         number(4, 2)  not null,
    start_date      date          not null,
    end_date        date          not null
);

create sequence education_seq start with 1 increment by 1;

--


create table contacts
(
    id            number primary key,
    person_id references persons,
    contact_title nvarchar2(20) default 'Phone',
    contact_id    nvarchar2(20) not null,
    contact_type  nvarchar2(20) not null,
    description   nvarchar2(200),
    status        number(1)     default 1
);

create sequence contact_seq start with 1 increment by 1;

--


create table jobs
(
    id           number primary key,
    person_id references persons,
    organisation nvarchar2(20) not null,
    title        nvarchar2(20) default 'Employee',
    start_date   date          not null,
    end_date     date          not null,
    description  nvarchar2(200)
);

create sequence job_seq start with 1 increment by 1;

--

create table military_cards
(
    id           number primary key,
    person_id references persons,
    card_serial  nvarchar2(20) not null,
    license_type nvarchar2(20) not null,
    city         nvarchar2(20),
    organisation nvarchar2(20),
    duration     number(3)

);

create sequence military_cards start with 1 increment by 1;

--

create table skills
(
    id            number primary key,
    person_id references persons,
    title         nvarchar2(40) not null,
    institute     nvarchar2(40) not null,
    duration      number        not null,
    register_date date,
    score         number        not null
);

create sequence skill_seq start with 1 increment by 1;

--


create table medicals
(
    id         number primary key,
    person_id references persons,
    disease    nvarchar2(50) not null,
    medicine   nvarchar2(50) not null,
    doctor     nvarchar2(50) not null,
    visit_date date          not null,
    status     number(1) default 1
);

create sequence medical_seq start with 1 increment by 1;

--

create table sim_cards
(
    id            number primary key,
    person_id references persons,
    title         nvarchar2(20)        not null,
    numbers       nvarchar2(20) unique not null,
    operator      nvarchar2(20)        not null,
    register_date date                 not null,
    status        number(1) default 1
);

create sequence sim_card_seq start with 1 increment by 1;

--

create table properties
(
    id        number primary key,
    person_id references persons,
    name      nvarchar2(20)        not null,
    brand     nvarchar2(20)        not null,
    serial    nvarchar2(20) unique not null,
    count     number    default 0,
    date_time timestamp default sysdate
);

create sequence property_seq start with 1 increment by 1;

---

create table lessons
(
  id           number primary key ,
  person_id    references persons,
  title        nvarchar2(20)   not null ,
  code         number not null ,
  teacher      nvarchar2(20)  not null ,
  unit         nvarchar2(20)  not null ,
  start_date_time date not null
);

create sequence lesson_seq start with 1 increment by 1;

---


