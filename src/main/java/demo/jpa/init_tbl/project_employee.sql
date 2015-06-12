DROP TABLE
	project_employee
;

CREATE 
TABLE	project_employee
	(
		project_id int unsigned NOT NULL,
		employee_id int unsigned NOT NULL,
		CONSTRAINT project_employee_pkey PRIMARY KEY (project_id, employee_id),
		CONSTRAINT project_employee_project_fkey FOREIGN KEY (project_id)
			REFERENCES project (id),
		CONSTRAINT project_employee_employee_fkey FOREIGN KEY (employee_id)
			REFERENCES employee (id)
	)
;