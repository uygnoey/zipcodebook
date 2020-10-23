# drop index country_uindex on country;
# drop index country_eng_uindex on country;
# drop index country_ext_uindex on country;
# drop index country_eng_ext_uindex on country;
# drop table country;

create table country
(
	id int auto_increment comment 'country id',
    code varchar(10) not null comment 'country code',
    country varchar(255) not null comment 'native language country name',
    country_ext varchar(255) null comment 'extra/other country name',
    country_eng varchar(255) null comment 'English country name',
    country_eng_ext varchar(255) null comment 'English extra/other country name',
    update_date timestamp default current_timestamp comment 'update date',
    constraint country_pk primary key (id)
);

create unique index country_uindex
    on country (country);

create unique index country_eng_uindex
	on country (country_eng);

create unique index country_ext_uindex
	on country (country_ext);

create unique index country_eng_ext_uindex
	on country (country_eng_ext);

insert country (code, country, country_ext, country_eng, country_eng_ext) values
('US', 'UNITED STATES', null, 'UNITED STATES', null),
('KR', '대한민국', '한국', 'KOREA OF REPUBLIC', 'SOUTH KOREA'),
('JP', '日本', null, 'JAPAN', null);
