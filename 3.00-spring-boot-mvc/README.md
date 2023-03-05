# third module
В третьем модуле (занятия № 65-119) рассмотрены следующие темы:
- @TestPropertySource
- H2 in-memory-database;
- @Autowired JDBCTemplate (не внедрился на spring-boot-starter-parent версии 2.7.6, пришлось,
как у преподавателя, поставить 2.6.2);
- @Sql (можно совмещать с @BeforeEach);
- @AutoConfigureMockMvc;
- MockHttpServletRequest, MockMvc, MockMvcRequestBuilders, MvcResult, ModelAndViewAssert;
- загрузка SQL-скриптов из файла application.properties;
- написание интеграционных тестов from-end-to-end.

В основу занятий был положен небольшой веб-сервис, имитирующий работу школьного журнала.  

Основной функционал:
- список всех учащихся;
- добавить, удалить учащегося;
- посмотреть, добавить, удалить оценки учащегося;
- автоматическое вычисление среднего балла по каждой дисциплине.

![img.gif](img.gif)

Часть функционала была подготовлена преподавателем заранее, часть написана во время уроков вместе с тестами 
через использование Test Driven Development.