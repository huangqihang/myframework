DROP TABLE address;

CREATE 
TABLE 	address
	(
		id int unsigned NOT NULL AUTO_INCREMENT,
		street character varying(255) NOT NULL,
		city character varying(100) NOT NULL,
		state character varying(100),
		province character varying(100) NOT NULL,
		country character varying(100) NOT NULL,
		postcode character varying(50) NOT NULL,
		CONSTRAINT address_pkey PRIMARY KEY (id)
	)
;