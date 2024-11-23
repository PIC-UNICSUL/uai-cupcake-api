CREATE TABLE IF NOT EXISTS  cs_order (
        id UUID PRIMARY KEY,
        user_id UUID NOT NULL REFERENCES cs_user (id) ON DELETE CASCADE,
        status VARCHAR CHECK (status IN ('pending', 'confirmed', 'delivered', 'canceled')) NOT NULL,
        created_at TIMESTAMP DEFAULT NOW(),
        updated_at TIMESTAMP DEFAULT NOW()
);