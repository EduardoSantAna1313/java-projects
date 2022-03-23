create table parcela(
	id serial not null,
	pedidoId numeric(15,2) not null,
	valor numeric (15,2) not null,
	parcela numeric (15,2) not null
);
alter table parcela add primary key (id);