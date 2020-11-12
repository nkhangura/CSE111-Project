SELECT lo_city, lo_country, lo_locid, ili_libid, ili_locid
FROM islocatedin, location
WHERE ili_libid = 1
AND ili_locid = lo_locid


