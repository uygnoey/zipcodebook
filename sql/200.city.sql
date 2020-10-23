# drop index city_uindex on city;
# drop index city_eng_uindex on city;
# drop index city_ext_uindex on city;
# drop index city_eng_ext_uindex on city;
# drop table city;

create table city
(
	id int auto_increment comment 'city id',
    type_id int not null comment 'type id',
	state_id int not null comment 'state/province/region id',
    city varchar(255) not null comment 'city name',
    update_date timestamp default current_timestamp comment 'update date',
    constraint city_pk primary key (id),
    constraint city_type_id_fk foreign key (type_id) references type (id),
    constraint city_state_id_fk foreign key (state_id) references state (id)
);

create unique index city_uindex
    on city (city);
