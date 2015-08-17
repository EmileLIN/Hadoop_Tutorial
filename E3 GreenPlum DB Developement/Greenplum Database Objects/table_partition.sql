--- CREATE PARTITION TABLE


--example 01 : build a partition table by date

CREATE TABLE tb_01(
	id int,
	mydate date,
	amount decimal(10,2)	
)
DISTRIBUTED BY(id)
PARTITION BY RANGE(mydate)
(
	START (date '2013-01-01') INCLUSIVE,
	END （date '2014-01-01'）EXCLUSIVE,
	EVERY (INTERVAL '1 day')
);


--example 02 : define partition names

CREATE TABLE tb_02(
	id int,
	mydate date,
	amount decimal(10,2)	
)
DISTRIBUTED BY(id)
PARTITION BY RANGE(mydate)
(
	PARTITION Jan13 START (date '2013-01-01') INCLUSIVE,
	PARTITION Feb13 START (date '2013-02-01') INCLUSIVE,
	PARTITION Mar13 START (date '2013-03-01') INCLUSIVE,
	PARTITION Apr13 START (date '2013-04-01') INCLUSIVE,
	PARTITION May13 START (date '2013-05-01') INCLUSIVE,
	PARTITION Jun13 START (date '2013-06-01') INCLUSIVE,
	PARTITION Jul13 START (date '2013-07-01') INCLUSIVE,
	PARTITION Aug13 START (date '2013-08-01') INCLUSIVE,
	PARTITION Sep13 START (date '2013-09-01') INCLUSIVE,
	PARTITION Oct13 START (date '2013-10-01') INCLUSIVE,
	PARTITION Nov13 START (date '2013-11-01') INCLUSIVE,
	PARTITION Dev13 START (date '2013-12-01') INCLUSIVE
	END (date '2014-01-01') EXCLUSIVE
);


--example 03: use number partition

CREATE TABLE tb_03(
	id int,
	rank int,
	year int,
	gender char(1),
	count int
)
DISTRIBUTED BY (id)
PARTITION BY RANGE (year)
(
	START (2010) 
	END (2014) 
	EVERY (1),
	DEFAULT PARTITION extra		--define an extra partition,which save the data not include in range 2010-2013
);


--example 04： use list partition key

CREATE TABLE tb_04(
	id int,
	rank int,
	year int,
	gender char(1),
	count int
)
DISTRIBUTED BY (id)
PARTITION BY LIST (gender)
(
	PARTITION girls VALUES('F'),
	PARTITION boys VALUES('M'),
	DEFAULT PARTITION others 
);



---example 05:  mutilple level partition

CREATE TABLE tb_05(
	trans_id int,
	trans_date date,
	amount decimal(9,2),
	region text
)
DISTRIBUTED BY (trans_id)
PARTITION BY RANGE(trans_date)
SUBPARTITION BY LIST(region)
SUBPARTITION TEMPLATE
			(
				SUBPARTITION usa  VALUES('usa'),
				SUBPARTITION europe VALUES('europe'),
				DEFAULT SUBPARTITION other_region
			)		
(
	START (date '2013-09-01') 
	END (date '2014-01-01')
	EVERY (INTERVAL '1 month'),
	DEFAULT PARTITION outlying_dates
);

















