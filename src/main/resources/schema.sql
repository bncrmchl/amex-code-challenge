CREATE TABLE IF NOT EXISTS student (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    date_of_birth DATE NOT NULL,
    joining_date DATE NOT NULL,
    class TEXT NOT NULL
);

GRANT SELECT, INSERT, UPDATE, DELETE ON student TO api_user;
