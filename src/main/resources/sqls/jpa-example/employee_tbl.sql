DROP TABLE employee;

CREATE 
TABLE	employee
	(
		id int unsigned NOT NULL AUTO_INCREMENT,
		first_name character varying(255) NOT NULL,
		last_name  character varying(255) NOT NULL,
		salary numeric(10, 2),
		start_date date NOT NULL,
		end_date date,
		manager_id int unsigned NOT NULL,
		address_id int unsigned NOT NULL,
		CONSTRAINT employee_pkey PRIMARY KEY (id),
		CONSTRAINT manager_fkey FOREIGN KEY (manager_id)
			REFERENCES employee (id),
		CONSTRAINT address_fkey FOREIGN KEY (address_id)
			REFERENCES address (id)
	)
;