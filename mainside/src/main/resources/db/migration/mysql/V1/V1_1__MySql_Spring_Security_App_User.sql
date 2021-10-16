/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 */

/*All User's are stored in APP_USER table*/
CREATE TABLE APP_USER
(
    ID         INT          NOT NULL,
    SSO_ID     VARCHAR(30)  NOT NULL UNIQUE,
    PASSWORD   VARCHAR(100) NOT NULL,
    FIRST_NAME VARCHAR(30)  NOT NULL,
    LAST_NAME  VARCHAR(30)  NOT NULL,
    EMAIL      VARCHAR(30)  NOT NULL UNIQUE,
    STATE      VARCHAR(30)  NOT NULL,
    PRIMARY KEY (ID)
);

/* Populate APP_USER Table */
INSERT INTO APP_USER
VALUES (1, 'bill', 'abc123', 'Bill', 'Watcher', 'bill@xyz.com', 'Active');

INSERT INTO APP_USER
VALUES (2, 'danny', 'abc124', 'Danny', 'Theys', 'danny@xyz.com', 'Active');

INSERT INTO APP_USER
VALUES (3, 'sam', 'abc125', 'Sam', 'Smith', 'samy@xyz.com', 'Active');

INSERT INTO APP_USER
VALUES (4, 'nicole', 'abc126', 'Nicole', 'warner', 'nicloe@xyz.com', 'Active');

INSERT INTO APP_USER
VALUES (5, 'kenny', 'abc127', 'Kenny', 'Roger', 'kenny@xyz.com', 'Active');

INSERT INTO APP_USER
VALUES (6, 'sudo', 'nimda', 'SUDO', 'SUDO', 'sudo@xxx.xxx', 'Active');

INSERT INTO APP_USER
VALUES (7, 'tsuser', 'tspass1', 'Testside', 'User', 'tsuser@xxx.xxx', 'Active');

INSERT INTO APP_USER
VALUES (8, 'admin', 'nimda', 'Admin', 'User', 'admin@xxx.xxx', 'Active');

-- Should be inserted next generated value (9)
INSERT INTO SEQUENCES
VALUES ('APP_USER_SEQUENCE', 9);
