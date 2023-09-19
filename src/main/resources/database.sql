CREATE TABLE IF NOT EXISTS category (
    id int primary key AUTO_INCREMENT,
    name varchar(255) unique NOT NULL
);

CREATE TABLE IF NOT EXISTS singer (
    id int primary key auto_increment,
    name varchar(255) NOT NULL,
    category_id int references category(id)
);

CREATE TABLE song (
    id int primary key auto_increment,
    name varchar(255) NOT NULL,
    link varchar(255),
    singer_id int references singer(id)
);

