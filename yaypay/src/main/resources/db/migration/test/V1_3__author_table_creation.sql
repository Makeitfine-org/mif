/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

CREATE TABLE author
(
    id   int not null auto_increment primary key,
    name varchar(255)
);

insert into author (name)
values ('Ben First');

insert into author (name)
values ('Get Rid');
