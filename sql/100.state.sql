# drop index state_uindex on state;
# drop index state_eng_uindex on state;
# drop index state_ext_uindex on state;
# drop index state_eng_ext_uindex on state;
# drop table state;

create table state
(
	id int auto_increment comment 'state/province/region id',
	country_id int not null comment 'country id',
    state varchar(255) not null comment 'native language state/province/region name',
    state_ext varchar(255) null comment 'extra/other state/province/region name',
    state_eng varchar(255) null comment 'English state/province/region name',
    state_eng_ext varchar(255) null comment 'English extra/other state/province/region name',
    update_date timestamp default current_timestamp comment 'update date',
    constraint state_pk primary key (id),
    constraint state_country_id_fk foreign key (country_id) references country (id)
);

create unique index state_uindex
    on state (state);

create unique index state_eng_uindex
	on state (state_eng);

create unique index state_ext_uindex
	on state (state_ext);

create unique index state_eng_ext_uindex
	on state (state_eng_ext);
