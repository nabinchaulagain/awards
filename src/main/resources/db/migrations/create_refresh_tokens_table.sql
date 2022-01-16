--liquibase formatted sql

--changeset nabinchaulagain:create_refresh_tokens_table:1 endDelimiter:/ rollbackEndDelimiter:/
BEGIN
    EXECUTE IMMEDIATE
        'CREATE TABLE refresh_tokens
            (token VARCHAR2(255 CHAR), user_id NUMBER(9, 0) NOT NULL, expiry_date TIMESTAMP(6), issued_date TIMESTAMP(6), CONSTRAINT refresh_token_pk PRIMARY KEY (token), CONSTRAINT refresh_token_user_fk FOREIGN KEY(user_id) REFERENCES users (id))
        ';
EXCEPTION
    WHEN OTHERS THEN
        IF sqlcode = -955 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
END;
/
--rollback DROP TABLE refresh_tokens
