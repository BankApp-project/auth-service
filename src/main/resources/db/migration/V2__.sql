CREATE TABLE outbox
(
    id             UUID                        NOT NULL,
    timestamp      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    aggregate_type VARCHAR(255)                NOT NULL,
    aggregate_id   UUID                        NOT NULL,
    event_type     VARCHAR(255)                NOT NULL,
    payload        JSON                        NOT NULL,
    published      BOOLEAN                     NOT NULL,
    CONSTRAINT pk_outbox PRIMARY KEY (id)
);

ALTER TABLE my_credential_record
    ADD user_handle BYTEA;

ALTER TABLE my_credential_record
    ALTER COLUMN user_handle SET NOT NULL;

ALTER TABLE users
    DROP COLUMN id;

ALTER TABLE users
    ADD id UUID NOT NULL PRIMARY KEY;