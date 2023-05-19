-- 생성자 Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   위치:        2023-05-19 03:25:17 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE attachment (
    attach_id  NUMBER NOT NULL,
    post_id    NUMBER NOT NULL,
    filename   VARCHAR2(50) NOT NULL,
    filepath   VARCHAR2(100) NOT NULL,
    registered CHAR(19)
);

ALTER TABLE attachment ADD CONSTRAINT attachment_pk PRIMARY KEY ( attach_id );

CREATE TABLE "comment" (
    comment_id    NUMBER NOT NULL,
    post_post_id  NUMBER NOT NULL,
    users_user_id NUMBER NOT NULL,
    content       VARCHAR2(255) NOT NULL,
    written       CHAR(19) NOT NULL
);

ALTER TABLE "comment" ADD CONSTRAINT comment_pk PRIMARY KEY ( comment_id );

CREATE TABLE post (
    post_id       NUMBER NOT NULL,
    users_user_id NUMBER NOT NULL,
    title         VARCHAR2(100) NOT NULL,
    content       CLOB,
    hits          INTEGER DEFAULT 0 NOT NULL,
    written       CHAR(19) NOT NULL,
    updated       CHAR(19)
);

ALTER TABLE post ADD CONSTRAINT post_pk PRIMARY KEY ( post_id );

CREATE TABLE sitesetting (
    setting_key   VARCHAR2(30) NOT NULL,
    setting_value VARCHAR2(100)
);

ALTER TABLE sitesetting ADD CONSTRAINT sitesetting_pk PRIMARY KEY ( setting_key );

CREATE TABLE user_authority (
    users_user_id NUMBER NOT NULL,
    authority     VARCHAR2(20) NOT NULL
);

ALTER TABLE user_authority ADD CONSTRAINT user_authority_pk PRIMARY KEY ( users_user_id,
                                                                          authority );

CREATE TABLE users (
    user_id    NUMBER NOT NULL,
    login_id   NUMBER NOT NULL,
    password   VARCHAR2(100) NOT NULL,
    name       VARCHAR2(50) NOT NULL,
    email      VARCHAR2(100) NOT NULL,
    registered VARCHAR2(19) NOT NULL,
    lastlogged CHAR(19)
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( user_id );

ALTER TABLE attachment
    ADD CONSTRAINT attachment_post_fk FOREIGN KEY ( post_id )
        REFERENCES post ( post_id );

ALTER TABLE "comment"
    ADD CONSTRAINT comment_post_fk FOREIGN KEY ( post_post_id )
        REFERENCES post ( post_id );

ALTER TABLE "comment"
    ADD CONSTRAINT comment_users_fk FOREIGN KEY ( users_user_id )
        REFERENCES users ( user_id );

ALTER TABLE post
    ADD CONSTRAINT post_users_fk FOREIGN KEY ( users_user_id )
        REFERENCES users ( user_id );

ALTER TABLE user_authority
    ADD CONSTRAINT user_authority_users_fk FOREIGN KEY ( users_user_id )
        REFERENCES users ( user_id );



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             6
-- CREATE INDEX                             0
-- ALTER TABLE                             11
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
