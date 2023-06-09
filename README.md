# java-testing
# Задания для практикума по автоматизации тестирования (SDET) от SimbirSoft.


*Исхаков Тимур*

## Описание

В репозитории находятся два проекта, написанных на Java:  

- SimbirSoftAPItestingProject - проект с API-автотестами

- SimbirSoftUItestingProject - проект с UI-автотестами 

Каждый проект содержит файл .docx c тест-кейсами, по которым были написаны автотесты.
При написании автотестов были использованы:

- Selenium Webdriver (браузер Chrome) - для UI-тестов
- тестовый фреймворк JUnit 5
- фреймворк для сборки проектов Maven
- Allure FrameWork для формирование отчетов о пройденных тестах

Для создания API-тестов использовались библиотеки:

- Rest Assured
- Jackson

В каждом проекте реализована возможность параллельного запуска тестов.
Скриншоты запуска автотестов в Jenkins находятся в папке JenkinsAllureReports.
