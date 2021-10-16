----
--  Created under not commercial project "Make it fine"
--
--  Copyright 2017-2021
----

-- USER_PROFILE table contains all possible roles
CREATE TABLE USER_PROFILE
(
    ID   INT         NOT NULL,
    TYPE VARCHAR(30) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE SEQUENCES
(
    SEQ_NAME   VARCHAR(80) NOT NULL,
    SEQ_NUMBER BIGINT      NOT NULL,
    PRIMARY KEY (SEQ_NAME)
);

INSERT INTO USER_PROFILE
VALUES (1, 'USER');

INSERT INTO USER_PROFILE
VALUES (2, 'ADMIN');

INSERT INTO USER_PROFILE
VALUES (3, 'DBA');

-- Should be inserted next generated value (4)
INSERT INTO SEQUENCES
VALUES ('USER_PROFILE_SEQUENCE', 4);
