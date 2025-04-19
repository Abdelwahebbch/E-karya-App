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