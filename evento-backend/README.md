# How to start
A separate docker-compose file is located in the root of the backend project. This is for testing purposes and purposes of local development, completely separate from the frontend.
To start the project, run the following command:
```bash
docker-compose up --build
```
This will start up the backend with all its dependencies and a Postgresql database. The backend will be available at `http://localhost:8080`.
## Local development
You can also run the backend without running the command. For this, you need to have Intellij IDEA installed. Open the project in Intellij IDEA and run the `EventFinderApplication` class. The backend will be available at `http://localhost:8080`.
For ease of debugging, this approach is recommended as it allows you to set breakpoints and inspect the code.