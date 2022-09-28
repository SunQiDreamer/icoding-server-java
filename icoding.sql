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
)
    auto_increment = 5;

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
)
    auto_increment = 4;

create table Vaporschool.Hobby
(
    id          smallint auto_increment
        primary key,
    name        varchar(30)                         not null,
    create_time timestamp default CURRENT_TIMESTAMP not null,
    update_time timestamp default CURRENT_TIMESTAMP not null,
    constraint Hobby_id_uindex
        unique (id)
)
    auto_increment = 6;

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
)
    auto_increment = 10;

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
)
    auto_increment = 4;

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

create table Vaporschool.sys_role
(
    id   smallint    not null
        primary key,
    name varchar(20) not null
);

create table Vaporschool.sys_role_resource
(
    role_id     smallint not null,
    resource_id smallint not null,
    constraint sys_role_resource_pk
        unique (role_id, resource_id)
);

create table Vaporschool.sys_user
(
    id          int auto_increment
        primary key,
    nick_name   varchar(20)                         null,
    user_name   varchar(20)                         not null,
    password    varchar(20)                         not null,
    create_time timestamp default CURRENT_TIMESTAMP null,
    login_time  timestamp                           null,
    status      tinyint   default 0                 not null,
    constraint sys_user_id_uindex
        unique (id)
);

create table Vaporschool.sys_user_role
(
    role_id smallint not null,
    user_id int      not null
);


