<p align="center">
  <img src="./frontend/src/assets/images/f1logo.png" alt="drawing" width="500"/>
</p>

## Introduction
This is a web application showing information for the current [Formula 1 Season](https://www.formula1.com/).

Information includes:
- Race calendar with date and time
- Indicator of next upcomming session
- Driver standings

## Getting Started
### Frontend
To run the frontend locally, follow these steps:
1. Navigate to the project directory.
```console
cd frontend
```
2. Install dependencies.
```console
npm install
```
3. Set enviroment variables. An example can be found in `.env.example`, which can be cloned by:
```console
cp .env.example .env
```
4. Start the application.
```console
npm start
```
### Backend
To run the backend locally, follow these steps:
1. Navigate to the project directory.
```console
cd backend
```
2. Set enviroment variables. An example can be found in `.env.example`, which can be cloned by:
```console
cp .env.example .env
```
3. Start the application (or use run configs for IntelliJ)
```console
./gradlew bootRun
```

## Code style
### Frontend
The frontend application uses [Prettier](https://prettier.io/) for code formatting. Make sure this is installed in your preffered editor.

### Backend
The backend application uses [Spotless](https://github.com/diffplug/spotless) for code formatting.

To check formatting with Spotless on the whole codebase use `./gradlew spotlessCheck`.

Most changes can be applied automatically with `./gradlew spotlessApply`.

## Technologies
### Frontend
- JavaScript
- React
- Node.js
### Backend
- Java
- Spring
- Gradle
