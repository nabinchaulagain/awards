--liquibase formatted sql

--changeset nabinchaulagain:user_roles:1 endDelimiter:/ rollbackEndDelimiter:/
CREATE TABLE user_roles
        (
            user_id BIGINT NOT NULL,
            role_id BIGINT NOT NULL,
            CONSTRAINT USER_ROLES_PK PRIMARY KEY (user_id, role_id),
            CONSTRAINT USER_ROLES_USERS_FK FOREIGN KEY(user_id) REFERENCES users (id),
            CONSTRAINT USER_ROLES_ROLES_FK FOREIGN KEY(role_id) REFERENCES roles (id)
        );
/
--rollback DROP TABLE user_roles;
