# AskMe.com
This website allows users to write blogs or post comment on blogs. Only a user can view the comments.
It a spring-mvc application.

## About

This is a project that includes Spring+Hibernate. The idea was to build some basic blogging platform.

It was made using Spring Boot, Spring Security, Bootstrap, Spring Data JPA, Spring Data REST. Database used is Mysql.

There is a login and registration functionality included.

User has his own blog page, where he can add new blog posts. Every authenticated user can comment on posts made by other users. Non-authenticated users can see all blog posts, but cannot add new posts or comment. The blogs can be categorized in various tags.

The application is protected from cross-site scripting using HttpServletRequestWrapper.
