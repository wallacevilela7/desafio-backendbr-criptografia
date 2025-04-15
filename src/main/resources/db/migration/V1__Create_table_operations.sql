CREATE TABLE operation (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    user_document VARCHAR(255) NOT NULL,
    credit_card_token VARCHAR(255) NOT NULL,
    operation_value BIGINT NOT NULL
);