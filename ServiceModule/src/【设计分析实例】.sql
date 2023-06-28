DROP DATABASE IF EXISTS mao;
CREATE DATABASE mao CHARACTER SET UTF8;
USE mao;
CREATE TABLE member(
    mid             VARCHAR(20),
    name            VARCHAR(20),
    age             int(3),
    sex             VARCHAR(10),
    email           VARCHAR(50),
    birthday        DATE,
    note            LONGTEXT,
    CONSTRAINT pk_mid PRIMARY KEY (mid)
)engine = innodb;

INSERT INTO member(MID, NAME, AGE, SEX, EMAIL, BIRTHDAY, NOTE) VALUES ('1','1',1,'1','1','1989-12-4','1') ;
INSERT INTO member(MID, NAME, AGE, SEX, EMAIL, BIRTHDAY, NOTE) VALUES ('2','2',2,'2','2','1989-12-4','2') ;
INSERT INTO member(MID, NAME, AGE, SEX, EMAIL, BIRTHDAY, NOTE) VALUES ('3','3',3,'3','3','1989-12-4','3') ;
INSERT INTO member(MID, NAME, AGE, SEX, EMAIL, BIRTHDAY, NOTE) VALUES ('4','4',4,'4','4','1989-12-4','4') ;
INSERT INTO member(MID, NAME, AGE, SEX, EMAIL, BIRTHDAY, NOTE) VALUES ('5','5',5,'5','5','1989-12-4','5') ;
INSERT INTO member(MID, NAME, AGE, SEX, EMAIL, BIRTHDAY, NOTE) VALUES ('6','6',6,'6','6','1989-12-4','6') ;
INSERT INTO member(MID, NAME, AGE, SEX, EMAIL, BIRTHDAY, NOTE) VALUES ('7','7',7,'7','7','1989-12-4','7') ;

SELECT mid,name,age,sex,birthday,email,note FROM member LIMIT 0, 5  ;