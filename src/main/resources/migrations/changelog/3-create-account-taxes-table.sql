CREATE TABLE account_tax_rates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account VARCHAR(255) NOT NULL,
    "year" INT NOT NULL,
    tax_rate DECIMAL(5, 2) NOT NULL,
    CONSTRAINT uq_account_year UNIQUE (account, "year")
);
