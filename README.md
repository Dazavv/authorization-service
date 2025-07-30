# Authorization Service

## Описание

Сервис авторизации пользователей с поддержкой регистрации и аутентификации на основе JWT. Позволяет управлять пользователями с тремя ролями: `admin`, `premium_user` и `guest`.

---

## Основные возможности

* Регистрация новых пользователей с уникальным логином и email.
* Хранение паролей в зашифрованном виде (bcrypt).
* Авторизация с использованием JWT.
* Роли пользователей: `admin`, `premium_user`, `guest`.
* Ограниченное время жизни JWT.
* Обновление JWT (refresh token) без повторного ввода логина и пароля.
* Отзыв refresh токенов (logout).

---

## Технологии

* Java 17+
* Spring Boot 3+
* Spring Security
* Spring Data JPA (Hibernate)
* PostgreSQL
* JWT (jjwt, spring-security)
* Lombok

---

## JWT

JWT используется для аутентификации пользователей и передачи их роли в запросах к API:

* **Access Token** — короткоживущий (например, 15 минут), используется для доступа к защищённым ресурсам.
* **Refresh Token** — длительный по времени (например, 7 дней), используется для получения нового access токена без повторной аутентификации.

### Особенности реализации

* При входе (login) пользователю выдаются два токена: access и refresh.
* Access токен включается в заголовок Authorization как `Bearer <token>`.
* Refresh токен хранится на стороне клиента и при необходимости используется для получения нового access токена.
* При logout refresh токен удаляется из хранилища на сервере, что делает невозможным его дальнейшее использование.
* Время жизни токенов задаётся в настройках (например, `jwt.accessTokenExpiration`, `jwt.refreshTokenExpiration`).

---

## Модель пользователя

| Поле      | Описание                                  |
|-----------|-------------------------------------------|
| id        | Уникальный идентификатор                  |
| login     | Логин пользователя (уникальный)           |
| password  | Хеш пароля (bcrypt)                       |
| firstName | Имя                                       |
| LastName  | Фамилия                                   |
| email     | Электронная почта (уникальная)            |
| roles     | Набор ролей (admin, guest, premium\_user) |

---

## Использование

### Регистрация пользователя

`POST /api/auth/register`

Тело запроса:

```json
{
  "login": "user123",
  "password": "password",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe"
}
```

---

### Вход (логин)

`POST /api/auth/login`

Тело запроса:

```json
{
  "login": "user123",
  "password": "password"
}
```

Ответ содержит:

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### Обновление токена

`POST /api/auth/refresh`

Тело запроса:

```json
{
  "refreshToken": "..."
}
```

Ответ:

```json
{
  "accessToken": "...",
  "refreshToken": "..."
}
```

---

### Выход (отзыв токена)

`POST /api/auth/logout`

Тело запроса:

```json
{
  "refreshToken": "..."
}
```

---

### Изменение ролей (только ADMIN)

`POST /api/auth/change-role`

Тело запроса:

```json
{
  "login": "user123",
  "newRole": "PREMIUM_USER"
}
```
