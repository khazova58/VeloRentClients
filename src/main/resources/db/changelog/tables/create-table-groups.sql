-- liquibase formatted sql

-- changeset yakhazova:create-table-groups
create table if not exists rental.groups
(
    id          integer primary key,
    title       varchar(100) not null unique,
    description varchar(1000),
    create_date timestamp,
    update_date timestamp
)