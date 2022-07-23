CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DO $$
DECLARE

BEGIN

CREATE TABLE scores (
    uuid VARCHAR(255) NOT NULL CONSTRAINT scores_pkey PRIMARY KEY,
    active BOOLEAN,
    created TIMESTAMP,
    deleted BOOLEAN,
    modified TIMESTAMP,
    description VARCHAR(255),
    initial_range INTEGER,
    final_range INTEGER
);

-- Insert scores
insert into scores (uuid, active, created, deleted, modified, description, initial_range, final_range)
    values(uuid_generate_v4(), true, NOW(), false, null, 'Insuficiente', 0, 200);

insert into scores (uuid, active, created, deleted, modified, description, initial_range, final_range)
    values(uuid_generate_v4(), true, NOW(), false, null, 'Inaceitável', 201, 500);

insert into scores (uuid, active, created, deleted, modified, description, initial_range, final_range)
    values(uuid_generate_v4(), true, NOW(), false, null, 'Aceitável', 501, 700);

insert into scores (uuid, active, created, deleted, modified, description, initial_range, final_range)
    values(uuid_generate_v4(), true, NOW(), false, null, 'Recomendável', 701, 1000);
END $$;
