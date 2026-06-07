CREATE TABLE static_info (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    complex_id INTEGER NOT NULL,
    message TEXT NOT NULL
);
