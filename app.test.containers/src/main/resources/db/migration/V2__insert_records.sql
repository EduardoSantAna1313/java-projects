-- create some records

DO
$do$
declare
	API_ID varchar(36);
	PARAM_ID VARCHAR(36);
	TITLE VARCHAR(256);
BEGIN
	FOR i IN 1..0 loop
		API_ID := gen_random_uuid();
		PARAM_ID := gen_random_uuid();
	 	TITLE := CONCAT('TITLE API ', i);

	 	-- creating api
		INSERT INTO api(id, "name", description, status, created_by, updated_BY)
			VALUES(API_ID, CONCAT('NAME API ', i), 'description teste teste', 'CREATED','teste user','teste user');

		-- creating parameter
		INSERT INTO public.api_parameters (id, api_id, "name", "VALUE")
			VALUES(PARAM_ID, API_ID, 'title', TITLE);

   END LOOP;
END
$do$;
