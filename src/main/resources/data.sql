insert into genders (name) VALUES ('MALE');   --gender id = 1
insert into genders (name) VALUES ('FEMALE'); --gender id = 2

insert into users
(date_of_birth, email, enabled, full_name, gender_id, password, phone_number)
VALUES (now(), 'user@gmail.com', true, 'user', 1, '123', '123456789');  -- user id = 1

insert into attachments (content_type, original_name, size) values ('image/png', 'photo1', 120);  -- attachment id = 1
insert into attachments (content_type, original_name, size) values ('image/png', 'photo2', 120);  -- attachment id = 2
insert into attachments (content_type, original_name, size) values ('image/png', 'photo3', 120);  -- attachment id = 3
insert into attachments (content_type, original_name, size) values ('image/png', 'photo4', 120);  -- attachment id = 4
insert into attachments (content_type, original_name, size) values ('image/png', 'photo5', 120);  -- attachment id = 5


insert into categories (name, attachment_id, parent_category_id) VALUES ('Books', 1, null);                     -- category id = 1
insert into categories (name, attachment_id, parent_category_id) VALUES ('Religious', 2, 1);                    -- category id = 2
insert into categories (name, attachment_id, parent_category_id) VALUES ('Yangi Asr Avlodi Nashriyoti', 3, 2);  -- category id = 3
insert into categories (name, attachment_id, parent_category_id) VALUES ('«Hilol-Nashr» nashriyoti', 4, 2);     -- category id = 4
insert into categories (name, attachment_id, parent_category_id) VALUES ('Azon kitoblari', 5, 2);               -- category id = 5


insert into products (discount, name, price, short_description, warranty_period_month, category_id, cover_image_id) VALUES (0, 'Odobingiz obruyingiz', 6, 'some short description', 0, 5, 1);    -- product id = 1
insert into products (discount, name, price, short_description, warranty_period_month, category_id, cover_image_id) VALUES (0, 'Oltin Silsila', 10, 'some short description', 0, 4, 2);          -- product id = 2
insert into products (discount, name, price, short_description, warranty_period_month, category_id, cover_image_id) VALUES (0, 'Xadis va Hayot', 4, 'some short description', 0, 4, 3);          -- product id = 3
insert into products (discount, name, price, short_description, warranty_period_month, category_id, cover_image_id) VALUES (0, 'Barakaning Sirlari', 1, 'some short description', 0, 3, 4);      -- product id = 4
insert into products (discount, name, price, short_description, warranty_period_month, category_id, cover_image_id) VALUES (0, 'Iqror', 4, 'some short description', 0, 3, 5);                   -- product id = 5

insert into products_photos (products_id, photos_id) VALUES (1, 1);
insert into products_photos (products_id, photos_id) VALUES (1, 2);
insert into products_photos (products_id, photos_id) VALUES (2, 1);
insert into products_photos (products_id, photos_id) VALUES (2, 3);
insert into products_photos (products_id, photos_id) VALUES (3, 1);
insert into products_photos (products_id, photos_id) VALUES (4, 1);
insert into products_photos (products_id, photos_id) VALUES (4, 4);
insert into products_photos (products_id, photos_id) VALUES (5, 1);
insert into products_photos (products_id, photos_id) VALUES (5, 5);

insert into product_descriptions (description, product_id) VALUES ('Some full description with details', 1);

insert into characteristics (name) VALUES ('Language');     -- id = 1
insert into characteristics (name) VALUES ('Page');         -- id = 2
insert into characteristics (name) VALUES ('CoverType');    -- id = 3
insert into characteristics (name) VALUES ('BookType');     -- id = 4
insert into characteristics (name) VALUES ('YearOfIssue');  -- id = 5
insert into characteristics (name) VALUES ('Inscription');  -- id = 6
insert into characteristics (name) VALUES ('Publisher');    -- id = 7
insert into characteristics (name) VALUES ('Author');       -- id = 8

insert into values (value) VALUES ('Uzbek');            -- id = 1
insert into values (value) VALUES ('234');              -- id = 2
insert into values (value) VALUES ('Leather');          -- id = 3
insert into values (value) VALUES ('Paper book');       -- id = 4
insert into values (value) VALUES ('2021');             -- id = 5
insert into values (value) VALUES ('Kirill');           -- id = 6
insert into values (value) VALUES ('Yangi Asr Avlodi'); -- id = 7
insert into values (value) VALUES ('Xolid Ertugrul');   -- id = 8

insert into characteristics_values (characteristic_id, value_id) VALUES (1, 1);  -- id = 1
insert into characteristics_values (characteristic_id, value_id) VALUES (2, 2);  -- id = 2
insert into characteristics_values (characteristic_id, value_id) VALUES (3, 3);  -- id = 3
insert into characteristics_values (characteristic_id, value_id) VALUES (4, 4);  -- id = 4
insert into characteristics_values (characteristic_id, value_id) VALUES (5, 5);  -- id = 5
insert into characteristics_values (characteristic_id, value_id) VALUES (6, 6);  -- id = 6
insert into characteristics_values (characteristic_id, value_id) VALUES (7, 7);  -- id = 7
insert into characteristics_values (characteristic_id, value_id) VALUES (8, 8);  -- id = 8

-- Adding Characteristics to Product with Id = 1
insert into products_characteristics_values (products_id, characteristics_values_id) VALUES (1, 1);
insert into products_characteristics_values (products_id, characteristics_values_id) VALUES (1, 2);
insert into products_characteristics_values (products_id, characteristics_values_id) VALUES (1, 3);
insert into products_characteristics_values (products_id, characteristics_values_id) VALUES (1, 4);
insert into products_characteristics_values (products_id, characteristics_values_id) VALUES (1, 5);
insert into products_characteristics_values (products_id, characteristics_values_id) VALUES (1, 6);
insert into products_characteristics_values (products_id, characteristics_values_id) VALUES (1, 7);
insert into products_characteristics_values (products_id, characteristics_values_id) VALUES (1, 8);

insert into regions (name) VALUES ('Toshkent Shahri');
insert into districts (name, region_id) VALUES ('Chilonzor', 1);
insert into pay_types (commission_fee, name) VALUES (0.3, 'Stripe');