-- INSERT USERS
 INSERT
INTO
  users
  (user_Name, img)
VALUES
  ('Sergi', 'image');

--  INSERT PRODUCTS
INSERT
INTO
  product
  (name, description, user_id)
VALUES
  ('Product-01', 'description de producte', 1);
INSERT
  INTO
    product
    (name, description, user_id)
  VALUES
    ('Product-02', 'description de producte', 1);

--INSERT COMMENTS
 INSERT
INTO
  comments
  (comment, product_id)
VALUES
  ('comentari del producte 01', 1);


--INSERT LIKES

INSERT
INTO
  likes
  (user_id, product_id)
VALUES
  (1, 1);
INSERT
INTO
  likes
  (user_id, product_id)
VALUES
  (1, 2);