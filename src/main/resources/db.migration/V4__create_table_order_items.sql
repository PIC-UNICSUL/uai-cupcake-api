CREATE TABLE IF NOT EXISTS cs_order_items (
                               ID UUID PRIMARY KEY,
                               order_id UUID NOT NULL REFERENCES cs_order (id) ON DELETE CASCADE,
                               product_id UUID NOT NULL REFERENCES cs_product (id) ON DELETE CASCADE,
                               quantity INT NOT NULL CHECK (quantity > 0),
                               price DECIMAL NOT NULL CHECK (price >= 0),
                               created_at TIMESTAMP DEFAULT NOW()
);
