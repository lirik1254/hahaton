alter table device
drop column building_id;

alter table device
add column building_id integer;