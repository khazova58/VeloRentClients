-- liquibase formatted sql

-- changeset yakhazova:create-table-clients
create table if not exists rental.clients
(
    user_id      varchar(50)  not null primary key,
    last_name    varchar(100) not null,
    first_name   varchar(100) not null,
    middle_name  varchar(100) not null,
    age          integer      not null,
    address      varchar(255),
    phone_number varchar(15)  not null,
    email        varchar(100) unique,
    create_date  timestamp,
    last_access  timestamp,
    source_id    integer references rental.source (id),
    status_id    integer references rental.status (id),
    level_id     integer references rental.level_interest (id),
    referrer     varchar references rental.clients (user_id)
)