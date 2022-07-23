CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DO $$
DECLARE
    north_region_uuid uuid := uuid_generate_v4();
    northeast_region_uuid uuid := uuid_generate_v4();
    midwest_region_uuid uuid := uuid_generate_v4();
    southeast_region_uuid uuid := uuid_generate_v4();
    south_region_uuid uuid := uuid_generate_v4();
BEGIN

CREATE TABLE relationships (
    uuid VARCHAR(255) NOT NULL CONSTRAINT relationships_pkey PRIMARY KEY,
    active BOOLEAN,
    created TIMESTAMP,
    deleted BOOLEAN,
    modified TIMESTAMP,
    region VARCHAR(255)
);

CREATE TABLE states (
    uuid VARCHAR(255) NOT NULL CONSTRAINT states_pkey PRIMARY KEY,
    active BOOLEAN,
    created TIMESTAMP,
    deleted BOOLEAN,
    modified TIMESTAMP,
    relationship_uuid VARCHAR(255) NOT NULL,
    code VARCHAR(200) NOT NULL,
    CONSTRAINT FK_states_relationships
      FOREIGN KEY (relationship_uuid)
          REFERENCES relationships(uuid)
);

-- Insert regions
insert into relationships (uuid, active, created, deleted, modified, region) values(north_region_uuid, true, NOW(), false, null, 'Norte');
insert into relationships (uuid, active, created, deleted, modified, region) values(northeast_region_uuid, true, NOW(), false, null, 'Nordeste');
insert into relationships (uuid, active, created, deleted, modified, region) values(midwest_region_uuid, true, NOW(), false, null, 'Centro-Oeste');
insert into relationships (uuid, active, created, deleted, modified, region) values(southeast_region_uuid, true, NOW(), false, null, 'Sudeste');
insert into relationships (uuid, active, created, deleted, modified, region) values(south_region_uuid, true, NOW(), false, null, 'Sul');

-- insert north region states
insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, north_region_uuid, 'RO');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
values (uuid_generate_v4(), true, NOW(), false, null, north_region_uuid, 'AC');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, north_region_uuid, 'AM');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, north_region_uuid, 'RR');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, north_region_uuid, 'AP');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, north_region_uuid, 'PA');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, north_region_uuid, 'TO');

-- insert northeast region states
insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, northeast_region_uuid, 'MA');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, northeast_region_uuid, 'BA');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, northeast_region_uuid, 'CE');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, northeast_region_uuid, 'RN');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, northeast_region_uuid, 'PB');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, northeast_region_uuid, 'PE');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, northeast_region_uuid, 'AL');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, northeast_region_uuid, 'SE');

-- insert midwest region states
insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, midwest_region_uuid, 'MT');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, midwest_region_uuid, 'MS');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, midwest_region_uuid, 'GO');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, midwest_region_uuid, 'DF');

-- insert southeast region states
insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, southeast_region_uuid, 'SP');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, southeast_region_uuid, 'MG');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, southeast_region_uuid, 'ES');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, southeast_region_uuid, 'RJ');

-- insert south region states
insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, south_region_uuid, 'PR');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, south_region_uuid, 'SC');

insert into states (uuid, active, created, deleted, modified, relationship_uuid, code)
    values (uuid_generate_v4(), true, NOW(), false, null, south_region_uuid, 'RS');

END $$;
