/* Create initial database structure p */

CREATE TABLE STUDENT 
(
  ID BIGINT PRIMARY KEY IDENTITY(1,1),
  NAME VARCHAR(100),
  ADDRESS VARCHAR(255)
);
