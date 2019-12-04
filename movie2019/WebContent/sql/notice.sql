DROP TABLE NOTICE;

CREATE TABLE NOTICE(
NOTICE_NUMBER NUMBER PRIMARY KEY,
NOTICE_NAME VARCHAR2(40) REFERENCES  USERS (USER_ID) on delete cascade,
NOTICE_SUBJECT VARCHAR2(100),
NOTICE_CONTENT VARCHAR2(300),
NOTICE_DATE DATE
);	


INSERT INTO NOTICE VALUES((select nvl(max(NOTICE_NUMBER),0) from NOTICE)+1,
'admin1','�������� 1','�������� 1 �Դϴ�.',sysdate
);

INSERT INTO NOTICE VALUES((select nvl(max(NOTICE_NUMBER),0) from NOTICE)+1,
'admin3','�������� 2','�������� 2 �Դϴ�.',sysdate
);

INSERT INTO NOTICE VALUES((select nvl(max(NOTICE_NUMBER),0) from NOTICE)+1,
'admin2','�������� 3','�������� 3 �Դϴ�.',sysdate
);

