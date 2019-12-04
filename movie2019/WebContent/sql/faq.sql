DROP TABLE FAQ;

CREATE TABLE FAQ(
FAQ_NUMBER NUMBER PRIMARY KEY,
FAQ_NAME VARCHAR2(40) REFERENCES  USERS (USER_ID) on delete cascade,
FAQ_SUBJECT VARCHAR2(100),
FAQ_CONTENT VARCHAR2(300),
FAQ_DATE DATE
);	


INSERT INTO FAQ VALUES((select nvl(max(FAQ_NUMBER),0) from FAQ)+1,
'admin1','�̽���Ʈ ������?','�����Դϴ�.',sysdate
);

INSERT INTO FAQ VALUES((select nvl(max(FAQ_NUMBER),0) from FAQ)+1,
'admin1','�̽���Ʈ ������?1','�����Դϴ�.1',sysdate
);

INSERT INTO FAQ VALUES((select nvl(max(FAQ_NUMBER),0) from FAQ)+1,
'admin2','�̽���Ʈ ������?2','�����Դϴ�.2',sysdate
);
INSERT INTO FAQ VALUES((select nvl(max(FAQ_NUMBER),0) from FAQ)+1,
'admin3','�̽���Ʈ ������?3','�����Դϴ�.3',sysdate
);
