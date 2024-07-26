CREATE TABLE IF NOT EXISTS files
(
    id
    BIGINT
    GENERATED
    ALWAYS AS
    IDENTITY
    PRIMARY
    KEY,
    data
    VARCHAR
(
    7000
) NOT NULL,
    title VARCHAR
(
    255
) NOT NULL,
    description VARCHAR
(
    1000
) NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL
                                );