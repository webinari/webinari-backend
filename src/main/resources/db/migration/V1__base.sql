create table users
(
    id       bigserial primary key,
    username varchar unique               not null,
    password varchar                      not null,
    email    varchar                      not null
);