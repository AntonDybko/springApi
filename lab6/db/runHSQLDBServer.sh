#!/bin/sh

java -cp ./db/hsqldb-2.7.0.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb
