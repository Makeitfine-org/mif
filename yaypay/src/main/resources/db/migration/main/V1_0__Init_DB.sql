/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

CREATE TABLE USER
(
    ID   INT     AUTO_INCREMENT    NOT NULL,
    NAME VARCHAR(30) NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO USER (NAME) VALUES ('User 1');
INSERT INTO USER (NAME) VALUES ('User 3');
INSERT INTO USER (NAME) VALUES ('User 2');
