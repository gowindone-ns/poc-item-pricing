CREATE TABLE country (
    id CHAR(2),
    name TEXT,
    currency_code CHAR(3),
    -- INR,USD for example
    
   PRIMARY KEY(id)
);

INSERT INTO country values('IN','India');
INSERT INTO country values('US','United States');

CREATE TABLE store (
    id CHAR(6),
    name TEXT,
   
    PRIMARY KEY(id)
);

INSERT INTO store values('BB','Big Basket ');


CREATE TABLE product (
    name VARCHAR(255),
    description text,
    PRIMARY KEY(name)
);

INSERT INTO product values('Rice5KgBag1','Ponni Boiled Rice 5 Kg Bag');
INSERT INTO product values('Rice10KgBag1','Ponni Boiled Rice 10 Kg Bag');
INSERT INTO product values('Rice25KgBag1','Ponni Boiled Rice 25 Kg Bag');
INSERT INTO product values('Rice50KgBag1','Ponni Boiled Rice 50 Kg Bag');
INSERT INTO product values('RawRice5KgBag1','Ponni Raw Rice 5 Kg Bag');
INSERT INTO product values('RawRice10KgBag1','Ponni Raw Rice 10 Kg Bag');


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
INSERT INTO country_store values('USNY01','BB','US');


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
INSERT INTO country_store_product values('Rice10KgBag1INTN01','Rice10KgBag1','INTN01','SKU Value Goes Here',2000,'2023-02-25');
INSERT INTO country_store_product values('Rice25KgBag1INTN01','Rice25KgBag1','INTN01','SKU Value Goes Here',5000,'2023-02-25');
INSERT INTO country_store_product values('RawRice5KgBag1INTN01','RawRice5KgBag1','INTN01','SKU Value Goes Here',1200,'2023-02-25');
INSERT INTO country_store_product values('RawRice10KgBag1INTN01','RawRice10KgBag1','INTN01','SKU Value Goes Here',2400,'2023-02-25');


select csp.* from country c 
  inner join country_store cs on c.id =cs.country_id  
  inner join country_store_product csp  on csp.country_store_id = cs.id where c.id = 'IN';
 
 create view country_store_product_info as select csp.id ,csp.product_name,csp.country_store_id,csp.sku ,csp.price ,csp.last_feed_date  from country_store_product csp  ;

 

drop view country_store_product_info;
 
 create view country_store_product_info as select csp.*,c.id as country_code from country c 
  inner join country_store cs on c.id =cs.country_id  
  inner join country_store_product csp  on csp.country_store_id = cs.id order by csp.last_feed_date desc;
 



