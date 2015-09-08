DROP EXTERNAL TABLE tb_ext_gf01;


CREATE EXTERNAL TABLE tb_ext_gf01
(
	id int,
	name text
)
LOCATION ('gpfdist://mdw:8081/*')
FORMAT 'TEXT' (DELIMITER ',' NULL '');


SELECT * FROM tb_ext_gf01；