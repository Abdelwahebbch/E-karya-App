
-- Les Sequances 

-- create sequence user_id_seq start with 1 increment by 1 nocache nocycle;
-- create sequence property_id_seq start with 1000 increment by 1 nocache nocycle;
-- create sequence booking_id_seq start with 5000 increment by 1;


-- create table users (
--    id           int primary key,
--    fullname     varchar(100) not null,
--    email        varchar(100) not null unique,
--    phone_number varchar(20),
--    password     varchar(255) not null,
--    birthday     date,
--    bio          varchar(2000)
-- );


-- create table properties (
--    id              int primary key,
--    title           varchar2(100) not null,
--    location        varchar2(100) not null,
--    price_per_night decimal(10,2) not null,
--    status          int default 0 not null,
--    max_guests      int not null,
--    max_beds        int not null,
--    max_bedrooms    int not null,
--    max_bathrooms   int not null,
--    description     varchar2(1000) not null,
--    landlord_id     int not null,
--    constraint fk_user foreign key ( landlord_id )
--       references users ( id )
-- );

-- create table booking (
--    booking_id  int primary key not null,
--    landlord_id int not null,
--    user_id     int not null,
--    property_id int not null,
--    start_date  date not null,
--    end_date    date not null,
--    foreign key ( user_id )
--       references users ( id ),
--    foreign key ( landlord_id )
--       references users ( id ),
--    foreign key ( property_id )
--       references properties ( id )
-- );


-- CREATE OR REPLACE PROCEDURE update_property_status AS
-- BEGIN
--     -- Update the status of properties where the booking end_date is today's date
--     UPDATE properties
--     SET status = 0
--     WHERE id IN (
--         SELECT property_id
--         FROM booking
--         WHERE end_date = SYSDATE
--     );
-- END update_property_status;


-- BEGIN
--    DBMS_SCHEDULER.create_job (
--       job_name        => 'UPDATE_PROPERTY_STATUS_JOB',
--       job_type        => 'PLSQL_BLOCK',
--       job_action      => 'BEGIN update_property_status; END;',
--       start_date      => SYSTIMESTAMP,        
--       repeat_interval => 'FREQ=DAILY; BYHOUR=23; BYMINUTE=59; BYSECOND=59',
--       enabled         => TRUE
--    );
-- END;
-- /



-- drop TABLE PROPERTY_IMAGES;
-- drop TABLE properties;




-- CREATE TABLE property_images (
--     id          NUMBER          NOT NULL,
--     property_id    NUMBER          NOT NULL,
--     image       BLOB            NOT NULL,
--     is_primary  NUMBER(1,0)     DEFAULT 0,
--     CONSTRAINT pk_house_images PRIMARY KEY (id),
--     CONSTRAINT fk_house_images_house
--         FOREIGN KEY (property_id) REFERENCES properties(id)
-- );






































-- create table rent_proposals (
--    id               int primary key,
--    user_id          int not null,
--    owner_id          int not null,
--    property_id      int not null,
--    start_date       date not null,
--    end_date         date not null,
--    number_of_guests int not null,
--    total_price      decimal(10,2) not null,
--    foreign key ( user_id )
--       references users ( id ),
--    foreign key ( owner_id )
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


-- CREATE TABLE property_images (
--     id NUMBER PRIMARY KEY,
--     house_id NUMBER NOT NULL,
--     image BLOB NOT NULL,
--     is_primary NUMBER(1) DEFAULT 0,
--     CONSTRAINT fk_house FOREIGN KEY (house_id) REFERENCES properties(id)
-- );
-- -- create sequence property_image_id_seq start with 1 increment by 1 nocache nocycle;



