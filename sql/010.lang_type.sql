# drop index type_uindex on type;
# drop table type;

create table type
(
	id int auto_increment comment 'type id',
    type varchar(255) not null comment 'type name',
    update_date timestamp default current_timestamp comment 'update date',
    constraint type_pk primary key (id)
);

create unique index type_uindex
    on type (type);

insert type (type) values
('English'),
('Korean'),
('Kanji'),
('Zenkaku'),
('Zenkaku with Sutegana');
