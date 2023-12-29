Run configuration local:
`MONGO_DB_DATABASE=note;MONGO_DB_ROOT_PASSWORD=p@sswordNote;MONGO_DB_ROOT_USERNAME=admin;MONGO_DB_HOST=localhost`


```shell
touch infra/docker/.env
echo "MONGO_DB_ROOT_USERNAME=admin
MONGO_DB_ROOT_PASSWORD=p@sswordNote
MONGO_DB_DATABASE=note
" > infra/docker/.env
```