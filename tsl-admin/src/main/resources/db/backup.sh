#!/bin/sh

## ./backup.sh tsl

/usr/local/opt/mysql\@5.5/bin/mysqldump -uroot -proot --no-data --events --routines --triggers --databases $1 > $1-schema.sql
/usr/local/opt/mysql\@5.5/bin/mysqldump -uroot -proot --no-create-info --skip-events --skip-routines --skip-triggers --databases $1 > $1-data.sql

