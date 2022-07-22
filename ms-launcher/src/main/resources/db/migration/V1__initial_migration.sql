CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DO $$
DECLARE

BEGIN

CREATE TABLE person (
    uuid VARCHAR(255) NOT NULL CONSTRAINT person_pkey PRIMARY KEY,
    active BOOLEAN,
    created TIMESTAMP,
    deleted BOOLEAN,
    modified TIMESTAMP,
    name VARCHAR(255),
    phone VARCHAR(255),
    age INTEGER,
    city VARCHAR(255),
    state VARCHAR(255),
    score INTEGER,
    region VARCHAR(255)
);

insert into person (uuid, active, created, deleted, modified, name, phone, age, city, state, score, region)
    values (uuid_generate_v4(), true, NOW(), false, null, 'João', '99 99999-9999', 30, 'Santos', 'SP', 1000, 'Sudeste');

END $$;
