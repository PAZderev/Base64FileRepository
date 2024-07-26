# File Repository Service

## Описание решения

Этот проект представляет собой микросервис для хранения файлов в формате base64 и их атрибутов.
Микросервис предоставляет HTTP API для CRUD операций файлов с использованием формата JSON.
Приложение написано на Java с использованием Spring Boot и PostgreSQL в качестве базы данных.
Автор проекта не успел ознакомиться с ORM-фреймворками, поэтому
взаимодействие с базой данных происходит с помощью чистого JdbcTemplate.
Контейнеризация выполнена с использованием Docker и Docker Compose.

## Инструкция по запуску приложения

### Шаг 1: Клонирование репозитория

```bash
git clone https://github.com/yourusername/FileRepository.git
cd FileRepository
```
### Шаг 2: Сборка проекта
Убедитесь, что у вас установлен Maven и Docker.

``` bash
mvn clean package
```
### Шаг 3: Запуск контейнеров
``` bash
docker-compose up -d
```

### Шаг 4: Проверка статуса контейнеров
``` bash
docker-compose ps
```
### Примеры тестовых запросов для проверки API-методов
Для тестирования API-методов можно использовать Postman или аналогичные инструменты.

#### Создание файла
```http
POST /files
Content-Type: application/json

{
  "base64Data": "base64_encoded_file_content",
  "title": "example_file",
  "creation_date": "2024-07-23T16:37:23Z",
  "description": "Example description"
}
```
Пример команды curl
```bash
curl -X POST http://localhost:8080/files \
-H "Content-Type: application/json" \
-d '{
  "base64Data": "base64_encoded_file_content",
  "title": "example_file",
  "creation_date": "2024-07-23T16:37:23Z",
  "description": "Example description"
}'
```

В ответ вернется id добавленого файла. При пустой базе данных первый ответ будет - 1.

#### Получение файла
```http
GET /files/{id}
```

```bash
curl -X GET http://localhost:8080/files/1
```
Ответ:
```json
{
"base64Data": "base64_encoded_file_content",
"title": "example_file",
"creation_date": "2024-07-23T16:37:23Z",
"description": "Example description"
}
```

#### Получение списка файлов с пагинацией
```http
GET /files?page=0&size=10&sort=desc
```
curl 
```bash
curl -X GET "http://localhost:8080/files?page=0&size=10&sort=desc"
```

Ответ
```json
[
  {
    "base64Data": "base64_encoded_file_content",
    "title": "example_file",
    "creation_date": "2024-07-23T16:37:23Z",
    "description": "Example description"
  },
  ...
]

```

#### Удаление файла по ID
```http
DELETE /files/{id}
```
curl
```bash
curl -X DELETE http://localhost:8080/files/1
```
В ответе вернется true, если запись удалена.

#### Обновление файла

```http request
PUT /files
Content-Type: application/json

{
  "id": 1,
  "base64Data": "updated_base64_encoded_file_content",
  "title": "updated_example_file",
  "creation_date": "2024-07-23T16:37:23Z",
  "description": "Updated example description"
}

```
curl
```bash
curl -X PUT http://localhost:8080/files \
-H "Content-Type: application/json" \
-d '{
  "id": 1,
  "file": "updated_base64_encoded_file_content",
  "title": "updated_example_file",
  "creation_date": "2024-07-23T16:37:23Z",
  "description": "Updated example description"
}'
```
В ответе вернется число 1, если запись успешно обновлена.

####  При возникновении вопросов или проблем, пожалуйста, создайте issue в репозитории или свяжитесь с разработчиком напрямую.