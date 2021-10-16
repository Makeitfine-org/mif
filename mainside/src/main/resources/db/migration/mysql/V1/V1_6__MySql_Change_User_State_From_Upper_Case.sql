/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 */

ALTER TABLE APP_USER
    DROP COLUMN STATE;

ALTER TABLE APP_USER
    ADD STATE ENUM ('Active', 'Inactive', 'Deleted', 'Locked');

UPDATE APP_USER
SET STATE = 'Active'
where ID = 1;

UPDATE APP_USER
SET STATE = 'Active'
where ID = 2;

UPDATE APP_USER
SET STATE = 'Inactive'
where ID = 3;

UPDATE APP_USER
SET STATE = 'Locked'
where ID = 4;

UPDATE APP_USER
SET STATE = 'Inactive'
where ID = 5;

UPDATE APP_USER
SET STATE = 'Active'
where ID = 6;

UPDATE APP_USER
SET STATE = 'Active'
where ID = 7;

UPDATE APP_USER
SET STATE = 'Active'
where ID = 8;
