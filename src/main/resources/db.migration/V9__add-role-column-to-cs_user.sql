ALTER TABLE cs_user
    ADD COLUMN role VARCHAR(50) NOT NULL DEFAULT 'CUSTOMER' CHECK (role IN ('CUSTOMER', 'ADMIN'));