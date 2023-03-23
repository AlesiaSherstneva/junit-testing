# fourth module  
В четвёртом модуле (занятия № 120-136) рассмотрены следующие темы:
- тестирование Rest Controllers;
- @PersistenceContext EntityManager;
- JsonPath (в spring-boot-starter-test уже содержится, для других конфигураций нужно подключать dependency);
- Matchers of JsonPath (hasSize(), is()).

В основе занятий лежал функционал, схожий с приложением в третьем модуле, но использующий REST вместо MVC.

Тесты для сервиса StudentAndGradeService были скопированы с предыдущего проекта, тесты для контроллера 
GradebookController написаны в ходе уроков (на этот раз классическим способом, без использования TDD).