create database ccondae;

use ccondae;

create table category (
    id int primary key,
    count int default 0,
    name varchar(255)
);