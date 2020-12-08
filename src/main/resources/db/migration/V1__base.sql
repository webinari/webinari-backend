create table roles
(
    id   bigserial primary key,
    name varchar unique not null
);

create table users
(
    id       bigserial primary key,
    role_id  bigint references roles (id) not null,
    username varchar unique               not null,
    password varchar                      not null,
    email    varchar                      not null
);

create table rooms
(
    id       bigserial primary key,
    owner_id bigint references users (id) not null,
    name     varchar                      not null
)