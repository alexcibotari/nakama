# NAKAMA

[![Build Status](https://travis-ci.org/alexcibotari/nakama.svg?branch=develop)](https://travis-ci.org/alexcibotari/nakama)

## 0. Pre-requirements
+ Java 8
+ NodeJS
+ MySQL with schema 'nakama' for production environment

## 1. Checkout repository and Git config
### Clone repository
- run `git@github.com:alexcibotari/nakama.git`

### Checkout branch **master** and **develop**
- run `git checkout master`
- run `git checkout develop`

## 2. Setup
### 2.1 Install Node Packages (see package.json)
- Install Global packages `npm run install-global`
- Install Local packages `npm install`

### 2.2 Build FrontEnd
- run `grunt`

## 3. Run
### Spring Boot
#### Gradle
    gradlew bootRun
#### Java
Run Application.java
### Tomcat WAR
Add project into Tomcat

## 4. Application credentials
admin / admin

## 5. General
- Don't touch your team members components without talk to them
- Every task needs an feature branch
- Never merge your feature branches by yourself
- Every commit message need a task

## 6. Commit Message
- Every commit message need a task
- Every commit message needs a label
    - [tests]: new or changed test
    - [mock]: new or changed mock data
    - [new]: new method, class, constants, component ...
    - [change]: a change on existing tested code
    - [bugfix]: a fix on existing code
    - [refactoring]: a refactoring of existing code
    - [doc]: a change in documentation (readme ...)
    - [import]: swagger import or other imports
    - [config]: new or changed configuration files
- Example message
    `NAKAMA-9 [bugfix] fix issue on login page`

## 7. Naming
- Feature branch: `features/NAKAMA-12-WORKLOG`
- Merge request: feature branch name
