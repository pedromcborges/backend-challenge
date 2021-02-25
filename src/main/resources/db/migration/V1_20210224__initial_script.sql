create table if not exists message
(
    id UUID not null primary key,
    send_date timestamp,
    destination varchar(128),
    message text,
    status varchar(36),
    channel varchar(36)
);