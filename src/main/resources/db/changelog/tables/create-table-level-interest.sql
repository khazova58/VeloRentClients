-- liquibase formatted sql

-- changeset yakhazova:create-table-level-interest
create table if not exists rental.level_interest
(
    id          integer primary key,
    title       varchar(100)  not null unique,
    description varchar(1000) not null,
    weight      integer
)