--liquibase formatted sql

--changeset nabinchaulagain:refresh_tokens:1 endDelimiter:/ rollbackEndDelimiter:/
CREATE TABLE IF NOT EXISTS  refresh_tokens (
    token VARCHAR(255),
    user_id BIGINT NOT NULL,
    expiry_date TIMESTAMP(6),
    issued_date TIMESTAMP(6),
    CONSTRAINT refresh_token_pk PRIMARY KEY (token),
    CONSTRAINT refresh_token_user_fk FOREIGN KEY(user_id) REFERENCES USERS (id)
);
/
--rollback DROP TABLE refresh_tokens
