-- liquibase formatted sql

-- changeset yakhazova:create-table-status
create table if not exists rental.status
(
    id          integer primary key,
    "name"      varchar(100)  not null unique,
    description varchar(1000) not null
)