create table users
(
    id       bigserial primary key,
    username varchar unique               not null,
    password varchar                      not null,
    email    varchar                      not null
);

create table rooms_metadata
(
    id bigserial primary key,
    lecturer varchar,
    video_id varchar
);

create table rooms
(
    id bigserial primary key,
    name varchar,
    public_id varchar,
    user_id bigint not null references users (id),
    metadata_id bigint references rooms_metadata (id)
)