ALTER TABLE cs_order
    DROP CONSTRAINT cs_order_status_check;

ALTER TABLE cs_order
    ADD CONSTRAINT cs_order_status_check CHECK (status IN ('PENDING', 'CONFIRMED', 'CANCELLED', 'DELIVERED'));
