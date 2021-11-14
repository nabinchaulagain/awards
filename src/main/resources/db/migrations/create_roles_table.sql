--liquibase formatted sql

--changeset nabinchaulagain:create_roles_table:1 endDelimiter:/ rollbackEndDelimiter:/
BEGIN
    EXECUTE IMMEDIATE
        'CREATE TABLE roles(
            id NUMBER,
            name VARCHAR2(255 CHAR),
            CONSTRAINT ROLES_PK PRIMARY KEY (id)
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
