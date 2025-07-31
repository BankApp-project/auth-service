CREATE TABLE passkeys
(
    id              UUID         NOT NULL,
    user_id         UUID         NOT NULL,
    credential_id   BYTEA        NOT NULL,
    user_handle     BYTEA        NOT NULL,
    username        VARCHAR(255) NOT NULL,
    public_key      OID          NOT NULL,
    signature_count BIGINT       NOT NULL,
    uv_initialized  BOOLEAN      NOT NULL,
    backup_eligible BOOLEAN      NOT NULL,
    backup_state    BOOLEAN      NOT NULL,
    transports      VARCHAR(255),
    CONSTRAINT pk_passkeys PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         UUID                        NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    email      VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE passkeys
    ADD CONSTRAINT uc_passkeys_credentialid UNIQUE (credential_id);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

CREATE UNIQUE INDEX idx_credential_id ON passkeys (credential_id);

ALTER TABLE passkeys
    ADD CONSTRAINT FK_PASSKEYS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);