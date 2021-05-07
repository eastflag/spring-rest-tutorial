create table board
(
    id      int auto_increment
        primary key,
    title   varchar(100)                             not null,
    content text                                     not null,
    created datetime(6) default current_timestamp(6) not null,
    updated datetime(6) default current_timestamp(6) not null on update current_timestamp(6)
);

create table comment
(
    id       int auto_increment
        primary key,
    content  text                                     not null,
    created  datetime(6) default current_timestamp(6) not null,
    updated  datetime(6) default current_timestamp(6) not null on update current_timestamp(6),
    board_id int                                      null,
    constraint FK_77a3101cc141a4046264ce59d6d
        foreign key (board_id) references board (id)
            on update cascade on delete cascade
);

create table image
(
    id            int auto_increment
        primary key,
    mimetype      varchar(100)                             null,
    data          longblob                                 not null,
    original_name varchar(100)                             null,
    created       datetime(6) default current_timestamp(6) not null
);

create table role
(
    id   int auto_increment primary key,
    name varchar(20) null
);

create table user
(
    id       bigint auto_increment  primary key,
    email    varchar(255) null,
    password varchar(255) null,
    username varchar(255) null
);

create table user_role
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    constraint user_role_role_id_fk
        foreign key (role_id) references role (id),
    constraint user_role_user_id_fk
        foreign key (user_id) references user (id)
);