# Task Tracker

Task tracker is a project used to track and manage your tasks. In this task, you will build a simple command line interface (CLI) to track what you need to do, what you have done, and what you are currently working on. This project will help you practice your programming skills, including working with the filesystem, handling user inputs, and building a simple CLI application.

## Requirements

The application should now use PostgreSQL as the database to store tasks, Flyway for managing database migrations, and Spring Boot for input validation. The user should still be able to:

- Add, Update, and Delete tasks
- Mark a task as to do, in progress, or done
- List all tasks
- List all tasks that are done
- List all tasks that are to do
- List all tasks that are in progress

### Database

- The application should use PostgreSQL to store the tasks.
- Flyway should be used for database migrations to handle schema changes.
- Spring Boot should manage the application configuration and validate user inputs before adding or updating tasks in the database.

### Task Properties

Each task will have the following properties:

- **id**: A unique identifier for the task
- **description**: A short description of the task
- **status**: The status of the task (`to do`, `in-progress`, `done`)
- **createdAt**: The date and time when the task was created
- **updatedAt**: The date and time when the task was last updated

## Instructions

To set up the application with PostgreSQL, Flyway, and Spring Boot:

1. Ensure PostgreSQL is installed and running.
2. Configure the database connection in the `application.properties` file.
3. Use Flyway to manage database migrations. Run the command:
    ```bash
    ./mvnw flyway:migrate
    ```
4. Build and run the Spring Boot application to manage tasks through the CLI.

### Example Commands

The list of commands and their usage remains the same:

```bash
# Adding a new task
task-cli add "Buy groceries"
# Output: Task added successfully (ID: 1)

# Update existing task
task-cli update 1 "Buy groceries and cook dinner"
# Output: Task description updated successfully

# Delete task
task-cli delete 1
# Output: Task deleted successfully

# Marking a task as in progress or done
task-cli to-do 1
task-cli mark-in-progress 1
task-cli mark-done 1

# Listing all tasks
task-cli list

# Listing tasks by status
task-cli list to-do
task-cli list done
task-cli list in-progress
