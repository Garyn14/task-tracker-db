-- create ENUM for TaskStatus
CREATE TYPE task_status AS ENUM ('to-do', 'in-progress', 'done');

-- create table tasks
CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    status task_status,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);