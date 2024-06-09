CREATE TABLE operations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    payer_bank VARCHAR(255) NOT NULL,
    payee_bank VARCHAR(255) NOT NULL,
    payer_account VARCHAR(6) NOT NULL,
    payee_account VARCHAR(6) NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    transaction_number VARCHAR(6) NULL,
    datetime TIMESTAMP NOT NULL
);