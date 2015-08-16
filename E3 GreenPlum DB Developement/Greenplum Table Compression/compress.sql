--create a table with compression techs 

--exeample 01

CREATE TABLE tb_t1(
	c1 int encoding(compresstype=zlib) , --use zlib algos to compress this column
	c2 text encoding(compresstype=quicklz,blocksize=65536), -- use quicklz algo, give block size
	c3 text
)
with(appendonly=true,orientation=column);
--To us compress tech, the table should be appendonly,and column storage is more effective


--example 02

CREATE TABLE tb_t2(
	c1 int encoding(compresstype=zlib) , --use zlib algos to compress this column
	c2 text encoding(compresstype=quicklz,blocksize=65536), -- use quicklz algo, give block size
	c3 text encoding(compresstype=rle_type)
)
with(appendonly=true,orientation=column);