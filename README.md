# middleware-commons

Common java modules for middleware.

Do not include Bean annotations in this project, if it is possible to annotate from the middleware project.
In `SecurityConfiguration` we extend a class with `@Bean` annotations, so it needs to be annotated with `@Configuration` in this project.

## Local setup

It is required to install the commit-msg hooks that enforce the [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/) format before committing changes to the repository.

Run

```bash
npm install --global git-conventional-commits
git config --local core.hooksPath .githooks/
```

## Development

While developing, you may need to test or use the new changes for other middlewares.

Since Gradle can understand Maven repos, we can utilize the maven local repository.

Run the bellow command (you can change the artifact version to test as well if you want) and refresh Gradle in another middleware

```bash
./gradlew publishToMavenLocal
```
