--liquibase formatted sql

--changeset nabinchaulagain:create_users_table:1 endDelimiter:/ rollbackEndDelimiter:/
BEGIN
EXECUTE IMMEDIATE '
    CREATE TABLE users(
        id NUMBER,
        username VARCHAR2(255 CHAR) NOT NULL,
        email VARCHAR2(255 CHAR) NOT NULL,
        password VARCHAR2(255 CHAR),
        CONSTRAINT user_id_pk PRIMARY KEY (id)
    )
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

--rollback DROP TABLE users;
