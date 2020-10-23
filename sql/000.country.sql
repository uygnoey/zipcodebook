# drop index country_name_uindex on country;
# drop index country_name_eng_uindex on country;
# drop index country_name_ext_uindex on country;
# drop table country;

create table country
(
	id int auto_increment comment 'country id',
    code varchar(10) not null comment 'country code',
	name varchar(255) not null comment 'native language country name',
	name_eng varchar(255) null comment 'english country name',
	name_ext varchar(255) null comment 'extra/other country name',
    constraint country_pk primary key (id)
);

create unique index country_name_uindex
    on country (name);

create unique index country_name_eng_uindex
	on country (name_eng);

create unique index country_name_ext_uindex
	on country (name_ext);

insert country (code, name, name_eng, name_ext) values
('US', 'UNITED STATES', 'UNITED STATES', null),
('KR', '대한민국', 'KOREA OF REPUBLIC', 'SOUTH KOREA'),
('JP', '日本', 'JAPAN', null);
