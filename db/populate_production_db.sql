INSERT INTO trip_data (datetime, 
		position,
		gps_speed_ms,
		gps_speed_kmh,
		speed_obd_kmh,
		altitude,
		engine_load,
		engine_rpm,
		throttle_position,
		air_temperature,
		fuel_level,
		kpl)
SELECT to_timestamp(gps_time, 'Dy Mon DD HH:MI:SS    YYYY') as datetime, 
		ST_SetSRID(ST_MakePoint(long, lat),4326) as position,
		gps_speed_ms,
		gps_speed_kmh,
		speed_obd_kmh,
		altitude,
		engine_load,
		engine_rpm,
		throttle_position,
		intake_air_temperature as air_temperature,
		fuel_level,
		kpl
FROM trips_raw
ORDER BY datetime;

SELECT 
INTO trip_data
FROM trips_raw;

// Comments
id    serial primary key,
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

                               Tabelle »public.trips_raw«
         Spalte         |          Typ          | Attribute | Speicherung | Beschreibung 
------------------------+-----------------------+-----------+-------------+--------------
 long                   | double precision      |           | plain       | 
 lat                    | double precision      |           | plain       | 
 gps_speed_ms           | double precision      |           | plain       | 
 gps_speed_kmh          | double precision      |           | plain       | 
 speed_obd_kmh          | double precision      |           | plain       | 
 altitude               | double precision      |           | plain       | 
 g_total                | double precision      |           | plain       | 
 engine_load            | double precision      |           | plain       | 
 engine_rpm             | double precision      |           | plain       | 
 throttle_position      | double precision      |           | plain       | 
 intake_air_temperature | double precision      |           | plain       | 
 fuel_level             | double precision      |           | plain       | 
 kpl                    | double precision      |           | plain       | 
 kpl_avg                | double precision      |           | plain       | 
 trip_distance          | double precision      |           | plain       | 
 gps_time               | character varying(50) |           | extended    | 
 device_time            | character varying(50) |           | extended    | 
 

