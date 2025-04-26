-- -- Users data
-- INSERT INTO users (id, fullname, email, phone_number, password, birthday, bio) VALUES
-- (1, 'John Smith', 'john.smith@email.com', '+1-555-123-4567', 'hashed_password_1', TO_DATE('1985-03-15', 'YYYY-MM-DD'), 'Experienced traveler who loves to explore new cities and cultures. I work as a software engineer and enjoy photography in my free time.');

-- INSERT INTO users (id, fullname, email, phone_number, password, birthday, bio) VALUES
-- (2, 'Maria Garcia', 'maria.garcia@email.com', '+1-555-234-5678', 'hashed_password_2', TO_DATE('1990-07-22', 'YYYY-MM-DD'), 'Food enthusiast and cooking blogger. I travel to discover authentic local cuisines and share my experiences with my followers.');

-- INSERT INTO users (id, fullname, email, phone_number, password, birthday, bio) VALUES
-- (3, 'Ahmed Hassan', 'ahmed.hassan@email.com', '+20-100-456-7890', 'hashed_password_3', TO_DATE('1978-11-05', 'YYYY-MM-DD'), 'Property investor and landlord with multiple listings across the country. I ensure all my properties provide exceptional comfort and value.');

-- INSERT INTO users (id, fullname, email, phone_number, password, birthday, bio) VALUES
-- (4, 'Sarah Johnson', 'sarah.johnson@email.com', '+1-555-345-6789', 'hashed_password_4', TO_DATE('1992-09-18', 'YYYY-MM-DD'), 'Digital nomad working remotely while exploring the world. I value clean, quiet spaces with reliable internet connections.');

-- INSERT INTO users (id, fullname, email, phone_number, password, birthday, bio) VALUES
-- (5, 'Carlos Rodriguez', 'carlos.rodriguez@email.com', '+34-611-234-567', 'hashed_password_5', TO_DATE('1983-05-30', 'YYYY-MM-DD'), 'Architect with a passion for unique and well-designed spaces. I appreciate properties with character and thoughtful layouts.');

-- INSERT INTO users (id, fullname, email, phone_number, password, birthday, bio) VALUES
-- (6, 'Emma Wilson', 'emma.wilson@email.com', '+44-7700-900-123', 'hashed_password_6', TO_DATE('1995-12-10', 'YYYY-MM-DD'), 'Graduate student researching sustainable tourism. I prefer eco-friendly accommodations and enjoy connecting with local communities.');

-- INSERT INTO users (id, fullname, email, phone_number, password, birthday, bio) VALUES
-- (7, 'Hiroshi Tanaka', 'hiroshi.tanaka@email.com', '+81-90-1234-5678', 'hashed_password_7', TO_DATE('1975-08-25', 'YYYY-MM-DD'), 'Business executive who travels frequently for work. I value efficiency, cleanliness, and good locations close to business districts.');

-- INSERT INTO users (id, fullname, email, phone_number, password, birthday, bio) VALUES
-- (8, 'Priya Patel', 'priya.patel@email.com', '+91-98765-43210', 'hashed_password_8', TO_DATE('1988-04-12', 'YYYY-MM-DD'), 'Family-oriented traveler who enjoys vacationing with my husband and two children. We look for spacious, child-friendly accommodations.');

-- INSERT INTO users (id, fullname, email, phone_number, password, birthday, bio) VALUES
-- (9, 'Lucas Müller', 'lucas.mueller@email.com', '+49-151-1234-5678', 'hashed_password_9', TO_DATE('1980-01-20', 'YYYY-MM-DD'), 'Outdoor enthusiast who loves hiking and skiing. I prefer properties close to nature and adventure activities.');

-- INSERT INTO users (id, fullname, email, phone_number, password, birthday, bio) VALUES
-- (10, 'Sophia Chen', 'sophia.chen@email.com', '+86-138-1234-5678', 'hashed_password_10', TO_DATE('1993-06-08', 'YYYY-MM-DD'), 'Art curator who travels for exhibitions and gallery openings. I appreciate aesthetically pleasing spaces with artistic touches.');

-- -- Properties data
-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (1, 'Luxury Beachfront Villa', 'Malibu, California', 'Stunning oceanfront property with panoramic views of the Pacific. Features include a private pool, direct beach access, and a gourmet kitchen. Perfect for a luxurious getaway.', 8, 4, 4, 750.00, 4.9, 27);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (2, 'Cozy Downtown Apartment', 'New York City, New York', 'Modern apartment in the heart of Manhattan. Walking distance to major attractions, restaurants, and shopping. Recently renovated with high-end finishes.', 2, 1, 1, 200.00, 4.7, 103);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (3, 'Historic Townhouse', 'Boston, Massachusetts', 'Charming 19th-century townhouse in Beacon Hill. Original architectural details combined with modern amenities. Close to Boston Common and Freedom Trail.', 6, 3, 3, 350.00, 4.8, 56);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (4, 'Mountain Retreat Cabin', 'Aspen, Colorado', 'Rustic yet luxurious cabin with breathtaking mountain views. Wood-burning fireplace, hot tub, and ski-in/ski-out access. Perfect for winter sports enthusiasts.', 4, 2, 2, 400.00, 4.9, 42);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (5, 'Urban Loft', 'Chicago, Illinois', 'Spacious industrial loft in a converted warehouse. High ceilings, exposed brick, and large windows. Located in the trendy West Loop neighborhood.', 3, 2, 1, 175.00, 4.6, 89);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (6, 'Seaside Cottage', 'Cape Cod, Massachusetts', 'Quaint cottage just steps from the beach. Enjoy beautiful sunsets from the wraparound porch. Fully equipped kitchen and outdoor BBQ area.', 5, 3, 2, 225.00, 4.7, 64);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (7, 'Desert Oasis Villa', 'Scottsdale, Arizona', 'Contemporary villa with private pool and stunning desert landscape views. Features include a spa-like master bathroom, outdoor kitchen, and fire pit.', 6, 3, 3, 300.00, 4.8, 31);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (8, 'Lakefront Cabin', 'Lake Tahoe, California', 'Cozy cabin with spectacular lake views. Private dock, kayaks included, and large deck for outdoor dining. Great for summer and winter activities.', 6, 3, 2, 275.00, 4.7, 48);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (9, 'French Quarter Apartment', 'New Orleans, Louisiana', 'Elegant apartment in a historic building with wrought-iron balcony overlooking the lively streets. Walking distance to jazz clubs, restaurants, and attractions.', 2, 1, 1, 150.00, 4.5, 72);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (10, 'Pacific Northwest Treehouse', 'Portland, Oregon', 'Unique treehouse experience surrounded by towering pines. Eco-friendly design with modern amenities. Perfect for nature lovers seeking a peaceful retreat.', 2, 1, 1, 195.00, 4.9, 37);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (11, 'Tropical Paradise Villa', 'Kauai, Hawaii', 'Luxurious villa surrounded by lush tropical gardens. Infinity pool with ocean views, outdoor shower, and short walk to a secluded beach.', 8, 4, 4, 650.00, 4.9, 23);

-- INSERT INTO properties (id, title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) VALUES
-- (12, 'Downtown Penthouse', 'Seattle, Washington', 'Upscale penthouse with floor-to-ceiling windows offering panoramic city and water views. Rooftop terrace, gourmet kitchen, and luxury furnishings.', 4, 2, 2, 450.00, 4.8, 51);

-- -- Rent Proposals data
-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (1, 1, 3, TO_DATE('2023-06-15', 'YYYY-MM-DD'), TO_DATE('2023-06-20', 'YYYY-MM-DD'), 4, 1750.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (2, 2, 5, TO_DATE('2023-07-10', 'YYYY-MM-DD'), TO_DATE('2023-07-15', 'YYYY-MM-DD'), 2, 875.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (3, 4, 1, TO_DATE('2023-08-05', 'YYYY-MM-DD'), TO_DATE('2023-08-12', 'YYYY-MM-DD'), 6, 5250.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (4, 6, 8, TO_DATE('2023-09-20', 'YYYY-MM-DD'), TO_DATE('2023-09-25', 'YYYY-MM-DD'), 4, 1375.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (5, 8, 10, TO_DATE('2023-10-15', 'YYYY-MM-DD'), TO_DATE('2023-10-18', 'YYYY-MM-DD'), 2, 585.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (6, 9, 7, TO_DATE('2023-11-22', 'YYYY-MM-DD'), TO_DATE('2023-11-26', 'YYYY-MM-DD'), 5, 1200.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (7, 10, 2, TO_DATE('2023-12-28', 'YYYY-MM-DD'), TO_DATE('2024-01-02', 'YYYY-MM-DD'), 2, 1000.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (8, 5, 11, TO_DATE('2024-01-15', 'YYYY-MM-DD'), TO_DATE('2024-01-22', 'YYYY-MM-DD'), 6, 4550.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (9, 7, 4, TO_DATE('2024-02-10', 'YYYY-MM-DD'), TO_DATE('2024-02-15', 'YYYY-MM-DD'), 3, 2000.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (10, 3, 9, TO_DATE('2024-03-05', 'YYYY-MM-DD'), TO_DATE('2024-03-08', 'YYYY-MM-DD'), 2, 450.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (11, 1, 6, TO_DATE('2024-04-18', 'YYYY-MM-DD'), TO_DATE('2024-04-23', 'YYYY-MM-DD'), 4, 1125.00);

-- INSERT INTO rent_proposals (id, user_id, property_id, start_date, end_date, number_of_guests, total_price) VALUES
-- (12, 2, 12, TO_DATE('2024-05-20', 'YYYY-MM-DD'), TO_DATE('2024-05-25', 'YYYY-MM-DD'), 3, 2250.00);

-- -- Reviews data
-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (1, 1, 3, 4.5, 'Beautiful townhouse with lots of character. Great location for exploring Boston. The kitchen was well-equipped and the beds were very comfortable. Would definitely stay here again!');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (2, 2, 5, 4.0, 'Loved the industrial feel of this loft. Very spacious and the location was perfect for our needs. Only downside was some noise from the street at night.');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (3, 4, 1, 5.0, 'Absolutely stunning property! The views were even better than in the photos. Everything was immaculate and the host was very responsive. A perfect luxury getaway.');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (4, 6, 8, 4.5, 'We had a wonderful time at this lakefront cabin. The private dock was perfect for swimming and the kayaks were a great bonus. Kitchen could use some updating but overall a fantastic stay.');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (5, 8, 10, 5.0, 'Such a unique experience staying in this treehouse! It was cozy yet had all the amenities we needed. The surrounding forest was peaceful and beautiful.');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (6, 9, 7, 4.0, 'The desert villa was beautiful and the pool was refreshing in the Arizona heat. The outdoor kitchen was great for entertaining. Would have given 5 stars but had some issues with the AC.');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (7, 10, 2, 4.5, 'Perfect location in Manhattan! The apartment was small but very well designed and had everything we needed. The host provided great recommendations for local restaurants.');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (8, 5, 11, 5.0, 'Paradise found! This villa exceeded all our expectations. The gardens were lush and beautiful, and the beach was just a short walk away. We''re already planning our return trip.');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (9, 7, 4, 4.5, 'Excellent ski-in/ski-out location. The cabin was warm and inviting after a day on the slopes. The hot tub was a big hit with our group.');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (10, 3, 9, 3.5, 'Great location in the French Quarter. The balcony was perfect for people watching. The apartment could use some updating, but it was clean and comfortable.');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (11, 1, 6, 4.0, 'Lovely cottage with a great porch for enjoying the sea breeze. Beach access was convenient and the kitchen had everything we needed for cooking seafood dinners.');

-- INSERT INTO reviews (id, user_id, property_id, rating, commentaire) VALUES
-- (12, 2, 12, 5.0, 'The views from this penthouse were breathtaking! High-end finishes throughout and the rooftop terrace was perfect for evening cocktails. Would highly recommend!');

-- -- Approvals data
-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (1, 1, 3, 'approved');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (2, 2, 5, 'approved');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (3, 3, 3, 'approved');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (4, 4, 7, 'approved');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (5, 5, 9, 'rejected');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (6, 6, 3, 'approved');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (7, 7, 5, 'pending');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (8, 8, 3, 'pending');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (9, 9, 7, 'approved');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (10, 10, 9, 'pending');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (11, 11, 3, 'rejected');

-- INSERT INTO approvals (id, proposal_id, landlord_id, status) VALUES
-- (12, 12, 5, 'pending');

-- -- -- Add the PropertyImages table with BLOB storage
-- -- CREATE TABLE PropertyImages (
-- --     id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
-- --     property_id INT NOT NULL,
-- --     image_data BLOB NOT NULL,
-- --     file_name VARCHAR2(255) NOT NULL,
-- --     mime_type VARCHAR2(100) NOT NULL,
-- --     file_size NUMBER NOT NULL,
-- --     caption VARCHAR2(255),
-- --     display_order NUMBER(2) NOT NULL,
-- --     is_primary NUMBER(1) DEFAULT 0 NOT NULL,
-- --     uploaded_at TIMESTAMP DEFAULT SYSTIMESTAMP,
-- --     uploaded_by VARCHAR2(100),
-- --     CONSTRAINT fk_property_images_property
-- --         FOREIGN KEY (property_id)
-- --         REFERENCES Properties(id)
-- --         ON DELETE CASCADE,
-- --     CONSTRAINT chk_is_primary CHECK (is_primary IN (0, 1))
-- -- );

-- -- -- Create an index for faster queries
-- -- CREATE INDEX idx_property_images_property_id ON PropertyImages(property_id);

-- -- -- Create a unique index to ensure only one primary image per property
-- -- CREATE UNIQUE INDEX idx_property_images_primary 
-- -- ON PropertyImages(property_id) 
-- -- WHERE is_primary = 1;

-- -- Note: Actual BLOB data would be inserted programmatically through your application
-- -- This is a placeholder comment to indicate where you would insert image data
-- -- INSERT INTO PropertyImages (property_id, image_data, file_name, mime_type, file_size, caption, display_order, is_primary, uploaded_by)
-- -- VALUES (1, [BLOB DATA], 'beach_view.jpg', 'image/jpeg', 1048576, 'Stunning ocean view', 1, 1, 'admin');
INSERT INTO properties VALUES (1000, 'Cozy Apartment in Downtown', 'New York, NY', 120.00, 4, 2, 2, 1, 'Modern apartment close to subway and shopping.', 1);
INSERT INTO properties VALUES (1001, 'Beachfront Villa', 'Malibu, CA', 450.00, 8, 4, 4, 3, 'Luxury villa with ocean views and private pool.', 2);
INSERT INTO properties VALUES (1002, 'Mountain Retreat', 'Aspen, CO', 300.00, 6, 3, 3, 2, 'Peaceful cabin surrounded by nature.', 3);
INSERT INTO properties VALUES (1003, 'Urban Studio', 'Chicago, IL', 95.00, 2, 1, 1, 1, 'Simple studio for solo travelers or couples.', 1);
INSERT INTO properties VALUES (1004, 'Modern Loft', 'San Francisco, CA', 200.00, 3, 2, 1, 1, 'Stylish loft with open space and sunlight.', 2);
INSERT INTO properties VALUES (1005, 'Countryside House', 'Nashville, TN', 150.00, 5, 3, 2, 2, 'Relaxing house near the countryside.', 3);
INSERT INTO properties VALUES (1006, 'Lakeview Cabin', 'Lake Tahoe, NV', 220.00, 6, 3, 3, 2, 'Scenic views of the lake with cozy interiors.', 1);
INSERT INTO properties VALUES (1007, 'Penthouse Suite', 'Miami, FL', 500.00, 6, 3, 3, 2, 'Top-floor luxury suite near beach.', 2);
INSERT INTO properties VALUES (1008, 'Tiny Home', 'Portland, OR', 70.00, 2, 1, 1, 1, 'Eco-friendly tiny home for short stays.', 3);
INSERT INTO properties VALUES (1009, 'Ranch Stay', 'Austin, TX', 180.00, 5, 3, 3, 2, 'Farmhouse experience in the Texas hills.', 1);

INSERT INTO properties VALUES (1010, 'Desert House', 'Phoenix, AZ', 160.00, 4, 2, 2, 2, 'Modern home with desert-style decor.', 2);
INSERT INTO properties VALUES (1011, 'Ski-in/Ski-out Chalet', 'Park City, UT', 400.00, 8, 4, 4, 3, 'Perfect for winter vacations.', 3);
INSERT INTO properties VALUES (1012, 'Historic Bungalow', 'Savannah, GA', 140.00, 4, 2, 2, 1, 'Beautiful restored home in the historic district.', 1);
INSERT INTO properties VALUES (1013, 'Treehouse Experience', 'Atlanta, GA', 130.00, 2, 1, 1, 1, 'Unique elevated stay among trees.', 2);
INSERT INTO properties VALUES (1014, 'Downtown Condo', 'Seattle, WA', 190.00, 3, 2, 1, 1, 'Modern condo in the heart of the city.', 3);
INSERT INTO properties VALUES (1015, 'Ocean Bungalow', 'Honolulu, HI', 420.00, 4, 2, 2, 2, 'Beach paradise with private access.', 1);
INSERT INTO properties VALUES (1016, 'Studio Near Campus', 'Boston, MA', 110.00, 2, 1, 1, 1, 'Ideal for visiting students and scholars.', 2);
INSERT INTO properties VALUES (1017, 'Luxury Farmhouse', 'Boulder, CO', 320.00, 6, 4, 3, 3, 'Modern comfort meets rustic charm.', 3);
INSERT INTO properties VALUES (1018, 'Basement Apartment', 'Detroit, MI', 85.00, 2, 1, 1, 1, 'Affordable and newly renovated.', 1);
INSERT INTO properties VALUES (1019, 'Modern Cabin', 'Smoky Mountains, TN', 230.00, 5, 3, 2, 2, 'Nature retreat with all modern amenities.', 2);

INSERT INTO properties VALUES (1020, 'Loft with City View', 'Los Angeles, CA', 275.00, 4, 2, 2, 2, 'Panoramic views and close to everything.', 3);
INSERT INTO properties VALUES (1021, 'Eco Lodge', 'Sedona, AZ', 210.00, 4, 2, 2, 2, 'Eco-conscious stay in the red rocks.', 1);
INSERT INTO properties VALUES (1022, 'Family Villa', 'Orlando, FL', 240.00, 6, 3, 3, 2, 'Great for family vacations near parks.', 2);
INSERT INTO properties VALUES (1023, 'Minimalist Flat', 'San Jose, CA', 180.00, 2, 1, 1, 1, 'Clean and simple space with smart tech.', 3);
INSERT INTO properties VALUES (1024, 'Vintage Home', 'Philadelphia, PA', 160.00, 4, 2, 2, 2, 'Classic charm in a quiet neighborhood.', 1);
INSERT INTO properties VALUES (1025, 'Island Escape', 'Key West, FL', 380.00, 5, 3, 3, 2, 'Relaxing island vibes with beach access.', 2);
INSERT INTO properties VALUES (1026, 'High-Rise Apartment', 'Denver, CO', 195.00, 3, 2, 1, 1, 'Skyline views with luxury furniture.', 3);
INSERT INTO properties VALUES (1027, 'Suburban Retreat', 'Columbus, OH', 145.00, 4, 2, 2, 1, 'Quiet place for families.', 1);
INSERT INTO properties VALUES (1028, 'Countryside Cottage', 'Burlington, VT', 125.00, 3, 2, 2, 1, 'Cozy place with fireplace and garden.', 2);
INSERT INTO properties VALUES (1029, 'Studio in Arts District', 'Dallas, TX', 105.00, 2, 1, 1, 1, 'Trendy studio surrounded by culture.', 3);

 COMMIT;