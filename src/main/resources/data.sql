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


insert into products (discount, name, price, short_description, warranty_period_month, category_id) VALUES (0, 'Odobingiz obruyingiz', 6, 'some short description', 0, 5);    -- product id = 1
insert into products (discount, name, price, short_description, warranty_period_month, category_id) VALUES (0, 'Oltin Silsila', 10, 'some short description', 0, 4);          -- product id = 2
insert into products (discount, name, price, short_description, warranty_period_month, category_id) VALUES (0, 'Xadis va Hayot', 4, 'some short description', 0, 4);          -- product id = 3
insert into products (discount, name, price, short_description, warranty_period_month, category_id) VALUES (0, 'Barakaning Sirlari', 1, 'some short description', 0, 3);      -- product id = 4
insert into products (discount, name, price, short_description, warranty_period_month, category_id) VALUES (0, 'Iqror', 4, 'some short description', 0, 3);                   -- product id = 5

insert into products_photos (products_id, photos_id) VALUES (1, 1);
insert into products_photos (products_id, photos_id) VALUES (1, 2);
insert into products_photos (products_id, photos_id) VALUES (2, 1);
insert into products_photos (products_id, photos_id) VALUES (2, 3);
insert into products_photos (products_id, photos_id) VALUES (3, 1);
insert into products_photos (products_id, photos_id) VALUES (4, 1);
insert into products_photos (products_id, photos_id) VALUES (4, 4);
insert into products_photos (products_id, photos_id) VALUES (5, 1);
insert into products_photos (products_id, photos_id) VALUES (5, 5);

