
CREATE DATABASE flow CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

create table event
(
    id           bigint auto_increment comment '主键'
        primary key,
    day          text                                 not null comment '哪一天',
    content      longtext default ''                  null comment '具体事情',
    gmt_create   datetime default current_timestamp() null comment '创建时间',
    gmt_modified datetime default current_timestamp() null on update current_timestamp() comment '修改时间',
    constraint event_day_uindex
        unique (day) using hash
)
    comment '大事迹';

create table flow
(
    id           bigint auto_increment comment 'ID'
        primary key,
    day          text                                 not null comment '哪一天',
    item         longtext default ''                  null comment '发生的哪些具体事情',
    gmt_create   datetime default current_timestamp() null comment '创建时间',
    gmt_modified datetime default current_timestamp() null on update current_timestamp() comment '修改时间',
    constraint flow_day_uindex
        unique (day) using hash
)
    comment '夫路';

ALTER TABLE `flow`.`flow` CHARACTER SET = utf8mb4, COLLATE = utf8mb4_general_ci;
ALTER TABLE `flow`.`event` CHARACTER SET = utf8mb4, COLLATE = utf8mb4_general_ci;
ALTER TABLE flow MODIFY COLUMN item longtext  CHARACTER SET utf8mb4  COLLATE utf8mb4_unicode_ci default '' NULL comment '发生的哪些具体事情';