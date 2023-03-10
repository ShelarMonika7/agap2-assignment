create sequence hibernate_sequence;

create table recipe (
    id bigint not null primary key,
    name varchar(160),
    vegetarian varchar(160) ,
    number_of_serving integer,
    ingredients varchar(160),
    instructions varchar(160)

);
