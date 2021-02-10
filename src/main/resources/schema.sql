drop table if exists chat;
drop table if exists user_contacts;
drop table if exists user_chats;
drop table if exists chat_members;

create table chat
(
    chat_id   bigint       not null primary key,
    chat_name varchar(255) null default null -- makes sense for a group chats only
);

create table user_contacts
(
    user_id    bigint not null,
    contact_id bigint not null
);

create table user_chats
(
    user_id bigint not null,
    chat_id bigint not null
);

create table chat_members
(
    chat_id   bigint not null,
    member_id bigint not null
);