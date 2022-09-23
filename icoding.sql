create table Course
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

create table Grade
(
    id          smallint auto_increment
        primary key,
    name        varchar(30) default ''                not null,
    create_time timestamp   default CURRENT_TIMESTAMP not null,
    update_time timestamp   default CURRENT_TIMESTAMP not null,
    constraint Grade_id_uindex
        unique (id),
    constraint Grade_name_uindex
        unique (name)
)
    auto_increment = 4;

create table Hobby
(
    id          smallint auto_increment
        primary key,
    name        varchar(30)                         not null,
    create_time timestamp default CURRENT_TIMESTAMP not null,
    update_time timestamp default CURRENT_TIMESTAMP not null,
    constraint Hobby_id_uindex
        unique (id)
);

create table Student
(
    id          smallint auto_increment
        primary key,
    name        varchar(30) default '' not null,
    no          smallint    default 0  not null,
    create_time timestamp              null,
    update_time timestamp              null,
    grade_id    smallint               null,
    sex         tinyint     default 0  null,
    constraint Student_id_uindex
        unique (id),
    constraint Student_no_uindex
        unique (no)
)
    auto_increment = 10;

create table Teacher
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

