create table calendar
(
    calendar_id   int auto_increment
        primary key,
    calendar_name varchar(40) not null
);

create table event_detail
(
    detail_id  int auto_increment
        primary key,
    title      varchar(45)       not null,
    content    varchar(45)       null,
    start_time datetime          not null,
    end_time   datetime          not null,
    is_repeat  tinyint default 0 not null
);

create table event
(
    event_id     int auto_increment
        primary key,
    detail_id    int               not null,
    calendar_id  int               not null,
    priority     int     default 0 not null,
    is_overriden tinyint default 0 not null,
    is_remind    tinyint default 0 not null,
    constraint calendar_id_2
        foreign key (calendar_id) references calendar (calendar_id)
            on delete cascade,
    constraint detail_id
        foreign key (detail_id) references event_detail (detail_id)
            on delete cascade
);

create index calendar_id_idx
    on event (calendar_id);

create index detail_id_idx
    on event (detail_id);

create table event_reminder
(
    event_id    int      not null
        primary key,
    remind_time datetime not null,
    constraint event_id_2
        foreign key (event_id) references event (event_id)
);

create table event_repetition
(
    detail_id     int                                    not null
        primary key,
    repeat_unit   varchar(45)                            not null,
    repeat_amount int                                    not null,
    until         datetime default '9999-12-31 12:59:59' not null,
    constraint detail_id_1
        foreign key (detail_id) references event_detail (detail_id)
            on delete cascade
);

create table user
(
    user_id int auto_increment
        primary key,
    open_id text        null,
    email   varchar(45) null,
    name    varchar(45) null
);

create table user_calendar
(
    user_id          int               not null,
    calendar_id      int               not null,
    is_dnd           tinyint default 0 not null,
    permission       varchar(45)       not null,
    is_expose_detail tinyint default 0 not null,
    primary key (user_id, calendar_id),
    constraint calendar_id
        foreign key (calendar_id) references calendar (calendar_id)
            on delete cascade,
    constraint user_id
        foreign key (user_id) references user (user_id)
);

create index calendar_id_idx
    on user_calendar (calendar_id);

create index user_idx
    on user_calendar (user_id);

create table user_detail
(
    user_id    int         not null,
    detail_id  int         not null,
    permission varchar(45) null,
    primary key (user_id, detail_id),
    constraint detail_id_2
        foreign key (detail_id) references event_detail (detail_id)
            on delete cascade,
    constraint user_id_2
        foreign key (user_id) references user (user_id)
);

create index detail_id_idx
    on user_detail (detail_id);

create index user_id_idx
    on user_detail (user_id);


