Note Application

Requirements:

| Tool           | Version |
|----------------|---------|
| Java           | v17     |
| Docker         | \> v20  |
| docker-compose | \> v2   |

Environment SetUp

All shell scrips need to be executed in the project path:

- Configure env vars first:

```bash
touch infra/docker/.env
echo "MONGO_DB_ROOT_USERNAME=admin
MONGO_DB_ROOT_PASSWORD=p@sswordNote
MONGO_DB_DATABASE=note
MONGO_DB_HOST=mongodb
MONGO_DB_PORT=27017
SERVER_PORT=8102
NOTE_APP_NAME=note
NOTE_APP_VERSION=0.0.1
" > infra/docker/.env
```

- Start the Note App with Dependencies (MongoDB)

```shell
./infra/docker/dockerization.sh start --build #to start
./infra/docker/dockerization.sh start --build -d #to start in the background
./infra/docker/dockerization.sh stop #to stop
./infra/docker/dockerization.sh clean #to destroy and clean all
```

- Sample HTTP requests:

```shell
# to save a note
curl --location 'http://localhost:8102/notes' \
--header 'Content-Type: application/json' \
--data '{
    "content": "Short content",
    "title": "Title",
    "tags": ["BUSINESS"]
}'

# to update a note by id
curl --location --request PUT 'http://localhost:8102/notes/{{id}}' \
--header 'Content-Type: application/json' \
--data '{
    "content": "Short content",
    "title": "Title",
    "tags": ["BUSINESS"]
}'

# to delete a note by id
curl --location --request DELETE 'http://localhost:8102/notes/{{id}}'

# to get a note by id
curl --location --request GET 'http://localhost:8102/notes/{{id}}'

# to list first 10 notes of BUSINESS tag (only include id,title,created_at)
curl --location --request GET 'http://localhost:8102/notes?minimal=true&tags=BUSINESS&size=10&page=0'

# to list first 5 notes of BUSINESS or IMPORTANT tag (full details)
curl --location --request GET 'http://localhost:8102/notes?minimal=false&tags=BUSINESS,IMPORTANT&size=5&page=0'
```

Run configuration to run the jar:
`MONGO_DB_DATABASE=note;MONGO_DB_HOST=localhost;MONGO_DB_PASSWORD=p@sswordNote;MONGO_DB_USERNAME=admin`
