drop table if exists user_contacts;
drop table if exists user_chats;
drop table if exists chat_members;

drop table if exists chat_message;
drop table if exists chat;

create table chat
(
    chat_id   bigint       not null primary key auto_increment,
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

create table chat_message
(
    message_id bigint   not null primary key auto_increment,
    content    longtext null     default null,
    chat_id    bigint   not null,
    author_id  bigint   not null,
    seen       boolean  not null default false,
    timestamp  bigint   not null,
    constraint fk_chat_message_chat_id_chat_chat_id
        foreign key (chat_id) references chat (chat_id),
    constraint fk_chat_message_author_id_user_user_id
        foreign key (author_id) references user (id)
)