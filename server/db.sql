
-- country - store - 1 country could have 0 or many stores
-- country - store - item - 1 country store could have 0 or many product

CREATE TABLE country (
    id CHAR(2),
    name TEXT,
    currency_code CHAR(3),
    -- INR,USD for example
    
   PRIMARY KEY(id)
);

INSERT INTO country values('IN','India');
INSERT INTO country values('US','United States of America');

CREATE TABLE store (
    id CHAR(6),
    name TEXT,
   
    PRIMARY KEY(id)
);
INSERT INTO store values('BB','Big Basket ');
INSERT INTO store values('RR','Reliance Retail ');


CREATE TABLE product (
    name VARCHAR(255),
    description text,
    PRIMARY KEY(name)
);
INSERT INTO product values('Rice5KgBag1','Ponni Boiled Rice 5 Kg Bag');

CREATE TABLE country_store (
    id CHAR(8),
    store_id CHAR(6), 
    country_id CHAR(2),   
    PRIMARY KEY(id) ,
    CONSTRAINT fk_country
      FOREIGN KEY(country_id) 
	  REFERENCES country(id),
	CONSTRAINT fk_store
      FOREIGN KEY(store_id) 
	  REFERENCES store(id)
);

INSERT INTO country_store values('INTN01','BB','IN');

CREATE TABLE country_store_product (
-- holds item pricing info associated with the store
    id VARCHAR(263),
    product_name VARCHAR(255),
    country_store_id CHAR(8),
    sku TEXT,
    price NUMERIC,   
    -- the currency will be available in country 
    last_feed_date TIMESTAMP WITH TIME zone,
    PRIMARY KEY(id),
    CONSTRAINT fk_country_store
      FOREIGN KEY(country_store_id) 
	  REFERENCES country_store(id),
    CONSTRAINT fk_product
      FOREIGN KEY(product_name) 
	  REFERENCES product(name)	      	  
);

INSERT INTO country_store_product values('Rice5KgBag1INTN01','Rice5KgBag1','INTN01','SKU Value Goes Here',1000,'2023-02-25');



