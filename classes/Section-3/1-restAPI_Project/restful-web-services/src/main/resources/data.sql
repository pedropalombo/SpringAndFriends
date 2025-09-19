-- user registration
INSERT INTO user_details(id, birth_date,name)
	VALUES(1001, current_date(), 'Dadus Basilicus');
	
INSERT INTO user_details(id, birth_date,name)
	VALUES(1002, current_date(), 'Valorious Multus');
	
INSERT INTO user_details(id, birth_date,name)
	VALUES(1003, current_date(), 'Dr. Nefarius');

-- post cataloging
INSERT INTO post(id, description, user_id)
	VALUES (51, 'it´s a me, Nefarius', 1003);

INSERT INTO post(id, description, user_id)
	VALUES (52, 'snap back to Basilicus',1001);

INSERT INTO post(id, description, user_id)
	VALUES (53, '1 + 1 = Valorious B)',1002);

INSERT INTO post(id, description, user_id)
	VALUES (54, 'another post, another Multus',1002);
