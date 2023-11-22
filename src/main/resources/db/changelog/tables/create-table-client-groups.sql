-- liquibase formatted sql

-- changeset yakhazova:create-table-client-groups
create table if not exists rental.client_groups
(
    client_id varchar references rental.clients (user_id),
    group_id  integer references rental.groups (id),
    PRIMARY KEY (client_id, group_id)
)