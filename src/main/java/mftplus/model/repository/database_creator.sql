create table persons
(
    id         number unique not null,
    name       nvarchar2(20) not null,
    family     nvarchar2(20) not null,
    birth_date date          not null,
    role       nvarchar2(20) default 'customer',
    status     number(1)     default 1
);

create sequence person_seq start with 1 increment by 1;

--

