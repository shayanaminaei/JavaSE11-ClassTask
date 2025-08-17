create table if not exists salaries (
    id int primary key  ,
    person_id INTEGER NOT NULL ,
    weekly_hour integer not null ,
    pay_per_hour integer not null ,
    start_date date not null ,
    end_date date not null ,
    employee_type varchar(20) not null
);

Create table if not exists persons(
        id         INTEGER PRIMARY KEY,
        name       VARCHAR(50)  NOT NULL,
        family     VARCHAR(50)  NOT NULL,
        birth_date DATE         NOT NULL,
        role       VARCHAR(20)  NOT NULL DEFAULT 'customer',
        status     SMALLINT     NOT NULL DEFAULT 1 CHECK (status IN (0,1))
);


CREATE TABLE marriages (
                           id             BIGSERIAL PRIMARY KEY,
                           person_id      BIGINT      NOT NULL REFERENCES persons(id) ON DELETE CASCADE,
                           name           VARCHAR(50) NOT NULL,
                           family         VARCHAR(50) NOT NULL,
                           marriage_date  DATE        NOT NULL,
                           is_alive       BOOLEAN     NOT NULL DEFAULT TRUE,
                           children       INTEGER     NOT NULL DEFAULT 0 CHECK (children >= 0)
);

CREATE INDEX idx_marriages_person_id ON marriages(person_id);
