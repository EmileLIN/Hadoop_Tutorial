--create a table with compression techs 

--example 01

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


--example 03
-- This example shows the table's compression mode will overwrite the columns' modes

CREATE TABLE tb_t3(
	c1 int encoding(compresstype=zlib) , 
	c2 text encoding(compresstype=quicklz,blocksize=65536), 
	c3 text encoding(compresstype=rle_type)
)
with(appendonly=true,orientation=column)
partition by range(c3)(start ('2010-01-01'::date),
					    end('2013-12-31'::date),
						column c3 encoding (compresstype=zlib));


--example 04
#This example shows use default compression type 

CREATE TABLE tb_t4(
	c1 int encoding(compresstype=zlib) , 
	c2 text, 
	c3 text encoding(compresstype=rle_type),
	c4 smallint encoding(compresstype = none),  	-- because we define the default compression mode
	DEFAULT COLUMN ENCODING (compresstype=quicklz,blocksize=65536)
)
with(appendonly=true,orientation=column)
partition by range(c3)(start ('2010-01-01'::date),
					    end('2013-12-31'::date),
						column c3 encoding (compresstype=zlib));
;
#The results is c1 : zlib, c2: quicklz, c3: zlib, c4: none


 

