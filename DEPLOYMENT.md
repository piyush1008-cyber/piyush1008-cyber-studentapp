# Deployment Guide

## Recommended option

Use Render for the quickest GitHub-connected deployment flow for this project.

## Before deploying

- Push the latest code to GitHub.
- Keep the default embedded H2 setup if you only need a project demo.
- If you want MySQL in the cloud, add the required database environment variables later.

## Render setup

1. Sign in to Render.
2. Connect your GitHub account.
3. Create a new Web Service from this repository.
4. Render can read the included `render.yaml`, or you can configure the service manually.
5. Wait for the build and deploy to finish.
6. Open the generated public URL.

## Build and start commands

If you configure the service manually, use:

```text
Build Command: ./mvnw clean package -DskipTests
Start Command: java -jar target/studentapp-0.0.1-SNAPSHOT.jar
```

## Environment variables

For demo deployment:

- `PORT=10000`
- `JWT_SECRET=<any long random secret>`

Optional MySQL deployment variables:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `DB_DRIVER=com.mysql.cj.jdbc.Driver`
- `JPA_DIALECT=org.hibernate.dialect.MySQLDialect`

## Presentation tip

If you only need to show the project publicly, deploy first with the default H2 setup. It is simpler and faster than adding a production database on day one.
