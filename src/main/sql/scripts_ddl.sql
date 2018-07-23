drop table product;
drop table shopping_cart;
drop table shopping_cart_item;
create table PRODUCT (
  ID integer primary key,
  COD varchar(50),
  DESCRIPTION varchar(200),
  CATEGORY varchar(100) not null,
  IMPORTED number(1) not null,
  price number not null
);

create table SHOPPING_CART (
  ID integer primary key,
  COD varchar(50),
  DESCRIPTION varchar(200)
);

create table SHOPPING_CART_ITEM (
  ID integer primary key,
  COD varchar(50),
  DESCRIPTION varchar(200),
  QUANTITY integer,
  PRODUCT_FK integer,
  SHOPPING_CART_FK integer,
  foreign key (PRODUCT_FK) references PRODUCT(ID),
  foreign key (SHOPPING_CART_FK) REFERENCES SHOPPING_CART(ID)
);

-- insert into SHOPPING_CART(ID, COD, DESCRIPTION) VALUES (1, 'CART1', 'Input 1');
--
-- insert into PRODUCT(ID, COD, DESCRIPTION, CATEGORY, IMPORTED, price) VALUES (1, '1', 'book', 'BOOKS', 0, 12.49);
-- insert into PRODUCT(ID, COD, DESCRIPTION, CATEGORY, IMPORTED, price) VALUES (2, '2', 'music CD', 'OTHER', 0, 14.99);
-- insert into PRODUCT(ID, COD, DESCRIPTION, CATEGORY, IMPORTED, price) VALUES (3, '3', 'chocolate bar', 'FOOD', 0, 0.85);
--
-- insert into SHOPPING_CART_ITEM(ID, COD, DESCRIPTION, QUANTITY, PRODUCT_FK, SHOPPING_CART_FK) values (1, 'item1', 'cart1 - item1', 1, 1, 1);
-- insert into SHOPPING_CART_ITEM(ID, COD, DESCRIPTION, QUANTITY, PRODUCT_FK, SHOPPING_CART_FK) values (2, 'item2', 'cart1 - item2', 1, 2, 1);
-- insert into SHOPPING_CART_ITEM(ID, COD, DESCRIPTION, QUANTITY, PRODUCT_FK, SHOPPING_CART_FK) values (3, 'item3', 'cart1 - item3', 1, 3, 1);