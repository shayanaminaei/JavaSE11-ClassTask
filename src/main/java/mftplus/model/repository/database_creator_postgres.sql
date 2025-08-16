create table salaries (
    id int primary key  ,
    person_id INTEGER NOT NULL ,
    weekly_hour integer not null ,
    pay_per_hour integer not null ,
    start_date date not null ,
    end_date date not null ,
    employee_type varchar(20) not null
)