
CREATE TABLE medicines
(
    id           UUID                        NOT NULL,
    created_by   VARCHAR(210)                NOT NULL,
    created      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_by   VARCHAR(20),
    updated      TIMESTAMP WITHOUT TIME ZONE,
    deleted      TIMESTAMP WITHOUT TIME ZONE,
    name         VARCHAR(255)                NOT NULL,
    manufacturer VARCHAR(255),
    stock        INTEGER,
    CONSTRAINT pk_medicines PRIMARY KEY (id)
);