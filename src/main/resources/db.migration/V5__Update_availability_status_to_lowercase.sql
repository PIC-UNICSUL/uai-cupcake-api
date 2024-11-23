ALTER TABLE cs_product
    DROP COLUMN availability_status;

ALTER TABLE cs_product
    ADD COLUMN availability_status VARCHAR(255) NOT NULL DEFAULT 'AVAILABLE';

ALTER TABLE cs_product
    ADD CONSTRAINT availability_status_check CHECK (availability_status IN ('AVAILABLE', 'UNAVAILABLE'));