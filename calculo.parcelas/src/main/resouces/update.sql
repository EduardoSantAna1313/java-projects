begin work;
lock table parcela in EXCLUSIVE mode;
	with tab as (
		select round(sum(valor)::decimal/(select count(*) from parcela where id <> ?):: decimal, 36)::text as valorTotal
		from parcela
	) update parcela set valor = (select valorTotal from tab limit 1)::decimal where id <> ?;
	delete from parcela	where id = ?;	
commit work;