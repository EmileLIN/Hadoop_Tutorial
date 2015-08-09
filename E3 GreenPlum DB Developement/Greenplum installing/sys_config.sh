#! /bin/bash

#This is a script to config system variable before we use greenplum, it should be executed
#before we lance greenplum  

#import Enviroment variables for greenplum
source /usr/local/greenplum-db/greenplum_path.sh

#Change IO scheduler , by default is cfq, we need "deadline" mode for greenplum
echo deadline > /sys/block/sda/queue/scheduler
echo deadline > /sys/block/sr0/queue/scheduler
echo deadline > /sys/block/sr1/queue/scheduler

#Change block size 
/sbin/blockdev --setra 16384 /dev/sda
/sbin/blockdev --setra 16384 /dev/sda1
/sbin/blockdev --setra 16384 /dev/sda2

#Check if the changment are made
echo "sda queue scheduler "
cat /sys/block/sda/queue/scheduler
echo "sr0 queue scheduler "
cat /sys/block/sr0/queue/scheduler
echo "sr1 queue scheduler "
cat /sys/block/sr1/queue/scheduler
echo "sda block size"
/sbin/blockdev --getra /dev/sda
echo "sda1 block size"
/sbin/blockdev --getra /dev/sda1
echo "sda2 block size"
/sbin/blockdev --getra /dev/sda2

