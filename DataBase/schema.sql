-- create table users (
--    id           int primary key,
--    fullname     varchar(100) not null,
--    email        varchar(100) not null unique,
--    phone_number varchar(20),
--    password     varchar(255) not null,
--    birthday     date,
--    bio          varchar(2000)
-- );

-- create sequence user_id_seq start with 1 increment by 1 nocache nocycle;
create sequence property_id_seq start with 1000 increment by 1 nocache nocycle;
create table properties (
   id              int primary key,
   title           varchar2(100) not null,
   location        varchar2(100) not null,
   price_per_night decimal(10,2) not null,
   max_guests      int not null,
   max_beds        int not null,
   max_bedrooms    int not null,
   max_bathrooms   int not null,
   description     varchar2(1000) not null
);
DECLARE
  seq_exists NUMBER;
BEGIN
  SELECT COUNT(*) INTO seq_exists FROM user_sequences WHERE sequence_name = 'HOUSES_SEQ';
  IF seq_exists = 0 THEN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE houses_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
  END IF;
END;
/

-- Create sequence for images table if it doesn't exist
DECLARE
  seq_exists NUMBER;
BEGIN
  SELECT COUNT(*) INTO seq_exists FROM user_sequences WHERE sequence_name = 'IMAGES_SEQ';
  IF seq_exists = 0 THEN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE images_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
  END IF;
END;
/

-- Create houses table if it doesn't exist
DECLARE
  table_exists NUMBER;
BEGIN
  SELECT COUNT(*) INTO table_exists FROM user_tables WHERE table_name = 'HOUSES';
  IF table_exists = 0 THEN
    EXECUTE IMMEDIATE 'CREATE TABLE houses (
      id NUMBER DEFAULT houses_seq.NEXTVAL PRIMARY KEY,
      title VARCHAR2(255) NOT NULL,
      description CLOB,
      location VARCHAR2(255) NOT NULL,
      price NUMBER(10,2) NOT NULL,
      bedrooms NUMBER(2) NOT NULL,
      bathrooms NUMBER(2) NOT NULL,
      beds NUMBER(2) NOT NULL,
      max_guests NUMBER(2) NOT NULL
    )';
  END IF;
END;
/

-- Create images table if it doesn't exist
DECLARE
  table_exists NUMBER;
BEGIN
  SELECT COUNT(*) INTO table_exists FROM user_tables WHERE table_name = 'IMAGES';
  IF table_exists = 0 THEN
    EXECUTE IMMEDIATE 'CREATE TABLE images (
      id NUMBER DEFAULT images_seq.NEXTVAL PRIMARY KEY,
      house_id NUMBER NOT NULL,
      url VARCHAR2(255) NOT NULL,
      caption VARCHAR2(255),
      is_primary NUMBER(1) DEFAULT 0,
      CONSTRAINT fk_house_id FOREIGN KEY (house_id) REFERENCES houses(id) ON DELETE CASCADE
    )';
  END IF;
END;
/

-- Create index for faster image lookups if it doesn't exist
DECLARE
  index_exists NUMBER;
BEGIN
  SELECT COUNT(*) INTO index_exists FROM user_indexes WHERE index_name = 'IDX_IMAGES_HOUSE_ID';
  IF index_exists = 0 THEN
    EXECUTE IMMEDIATE 'CREATE INDEX idx_images_house_id ON images(house_id)';
  END IF;
END;
/


-- Check if sequences exist
SELECT sequence_name, min_value, max_value, increment_by, last_number
FROM user_sequences
WHERE sequence_name IN ('HOUSES_SEQ', 'IMAGES_SEQ');

-- Check if tables exist with correct structure
SELECT table_name, column_name, data_type, data_length, nullable
FROM user_tab_columns
WHERE table_name IN ('HOUSES', 'IMAGES')
ORDER BY table_name, column_id;
-- create table rent_proposals (
--    id               int primary key,
--    user_id          int not null,
--    property_id      int not null,
--    start_date       date not null,
--    end_date         date not null,
--    number_of_guests int not null,
--    total_price      decimal(10,2) not null,
--    foreign key ( user_id )
--       references users ( id ),
--    foreign key ( property_id )
--       references properties ( id )
-- );
-- create table reviews (
--    id          int primary key,
--    user_id     int not null,
--    property_id int not null,
--    rating      decimal(2,1) not null,
--    commentaire varchar(1000),
--    foreign key ( user_id )
--       references users ( id ),
--    foreign key ( property_id )
--       references properties ( id )
-- );
-- create table approvals (
--    id          int primary key,
--    proposal_id int not null,
--    landlord_id int not null,
--    status      varchar2(10) default 'pending' check ( status in ( 'pending',
--                                                              'approved',
--                                                              'rejected' ) ),
--    foreign key ( proposal_id )
--       references rent_proposals ( id ),
--    foreign key ( landlord_id )
--       references users ( id )
-- );