create table cliente(
	id serial,
	nome varchar(50),
	email varchar(50),
	primary key(id)
);
create table produto(
	id serial,
	descricao varchar(50),
	valor double precision,
	primary key(id)
)