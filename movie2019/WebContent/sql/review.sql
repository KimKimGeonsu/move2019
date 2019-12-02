DROP TABLE review;

CREATE TABLE review(
REVIEW_NUMBER NUMBER UNIQUE,
MOVIE_ID NUMBER REFERENCES MOVIE (MOVIE_ID),
USER_ID VARCHAR2(40) REFERENCES USERS (USER_ID),
REVIEW_TITLE VARCHAR2(100),
REVIEW_CONTENT VARCHAR2(1000),
REVIEW_DATE DATE,
CONSTRAINT review_primarykey PRIMARY KEY (MOVIE_ID, USER_ID)
);

INSERT INTO review VALUES ((select nvl(max(REVIEW_NUMBER),0) from review)+1,
330457,'duswl13','�������� ����','�ʹ� ����ϰ� ���Ͱ��ƿ�Ф� ���ۺ��� �����ӤФФ�',sysdate);


INSERT INTO review VALUES ((select nvl(max(REVIEW_NUMBER),0) from review)+1,
330457,'sonyeonsoo','��վ���G����������','�ʹ���վ ��������',sysdate);


INSERT INTO review VALUES ((select nvl(max(REVIEW_NUMBER),0) from review)+1,
330457,'jiyeon','��...','��....',sysdate);

/* ���� �Ǵ� �� �������� �ִ� ��쿡�� ���� ������ְ� �ϱ�*/

