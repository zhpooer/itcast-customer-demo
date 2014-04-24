create table if not exists customer (
    id varchar(100) primary key,
    name varchar(100),
    gender varchar(10),
    birthday date,
    cellphone varchar(100),
    email varchar(100),
    hobby varchar(100),
    type varchar(100),
    description varchar(100)
);
