ALTER TABLE cs_order
    DROP COLUMN IF EXISTS order_status;

ALTER TABLE cs_order
    ADD COLUMN order_status VARCHAR(255) NOT NULL DEFAULT 'PENDING';

ALTER TABLE cs_order
    ADD CONSTRAINT order_status_check CHECK (order_status IN ('PENDING', 'CONFIRMED', 'DELIVERED', 'CANCELED'));
