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
    public_link varchar unique
);

create table translations
(
    id bigserial primary key,
    type int not null,
    video_id varchar
);

create table rooms
(
    id bigserial primary key,
    name varchar,
    public_id varchar,
    user_id bigint not null references users (id),
    translation_id bigint references translations (id),
    metadata_id bigint references rooms_metadata (id)
)