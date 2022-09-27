create table Vaporschool.Course
(
    id          smallint auto_increment
        primary key,
    name        varchar(30)                         not null,
    create_time timestamp default CURRENT_TIMESTAMP not null,
    update_time timestamp default CURRENT_TIMESTAMP not null,
    constraint Course_id_uindex
        unique (id),
    constraint Course_name_uindex
        unique (name)
);

create table Vaporschool.Grade
(
    id            smallint auto_increment
        primary key,
    name          varchar(30) default ''                not null,
    create_time   timestamp   default CURRENT_TIMESTAMP not null,
    update_time   timestamp   default CURRENT_TIMESTAMP not null,
    mainTeacherId smallint                              null,
    constraint Grade_id_uindex
        unique (id),
    constraint Grade_name_uindex
        unique (name)
);

create table Vaporschool.Hobby
(
    id          smallint auto_increment
        primary key,
    name        varchar(20)                         not null,
    create_time timestamp default CURRENT_TIMESTAMP not null,
    update_time timestamp default CURRENT_TIMESTAMP null,
    constraint Hobby_id_uindex
        unique (id),
    constraint Hobby_name_uindex
        unique (name)
);

create table Vaporschool.Student
(
    id          smallint auto_increment
        primary key,
    name        varchar(30) default '' not null,
    no          smallint    default 0  not null,
    create_time timestamp              null,
    update_time timestamp              null,
    grade_id    smallint               null,
    sex         tinyint     default 0  null,
    hobby_ids   varchar(30)            null,
    constraint Student_id_uindex
        unique (id),
    constraint Student_no_uindex
        unique (no)
);

create table Vaporschool.Teacher
(
    id          smallint auto_increment
        primary key,
    name        varchar(30)                         not null,
    sex         tinyint                             not null,
    grade_ids   varchar(100)                        null,
    course_id   tinyint                             not null,
    is_main     tinyint(1)                          null,
    hobby_ids   varchar(100)                        null,
    create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    update_time timestamp default CURRENT_TIMESTAMP not null,
    constraint Teacher_id_uindex
        unique (id)
);

create table Vaporschool._fluent_migrations
(
    id         varbinary(16) not null
        primary key,
    name       varchar(255)  not null,
    batch      bigint        not null,
    created_at datetime(6)   null,
    updated_at datetime(6)   null,
    constraint eb3ee69e0c062ede0b815d412472c764ccb82e41
        unique (name)
)
    charset = utf8mb4;

create table Vaporschool.sys_resource
(
    id         smallint auto_increment
        primary key,
    name       varchar(20) not null,
    uri        varchar(50) not null,
    permission varchar(20) null,
    type       smallint    null,
    icon       varchar(50) null,
    sn         smallint    null,
    parent_id  smallint    null
);

create table Vaporschool.sys_user_role
(
    role_id smallint not null,
    user_id int      not null
);

create table Vaporschool.table_name
(
    id   smallint    not null
        primary key,
    name varchar(20) not null
);

