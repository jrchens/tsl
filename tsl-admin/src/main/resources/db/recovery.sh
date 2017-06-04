#!/bin/sh

## ./recovery tsl tsl-schema.sql tsl-data.sql

/usr/local/opt/mysql\@5.5/bin/mysql -uroot -proot < $2
/usr/local/opt/mysql\@5.5/bin/mysql -uroot -proot $1 < $3


