ALTER TABLE APP_USER DROP COLUMN FIRST_NAME;
ALTER TABLE APP_USER DROP COLUMN LAST_NAME;
ALTER TABLE APP_USER DROP COLUMN DISCRIMINATOR;

CREATE TABLE CONTACT (
  ID           INT         NOT NULL,
  FIRST_NAME   VARCHAR(30) NOT NULL,
  MIDDLE_NAME  VARCHAR(30),
  LAST_NAME    VARCHAR(30) NOT NULL,
  BIRTH_DAY    DATETIME    NOT NULL,
  PHONE_NUMBER VARCHAR(30),
  PRIMARY KEY (ID),
  FOREIGN KEY (ID) REFERENCES APP_USER (ID)
);

INSERT INTO CONTACT VALUES (1, 'Bill', NULL, 'Watcher', '1981-8-8', '0735553432');
INSERT INTO CONTACT VALUES (2, 'Danny', NULL, 'Theys', '1986-6-3', '0715558440');
INSERT INTO CONTACT VALUES (3, 'Sam', 'Middlware', 'Smith', '1982-8-30', '0725556495');
INSERT INTO CONTACT VALUES (4, 'Nicole', NULL, 'Warner', '1987-5-22', null);
INSERT INTO CONTACT VALUES (5, 'Kenny', 'Kotton', 'Roger', '1980-8-29', '0725137211');
INSERT INTO CONTACT VALUES (6, 'SUDO', NULL, 'SUDO', '1979-12-28', null);
INSERT INTO CONTACT VALUES (7, 'Testside', NULL, 'Userino', '1978-4-3', '0715554032');
INSERT INTO CONTACT VALUES (8, 'Admin', NULL, 'Userino', '1980-8-29', '0725557763');

UPDATE APP_USER SET STATE = 'Invactive' where ID = 5;
UPDATE APP_USER SET STATE = 'Locked' where ID = 4;
UPDATE APP_USER SET STATE = 'Invactive' where ID = 3;
