DROP TABLE IF EXISTS Sheet1User;
DROP TABLE IF EXISTS Sheet1;
DROP TABLE IF EXISTS Sheet2;
DROP TABLE IF EXISTS Account;
CREATE TABLE Account(id INTEGER NOT NULL primary key autoincrement,
Email	VARCHAR(254) NOT NULL,
Name	VARCHAR(254) NOT NULL,
Password	VARCHAR(254) NOT NULL,
Userrole	VARCHAR(25) NOT NULL);
INSERT INTO Account (Email, Name, Password, Userrole) VALUES ('user@dea42.com', 'user', 'ChangeMe', 'ROLE_USER');
INSERT INTO Account (Email, Name, Password, Userrole) VALUES ('admin@dea42.com', 'admin', 'ChangeMe', 'ROLE_ADMIN');
CREATE TABLE Sheet1(id INTEGER NOT NULL primary key autoincrement,
IntField	INTEGER NOT NULL,
DateField	DATETIME,
Text	VARCHAR(7),
DecimalField	REAL);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (12, 1577944800000, 'Text 12', 11.4740385467383);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (13, 1578031200000, 'Text 13', 13.018155647877983);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (14, 1578117600000, 'Text 14', 15.736708349796885);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (15, 1578204000000, 'Text 15', 11.71172619564512);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (16, 1578290400000, 'Text 16', 11.407025248351868);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (17, 1578376800000, 'Text 17', 16.841672427550037);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (18, 1578463200000, 'Text 18', 12.11394283110295);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (19, 1578549600000, 'Text 19', 12.748148613544384);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (20, 1578636000000, 'Text 20', 13.449719540462491);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (21, 1578722400000, 'Text 21', 11.482661883353837);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (22, 1578808800000, 'Text 22', 4021.5667896362806);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (23, 1578895200000, 'Text 23', 12.356118242837768);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (24, 1578981600000, 'Text 24', 11.501023999868776);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (25, 1579068000000, 'Text 25', 11.024830960958841);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (26, 1579154400000, 'Text 26', 14.010562395335892);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (27, 1579240800000, 'Text 27', 11.808108606864669);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (28, 1579327200000, 'Text 28', 11.811175928830416);
INSERT INTO Sheet1 (IntField, DateField, Text, DecimalField) VALUES (29, 1579413600000, 'Text 29', 11.199482610951728);
CREATE TABLE Sheet1User(id INTEGER NOT NULL primary key autoincrement,
Userid	INTEGER,
Sheet1Id	INTEGER,
UserYN	VARCHAR(1));
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 1, 'Y');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 2, 'Y');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 3, 'N');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 4, 'N');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 5, null);
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 6, null);
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 7, 'X');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 8, '?');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 9, '?');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 10, '?');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 11, '?');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 12, '?');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 13, '?');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 14, '?');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 15, '?');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 16, '?');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 17, '?');
INSERT INTO Sheet1User (Userid, Sheet1Id, UserYN) VALUES (1, 18, '?');
CREATE TABLE Sheet2(id INTEGER NOT NULL primary key autoincrement,
DateField	DATETIME NOT NULL,
IntegerField	INTEGER NOT NULL,
Text	VARCHAR(21) NOT NULL,
DecimalField	REAL);
INSERT INTO Sheet2 (DateField, IntegerField, Text, DecimalField) VALUES (1261783140000, 1000, 'Text 172.722035879186', 172.722035879186);
INSERT INTO Sheet2 (DateField, IntegerField, Text, DecimalField) VALUES (1251059520000, 1001, 'Text 48.647887773539', 48.647887773538955);
INSERT INTO Sheet2 (DateField, IntegerField, Text, DecimalField) VALUES (1254019200000, 1002, 'Text 82.9032388974372', 82.903238897437149);
INSERT INTO Sheet2 (DateField, IntegerField, Text, DecimalField) VALUES (1248878940000, 1003, 'Text 23.4096126671001', 23.409612667100063);
INSERT INTO Sheet2 (DateField, IntegerField, Text, DecimalField) VALUES (1249507140000, 1004, 'Text 30.6799324678333', 30.679932467833293);
INSERT INTO Sheet2 (DateField, IntegerField, Text, DecimalField) VALUES (1257270420000, 1005, 'Text 120.491117171589', 120.49111717158894);
INSERT INTO Sheet2 (DateField, IntegerField, Text, DecimalField) VALUES (1312201200000, 1006, 'Text 756.305565620182', 756.305565620182);
INSERT INTO Sheet2 (DateField, IntegerField, Text, DecimalField) VALUES (1291914480000, 1007, 'Text 521.463975543096', 521.46397554309613);
