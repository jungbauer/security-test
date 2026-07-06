# Testing security avenues
Repo to explore different security features and how they interact.

# Roadmap
1. ~~Freely open pages~~
2. ~~Freely open rest endpoints~~
3. Pages governed by User login and different roles
4. Endpoints governed by User login and different roles
5. Pages and endpoints governed by Custom UserEmail security filter
6. Pages and endpoints governed by basic JWT security
7. Endpoint governed by JWT that isn't tracked by the backend

# Defined users
usr: `test@test.com` pwd: `test` 

# Notes
The H2 database falls over if you have a User entity. Not ideal. Have called my user TestUser as a workaround in this project.

# Todo topics
Security roles with defined privileges and a defined role hierarchy. [Starting point article.](https://www.baeldung.com/role-and-privilege-for-spring-security-registration)

# Inspiration
Articles that inspired and informed this exploration.

[Project structure](https://medium.com/@anandjeyaseelan10/spring-boot-project-structure-explained-best-practices-c2ba46ea57eb) - This seems to be the article Gemini pilfered some of its summary from. I'm going to use a Package-by-Layer approach since this shouldn't be super big.

[Custom error page](https://www.baeldung.com/spring-boot-custom-error-page) - Don't want to dump the error log to the user.

[H2 db console](https://www.baeldung.com/spring-boot-h2-console-error) - Useful for H2. Whole security filter not necessary, probably because of dependency spring-boot-h2console.

[Spring role based access control, RBAC](https://medium.com/@chanuthmaduka1986/secure-spring-boot-application-with-easy-role-based-access-control-8c2355933902) - Useful tut for implementing Roles.