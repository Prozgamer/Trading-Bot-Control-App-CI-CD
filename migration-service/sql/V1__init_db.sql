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
    mt_login VARCHAR(255) NOT NULL,
    mt_password VARCHAR(255) NOT NULL,
    server VARCHAR(255) NOT NULL,
    htimeframe VARCHAR(50) DEFAULT 'PERIOD_H4',
    ltimeframe VARCHAR(50) DEFAULT 'PERIOD_M15',
    is_running BOOLEAN DEFAULT FALSE,

    -- Новые поля настроек из твоего MQ5 файла
    lookback_candles INT DEFAULT 20,
    max_dd_prc NUMERIC(4, 2) DEFAULT 2.50,
    min_fvg_size INT DEFAULT 50,
    candles_to_live INT DEFAULT 9,

    structure_lookback INT DEFAULT 12,
    confirmation_wait_bars INT DEFAULT 12,
    min_stop_distance_points INT DEFAULT 100,
    max_stop_distance_points INT DEFAULT 299,

    -- Время сессий
    morn_start_time TIME DEFAULT '10:00:00',
    morn_end_time TIME DEFAULT '14:00:00',
    eve_start_time TIME DEFAULT '15:40:00',
    eve_end_time TIME DEFAULT '18:00:00',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Вставляем аккаунт, привязанный к админу (используем новые имена колонок mt_login, mt_password)
INSERT INTO trading_accounts (id, user_id, mt_login, mt_password, server)
VALUES (gen_random_uuid(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Admin-Acc', '123pass', 'metatrader86');