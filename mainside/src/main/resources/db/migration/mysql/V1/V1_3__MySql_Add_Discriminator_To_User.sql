/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 */

ALTER TABLE APP_USER
    ADD COLUMN DISCRIMINATOR VARCHAR(20) NOT NULL;

UPDATE APP_USER
SET DISCRIMINATOR = 'customer'
where SSO_ID = 'bill';

UPDATE APP_USER
SET DISCRIMINATOR = 'customer'
where SSO_ID = 'danny';

UPDATE APP_USER
SET DISCRIMINATOR = 'customer'
where SSO_ID = 'sudo';

UPDATE APP_USER
SET DISCRIMINATOR = 'customer'
where SSO_ID = 'tsuser';

UPDATE APP_USER
SET DISCRIMINATOR = 'customer'
where SSO_ID = 'admin';

UPDATE APP_USER
SET DISCRIMINATOR = 'worker'
where SSO_ID = 'sam';

UPDATE APP_USER
SET DISCRIMINATOR = 'worker'
where SSO_ID = 'nicole';

UPDATE APP_USER
SET DISCRIMINATOR = 'worker'
where SSO_ID = 'kenny';
