# Automated tests for vk likes api  

**Run:**<br>
Requires Java 8 and maven 3.6.3<br><br>
mvn test - запуск тестов <br>
mvn allure:serve - генерация отчета

Поддерживается две группы тестов smoke и regression (mvn -Dgroups=smoke test)

**Для прогона тестов необходимо установить данные тестового аккаунта (access_token, user_id) в vk.properties**
