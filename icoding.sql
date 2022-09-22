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

create table Vaporschool.grades
(
    id         smallint     not null
        primary key,
    name       varchar(255) not null,
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    constraint f5bced18dd859064783f8c180847e5f633ceb1c5
        unique (name)
);

create table Vaporschool.hobbies
(
    id         smallint     not null
        primary key,
    name       varchar(255) not null,
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    constraint `9f44df3041aabbbc80840bb3e9f0d9314333dc44`
        unique (name)
);

create table Vaporschool.`student+tag`
(
    id         varbinary(16) not null
        primary key,
    student_id varbinary(16) null,
    hobby_id   varbinary(16) null,
    created_at varchar(255)  null,
    updated_at varchar(255)  null,
    constraint a99066b815fb0ca08c6e91e378edcbc5a5590f9d
        unique (student_id, hobby_id)
)
    charset = utf8mb4;

create table Vaporschool.students
(
    id         smallint      not null
        primary key,
    name       varchar(255)  not null,
    no         bigint        not null,
    grade_id   varbinary(16) not null,
    created_at datetime(6)   null,
    updated_at datetime(6)   null,
    constraint `13ff81310b2d2a468250f340ea60337af157e02a`
        unique (no)
);

