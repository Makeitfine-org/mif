/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

CREATE TABLE book
(
    id     int not null auto_increment primary key,
    title  varchar(255),
    author varchar(255)
);

insert into book (title, author)
values ('any', 'auth 2');

insert into book (title, author)
values ('any 3', 'auth 3');
