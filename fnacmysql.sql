create database fnac;

create table funcionario(

	id int primary key NOT NULL AUTO_INCREMENT,
    login varchar(45),
    senha varchar(45),
    nome varchar(45),
    cpf varchar(45),
    telefone int
    
);

create table produto(

	id int primary key not nULL AUTO_INCREMENT,
    nome varchar(45),
    marca varchar(45),
    estoque int,
    preco double
    
);

create table fornecedor(

	id int primary key not null auto_increment,
    empresa varchar(45),
    cnpj varchar(45),
    telefone varchar(45),
    responsavel varchar(45)

);