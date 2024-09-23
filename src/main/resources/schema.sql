-- users
CREATE TABLE IF NOT EXISTS users
(
    id         int auto_increment primary key comment 'ID',
    user_token varchar(255) not null comment 'user_token',
    login_id   varchar(10)  not null comment '로그인 아이디',
    password   varchar(256) not null comment '비밀번호',
    name       varchar(20)  not null comment '사용자명',
    salt       varchar(50)  not null comment '암호화 salt 값',
    purpose    varchar(10)  not null comment '공부 목표'
) comment 'users' charset = utf8;

-- challenges
CREATE TABLE IF NOT EXISTS challenges
(
    id          int auto_increment primary key comment '챌린지 번호',
    name        varchar(50) not null comment '챌린지명',
    min_deposit int         not null comment '최소 보증금',
    max_deposit int         not null comment '최대 보증금',
    status      varchar(10) not null comment '챌린지 상태'
) comment 'challenges' charset = utf8;

-- orders
CREATE TABLE IF NOT EXISTS orders
(
    id             int auto_increment primary key comment '주문(신청) 번호',
    user_token     varchar(255) not null comment 'user_token',
    challenge_id   int          not null comment '챌린지 번호',
    challenge_name varchar(50)  not null comment '챌린지 명',
    deposit        int          not null comment '보증금',
    started_at     date         not null comment '시작일',
    study_time     int          not null comment '공부 시간'
) comment 'orders' charset = utf8;
