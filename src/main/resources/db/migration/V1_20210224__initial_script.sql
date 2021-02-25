create table if not exists message
(
    id varchar(36) not null primary key,
    send_date timestamp,
    destination varchar(128),
    message text,
    status varchar(36)
);