Дипломный проект представляет собой автоматизацию веб-сервиса, который предлагает купить тур по определённой цене двумя способами: по карте и в кредит по номеру карты.

Инструкция по запуску авто-тестов
1. Установить необходимое ПО
- Git
- IntelliJ IDEA
- JDK 11
- Docker Desktop 
2. Запусить Docker Desktop
3. Открыть Docker Desktop
4. Запустить контейнеры с СУБД MySQL, PostgreSQL и симулятором банковских сервисов. В терминале выполнить команду: docker-compose up
5. Запустить SUT. Выполнить команду: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
Для запуска SUT с подключением с Postgresql: выполнить команду: java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
6. Запустить тесты: в IntelliJ IDEA дважды нажать Ctrl и выполнить команду:
Для MySQL: ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
Для PostgreSQL: ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
7. Создать отчёт Allure: выполнить команду: ./gradlew allureServe
8. Для остановки контейнера выполнить команду: docker-compose down