create database grupotoday;
use grupotoday;

-- Tabla Marca
CREATE TABLE tb_marca (
	id INT auto_increment primary key,
    nombre varchar(255) NOT null
);

CREATE TABLE tb_categoria (
	id INT auto_increment primary key,
    nombre varchar(255) NOT NULL
);

CREATE TABLE tb_admin (
	id INT auto_increment primary key,
    nombre varchar(255) not null,
    email varchar(255) not null,
    password varchar(3000) not null
);

CREATE TABLE tb_Zapatilla (
	id INT auto_increment primary key,
    nombre varchar(255) not null,
    descripcion varchar(500),
    pre_zapa decimal(10, 2) not null,
    stock int not null,
    img_zapa varchar(500) not null,
    marca_id int,
    categoria_id int,
    admin_id int,
    foreign key (marca_id) references tb_marca(id),
    foreign key (categoria_id) references tb_categoria(id),
    foreign key (admin_id) references tb_admin(id)
);

CREATE TABLE tb_modelo (
	id int auto_increment primary key,
    nombe varchar(255) not null,
    zapatilla_id int,
    foreign key (zapatilla_id) references tb_zapatilla(id)  
);

CREATE TABLE tb_departamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
    
);

CREATE TABLE tb_cliente (
	id int auto_increment primary key,
    nombre varchar(255) not null,
    email varchar(255) not null,
    tel_cli varchar(20),
    depa_id int,
    foreign key (depa_id) references tb_departamento(id)
);

CREATE TABLE tb_boleta (
	id int auto_increment primary key,
    cliente_id int,
    fech_bol date not null,
    foreign key (cliente_id) references tb_cliente(id)
    
);

create table tb_deta_boleta (
    id int auto_increment primary key,
    boleta_id int not null,
    zapatilla_id int not null,
    cantidad int not null,
    pre_total decimal (10, 2) not null,
    foreign key (boleta_id) references tb_boleta(id),
    foreign key (zapatilla_id) references tb_zapatilla(id),
     UNIQUE(boleta_id, zapatilla_id)
    
);



