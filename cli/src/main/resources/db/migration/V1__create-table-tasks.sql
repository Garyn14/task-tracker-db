-- create ENUM for TaskStatus
CREATE TYPE task_status AS ENUM ('TO_DO', 'IN_PROGRESS', 'DONE');

-- create table tasks
CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    status task_status NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

