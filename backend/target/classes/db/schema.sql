CREATE DATABASE IF NOT EXISTS life_simulator;
USE life_simulator;

-- USERS
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- SIMULATIONS
CREATE TABLE simulations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    seed BIGINT NOT NULL,
    years INT NOT NULL,
    risk_tolerance DOUBLE NOT NULL,
    income DOUBLE NOT NULL,
    expenses DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    INDEX idx_sim_user (user_id)
);

-- DECISIONS
CREATE TABLE decisions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    simulation_id BIGINT NOT NULL,
    career VARCHAR(100),
    savings_rate DOUBLE,
    investment_strategy VARCHAR(100),
    lifestyle_cost DOUBLE,
    FOREIGN KEY (simulation_id) REFERENCES simulations(id),
    INDEX idx_dec_sim (simulation_id)
);

-- YEARLY OUTCOMES
CREATE TABLE yearly_outcomes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    simulation_id BIGINT NOT NULL,
    year_number INT NOT NULL,
    net_worth DOUBLE,
    income DOUBLE,
    expenses DOUBLE,
    FOREIGN KEY (simulation_id) REFERENCES simulations(id),
    INDEX idx_out_sim (simulation_id)
);

-- PROBABILITY SNAPSHOTS
CREATE TABLE probability_snapshots (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    simulation_id BIGINT NOT NULL,
    percentile_10 DOUBLE,
    percentile_50 DOUBLE,
    percentile_90 DOUBLE,
    FOREIGN KEY (simulation_id) REFERENCES simulations(id)
);

-- SIMULATION HISTORY
CREATE TABLE simulation_history (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    simulation_id BIGINT NOT NULL,
    run_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (simulation_id) REFERENCES simulations(id),
    INDEX idx_hist_user (user_id)
);

-- SEED USER
INSERT INTO users (email, password_hash)
VALUES ('demo@life.ai', '$2a$10$Dow1YH0u6g9n0kM9Yf6E0e3x0y9P4K2xF5Nnq7Zc8M1JkRZ9G');
