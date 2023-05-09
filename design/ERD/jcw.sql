
CREATE TABLE "comment" (
    comment_id INTEGER NOT NULL,
    post_id    INTEGER NOT NULL,
    "comment"  VARCHAR2(255) NOT NULL,
    written    CHAR(19) NOT NULL
);

ALTER TABLE "comment" ADD CONSTRAINT comment_pk PRIMARY KEY ( comment_id );

CREATE TABLE post (
    post_id   INTEGER NOT NULL,
    writer_id INTEGER NOT NULL,
    title     VARCHAR2(100) NOT NULL,
    content   CLOB,
    written   CHAR(19) NOT NULL
);

ALTER TABLE post ADD CONSTRAINT post_pk PRIMARY KEY ( post_id );

CREATE TABLE sitesetting (
    setting_key   VARCHAR2(30) NOT NULL,
    setting_value VARCHAR2(100)
);

ALTER TABLE sitesetting ADD CONSTRAINT sitesetting_pk PRIMARY KEY ( setting_key );

CREATE TABLE user_authority (
    user_id   INTEGER NOT NULL,
    authority VARCHAR2(20) NOT NULL
);

ALTER TABLE user_authority ADD CONSTRAINT user_authority_pk PRIMARY KEY ( user_id,
                                                                          authority );

CREATE TABLE users (
    user_id    INTEGER NOT NULL,
    login_id   INTEGER NOT NULL,
    password   VARCHAR2(100) NOT NULL,
    name       VARCHAR2(50) NOT NULL,
    email      VARCHAR2(100) NOT NULL,
    registered VARCHAR2(19) NOT NULL
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( user_id );

ALTER TABLE "comment"
    ADD CONSTRAINT comment_post_fk FOREIGN KEY ( post_id )
        REFERENCES post ( post_id );

ALTER TABLE post
    ADD CONSTRAINT post_users_fk FOREIGN KEY ( writer_id )
        REFERENCES users ( user_id );

ALTER TABLE user_authority
    ADD CONSTRAINT user_authority_users_fk FOREIGN KEY ( user_id )
        REFERENCES users ( user_id );