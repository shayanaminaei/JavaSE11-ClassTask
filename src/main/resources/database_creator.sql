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


create table users
(
    id            number primary key,
    person_id     number,
    username      nvarchar2(20) not null unique,
    password      nvarchar2(20) not null,
    nick_name     nvarchar2(20) not null,
    locked        number(1) default 0,
    register_date date default sysdate,
    constraint fk_user_person FOREIGN KEY (person_id) references persons (id)

);

create sequence user_seq start with 1 increment by 1;

create table cars
(
    id        number unique                 not null,
    person_id number,
    name      nvarchar2(20)                 not null,
    brand     nvarchar2(20)                 not null,
    man_date  DATE          DEFAULT SYSDATE NOT NULL,
    color     nvarchar2(20) default 'Black',
    plate     nvarchar2(10),
    constraint fk_car_person FOREIGN KEY (person_id) references persons (id)
);

create sequence cars_seq start with 1 increment by 1;


create table educations
(
    id              number primary key,
    person_id       number,
    university      nvarchar2(30) not null,
    education_grade nvarchar2(20) not null,
    average         number(4, 2)  not null,
    start_date      date          not null,
    end_date        date          not null,
    constraint fk_edu_person FOREIGN KEY (person_id) references persons (id)
);

create sequence education_seq start with 1 increment by 1;


create table contacts
(
    id            number primary key,
    person_id     number,
    contact_title nvarchar2(20) default 'Phone',
    contact_id    nvarchar2(20) not null,
    contact_type  nvarchar2(20) not null,
    description   nvarchar2(200),
    status        number(1)     default 1,
    constraint fk_contact_person FOREIGN KEY (person_id) references persons (id)
);

create sequence contact_seq start with 1 increment by 1;


create table jobs
(
    id           number primary key,
    person_id    number,
    organisation nvarchar2(20) not null,
    title        nvarchar2(20) default 'Employee',
    start_date   date          not null,
    end_date     date          not null,
    description  nvarchar2(200),
    constraint fk_job_person FOREIGN KEY (person_id) references persons (id)
);

create sequence job_seq start with 1 increment by 1;


create table military_cards
(
    id           number primary key,
    person_id    number,
    card_serial  nvarchar2(20) not null,
    license_type nvarchar2(20) not null,
    city         nvarchar2(20),
    organisation nvarchar2(20),
    duration     number(3),
    constraint fk_military_person FOREIGN KEY (person_id) references persons (id)

);

create sequence military_card_seq start with 1 increment by 1;


create table skills
(
    id            number primary key,
    person_id     number,
    title         nvarchar2(40) not null,
    institute     nvarchar2(40) not null,
    duration      number        not null,
    register_date date,
    score         number        not null,
    constraint fk_skill_person FOREIGN KEY (person_id) references persons (id)
);

create sequence skill_seq start with 1 increment by 1;


create table medicals
(
    id         number primary key,
    person_id  number,
    disease    nvarchar2(50) not null,
    medicine   nvarchar2(50) not null,
    doctor     nvarchar2(50) not null,
    visit_date date          not null,
    status     number(1) default 1,
    constraint fk_medical_person FOREIGN KEY (person_id) references persons (id)
);

create sequence medical_seq start with 1 increment by 1;


create table sim_cards
(
    id            number primary key,
    person_id     number,
    title         nvarchar2(20)        not null,
    numbers       nvarchar2(20) unique not null,
    operator      nvarchar2(20)        not null,
    register_date date                 not null,
    status        number(1) default 1,
    constraint fk_sim_person FOREIGN KEY (person_id) references persons (id)
);

create sequence sim_card_seq start with 1 increment by 1;


create table properties
(
    id        number primary key,
    person_id number,
    name      nvarchar2(20)        not null,
    brand     nvarchar2(20)        not null,
    serial    nvarchar2(20) unique not null,
    count     number    default 0,
    date_time timestamp default sysdate,
    constraint fk_property_person FOREIGN KEY (person_id) references persons (id)
);

create sequence property_seq start with 1 increment by 1;


create table driver_license
(
    id            number primary key,
    person_id     number,
    serial        nvarchar2(20) not null,
    license_type  nvarchar2(20) not null,
    city          nvarchar2(20) not null,
    register_date date,
    expire_date   date,
    constraint fk_license_person FOREIGN KEY (person_id) references persons (id)

);

create sequence license_seq start with 1 increment by 1;

CREATE TABLE marriages (
        id             number PRIMARY KEY,
        person_id      number      ,
        name           VARCHAR(50) NOT NULL,
        family         VARCHAR(50) NOT NULL,
        marriage_date  DATE        NOT NULL,
        is_alive       number(1)    ,
        children       INTEGER     ,
        constraint fk_marriage_person FOREIGN KEY (person_id) references persons (id)

);

create sequence marriage_seq start with 1 increment by 1;
