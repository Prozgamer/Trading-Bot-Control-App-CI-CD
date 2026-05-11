CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Вставляем админа
INSERT INTO users (id, name, email)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Admin', 'admin@example.com');

CREATE TABLE trading_accounts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    server VARCHAR(255) NOT NULL,
    htimeframe VARCHAR(50), -- Добавь эту строку (тип подбери под Java, обычно VARCHAR)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Вставляем аккаунт, привязанный к админу (user_id совпадает с id админа выше)
INSERT INTO trading_accounts (id, user_id, login, password, server)
VALUES (gen_random_uuid(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Admin-Acc', '123pass', 'metatrader86');