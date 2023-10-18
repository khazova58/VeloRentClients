# ClientRentalBicycleOrganization

### Веб-сервис работы с клиентами организации по прокату велосипедов со следующей функциональностью:

* создание, удаление, редактирование и поиск клиентов,
* чтение записи по id,
* поиск записи по полю Название без учета регистра

Стек:
* Java 17
* Spring Boot
* Spring Data JPA
* Gradle
* PostgreSQL
* Git

### Запуск приложения и отладка.

Swagger UI: http://localhost:8081/swagger-ui/index.html

Скрипты liquibase

Сборка: ./mvnw clean install

Локальный запуск.
По умолчанию приложение локально запускается под default профилем, с развертыванием окружения с помощью docker compose.

Для корректной работы приложения нужно заполнить username/password подключения к субд в файле application.yml. Либо указав параметры запуска: -D DB_USER= -D DB_PASSWORD=.

В IDEA достаточно прописать эти параметры Settings -> Build, Execution, Deployment -> Build Tool -> Maven -> Runner -> Environment variables