# drop index state_name_uindex on state_province;
# drop index state_name_eng_uindex on state_province;
# drop index state_name_ext_uindex on state_province;
# drop table state_province;

create table state_province
(
	id int auto_increment comment 'state/province/region id',
	country_id int not null comment 'country id',
	name varchar(255) not null comment 'native language state/province/region name',
	name_eng varchar(255) null comment 'english state/province/region name',
	name_ext varchar(255) null comment 'extra/other state/province/region name',
    constraint state_province_pk primary key (id),
    constraint state_country_id_fk foreign key (country_id) references country (id)
);

create unique index state_name_uindex
    on state_province (name);

create unique index state_name_eng_uindex
	on state_province (name_eng);

create unique index state_name_ext_uindex
	on state_province (name_ext);
