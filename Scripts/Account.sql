CREATE TABLE Account(id INTEGER NOT NULL primary key autoincrement,
Email	VARCHAR(254) NOT NULL,
Name	VARCHAR(254) NOT NULL,
Password	VARCHAR(254) NOT NULL,
Userrole	VARCHAR(25) NOT NULL,
CONSTRAINT UC_Email UNIQUE (Email));
