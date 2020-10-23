# drop index address_uindex on address;
# drop index address_eng_uindex on address;
# drop index address_ext_uindex on address;
# drop index address_eng_ext_uindex on address;
# drop table address;

create table address
(
	id int auto_increment comment 'address id',
	city_id int not null comment 'city id',
    zipcode varchar(100) not null comment 'zipcode/postal code',
    old_zipcode varchar(100) null comment 'old zipcode/postal code',
    address varchar(1024) not null comment 'native language address name',
    address_ext varchar(1024) null comment 'extra/other address name',
    address_eng varchar(1024) null comment 'English address name',
    address_eng_ext varchar(1024) null comment 'English extra/other address name',
    update_date timestamp default current_timestamp comment 'update date',
    constraint address_pk primary key (id),
    constraint address_city_id_fk foreign key (city_id) references city (id)
);
