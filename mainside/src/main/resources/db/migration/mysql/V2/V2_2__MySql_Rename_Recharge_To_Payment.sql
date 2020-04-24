/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 */

ALTER TABLE RECHARGE RENAME TO PAYMENT;

INSERT INTO SEQUENCES (SEQ_NAME, SEQ_NUMBER)
SELECT 'PAYMENT_SEQUENCE', SEQ_NUMBER
FROM SEQUENCES
WHERE SEQ_NAME = 'RECHARGE_SEQUENCE';

DELETE
FROM SEQUENCES
WHERE SEQ_NAME = 'RECHARGE_SEQUENCE';
