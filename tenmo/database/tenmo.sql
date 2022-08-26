BEGIN TRANSACTION;
DROP TABLE IF EXISTS tenmo_user, account, transfer, transfer_type ;
DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id, seq_transfer_id ;
-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;
CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);
-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;
CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance decimal(13, 2) NOT NULL DEFAULT 1000.00,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);
CREATE SEQUENCE seq_transfer_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;
CREATE TABLE transfer (
	transfer_id int NOT NULL DEFAULT nextval('seq_transfer_id'),
	transfer_type_id int NOT NULL,
	account_from int NOT NULL,
	account_to int NOT NULL,
	transfer_status boolean,
	amount decimal(13,2) NOT NULL,
	CONSTRAINT PK_transfer_id PRIMARY KEY (transfer_id),
	CONSTRAINT FK_transfer_account_from FOREIGN KEY (account_from) REFERENCES account(account_id),
	CONSTRAINT FK_transfer_account_to FOREIGN KEY (account_to) REFERENCES account(account_id),
	CONSTRAINT CK_amount_0 CHECK (amount > 0),
	CONSTRAINT CK_transfer_not_same_account CHECK (account_from != account_to)
	
);
CREATE TABLE transfer_type(
transfer_type_id serial ,
transfer_type_name varchar(50) NOT NULL,
CONSTRAINT PK_transfer_type_id PRIMARY KEY (transfer_type_id)
);
INSERT INTO transfer_type (transfer_type_name) VALUES ('send');
INSERT INTO transfer_type (transfer_type_name) VALUES ( 'request');
COMMIT;