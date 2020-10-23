# drop index address_uindex on address;
# drop index address_eng_uindex on address;
# drop index address_ext_uindex on address;
# drop index address_eng_ext_uindex on address;
# drop table address;

create table address
(
	id int auto_increment comment 'address id',
    type_id int not null comment 'type id',
	city_id int not null comment 'city id',
    zipcode varchar(100) not null comment 'zipcode/postal code',
    old_zipcode varchar(100) null comment 'old zipcode/postal code',
    address varchar(1024) not null comment 'address name',
    update_date timestamp default current_timestamp comment 'update date',
    constraint address_pk primary key (id),
    constraint address_type_id_fk foreign key (type_id) references type (id),
    constraint address_city_id_fk foreign key (city_id) references city (id)
);
