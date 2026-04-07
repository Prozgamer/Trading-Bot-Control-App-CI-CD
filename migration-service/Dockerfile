# Берем образ напрямую от создателей Flyway
FROM flyway/flyway:10.20

# Копируем скрипты
COPY ./sql /flyway/sql

# Запускаем миграцию при старте контейнера
ENTRYPOINT ["flyway", "migrate"]