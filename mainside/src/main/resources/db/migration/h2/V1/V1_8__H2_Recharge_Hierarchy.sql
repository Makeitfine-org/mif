----
--  Created under not commercial project "Make it fine"
--
--  Copyright 2017-2021
----

CREATE TABLE RECHARGE
(
    ID           INT         NOT NULL,
    AMOUNT       DOUBLE,
    PAYMENT_TYPE VARCHAR(30) NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO RECHARGE
VALUES (1, 234.30, 'Cach');

INSERT INTO RECHARGE
VALUES (2, 62343.310, 'Undefined');

INSERT INTO RECHARGE
VALUES (3, 1234, 'Card');

INSERT INTO SEQUENCES
VALUES ('RECHARGE_SEQUENCE', 4);
