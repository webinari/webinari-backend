create table roles
(
    id   bigserial primary key,
    name varchar unique not null
);

create table users
(
    id       bigserial primary key,
--     role_id  bigint references roles (id) not null,
    username varchar unique               not null,
    password varchar                      not null,
    email    varchar                      not null
);