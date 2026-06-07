# testProj

Backend на Spring Boot 4 (Java 21) для умного жилого комплекса: выдаёт JWT-аутентификацию, хранит устройства, группы, шаблоны и статическую информацию в PostgreSQL, а также проксирует данные о парковках, кладовых и новостях из внешнего API Ujin. Документация API — Swagger UI на `/api/swagger-ui.html`.

## Запуск

Для запуска надо указать токены SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, SPRING_DATASOURCE_PASSWORD, UJIN_TOKEN, JWT_SECRET
Затем

```bash
docker network create app
docker-compose up -d
```

Приложение доступно на `http://localhost:8080`, базовый путь API — `/api`.
