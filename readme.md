Run configuration local:
`MONGO_DB_DATABASE=note;MONGO_DB_HOST=localhost;MONGO_DB_PASSWORD=p@sswordNote;MONGO_DB_USERNAME=admin`


```shell
touch infra/docker/.env
echo "MONGO_DB_ROOT_USERNAME=admin
MONGO_DB_ROOT_PASSWORD=p@sswordNote
MONGO_DB_DATABASE=note
MONGO_DB_HOST=mongodb
MONGO_DB_PORT=27017
SERVER_PORT=8102
" > infra/docker/.env
```