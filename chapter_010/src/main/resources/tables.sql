create table if not exists brand(id serial primary key, name varchar(30));
create table if not exists users(id serial primary key, name varchar(50), password VARCHAR(100));
create table if not exists body (id serial primary key, name varchar(50));
create table if not exists engine (id serial primary key, name varchar(20));
create table if not exists gearbox(id serial primary key, name varchar(20));
create table if not exists axle (id serial primary key, name varchar(20));
create table if not exists car (id serial primary key, name varchar(50), description varchar(255)
            , created_date INTEGER, user_id integer references users(id)
            , axle_id integer references axle(id), brand_id integer references brand(id)
            , body_id integer references body(id), engine_id integer references engine(id)
            , gearbox_id integer references gearbox(id)
            , status integer, post_date timestamp, price INTEGER, pic VARCHAR(255));
create table if not EXISTS pics(id SERIAL PRIMARY KEY, car_id integer REFERENCES car(id), pic bytea);