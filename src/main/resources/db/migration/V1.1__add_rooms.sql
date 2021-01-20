create table rooms
(
    id bigserial primary key,
    name varchar unique,
    link varchar unique,
    user_id bigint not null references users (id)
)