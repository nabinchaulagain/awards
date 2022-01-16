--liquibase formatted sql

--changeset nabinchaulagain:create_user_roles_table:1 endDelimiter:/ rollbackEndDelimiter:/
BEGIN
    EXECUTE IMMEDIATE
        'CREATE TABLE user_roles
            (user_id NUMBER NOT NULL, role_id NUMBER NOT NULL, CONSTRAINT USER_ROLES_PK PRIMARY KEY (user_id, role_id), CONSTRAINT USER_ROLES_USERS_FK FOREIGN KEY(user_id) REFERENCES users (id), CONSTRAINT USER_ROLES_ROLES_FK FOREIGN KEY(role_id) REFERENCES roles (id))
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

--rollback DROP TABLE user_roles;
