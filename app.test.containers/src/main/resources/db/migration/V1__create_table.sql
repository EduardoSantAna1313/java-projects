CREATE TABLE API (
    ID VARCHAR(36) PRIMARY KEY,
    NAME VARCHAR(256) NOT NULL,
    DESCRIPTION VARCHAR(256),
    STATUS VARCHAR(10) CHECK (STATUS IN ('CREATED', 'PENDING')),
    CREATED TIMESTAMP NOT NULL DEFAULT NOW(),
    CREATED_BY VARCHAR(100),
    UPDATED TIMESTAMP NOT NULL DEFAULT NOW(),
    UPDATED_BY VARCHAR(100)
);

CREATE TABLE API_PARAMETERS (
    ID VARCHAR(36) PRIMARY KEY,
    API_ID VARCHAR(36) REFERENCES API(ID),
    PARAMETER_NAME VARCHAR(256),
    PARAMETER_VALUE VARCHAR(256)
);