CREATE TABLE trip_data
(
    id serial primary key,
	trip_id int,
	datetime timestamp,
	/*  position geom */
	gps_speed_ms double precision,
	gps_speed_kmh double precision,
	speed_obd_kmh double precision,
	altitude double precision,
	engine_load double precision,
	engine_rpm double precision,
	throttle_position double precision,
	air_temperature double precision,
	fuel_level double precision,
	kpl double precision
);

SELECT AddGeometryColumn ('trip_data','position',4326,'POINT',2);
