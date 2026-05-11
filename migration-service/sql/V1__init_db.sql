CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Таблица пользователей
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,       -- Соответствует @Column(name = "name")
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255),            -- Добавил, так как в Java модели User есть пароль
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Вставляем админа
INSERT INTO users (id, name, email, password)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Admin', 'admin@example.com', 'admin_pass');

-- Таблица торговых аккаунтов
CREATE TABLE trading_accounts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    mt_login VARCHAR(255) NOT NULL,    -- Исправлено для соответствия Java mtLogin
    mt_password VARCHAR(255) NOT NULL, -- Исправлено для соответствия Java mtPassword
    server VARCHAR(255) NOT NULL,
    htimeframe VARCHAR(50),
    ltimeframe VARCHAR(50),
    is_running BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Вставляем аккаунт, привязанный к админу (используем новые имена колонок mt_login, mt_password)
INSERT INTO trading_accounts (id, user_id, mt_login, mt_password, server)
VALUES (gen_random_uuid(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Admin-Acc', '123pass', 'metatrader86');