-- liquibase formatted sql

-- changeset yakhazova:create-table-source
create table if not exists rental.source
(
    id          integer primary key,
    "name"      varchar(100)  not null unique,
    description varchar(1000) not null
)