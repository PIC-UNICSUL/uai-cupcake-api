CREATE TABLE IF NOT EXISTS cs_product (
                           id UUID PRIMARY KEY,
                           name VARCHAR NOT NULL,
                           description VARCHAR,
                           category VARCHAR,
                           price DECIMAL NOT NULL CHECK (price >= 0),
                           availability_status VARCHAR CHECK (availability_status IN ('available', 'unavailable')) NOT NULL,
                           photos VARCHAR,
                           created_at TIMESTAMP DEFAULT NOW(),
                           updated_at TIMESTAMP DEFAULT NOW()
);