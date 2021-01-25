#### Основы
- Bean - клласс, жизненым циклом которого управляет Spring.
###### В Spring есть два IoC контейнера -  _BeanFactory_ и _ApplicationContext_.
- BeanFactory - самая простая версия контейнера. Наиболее часто используемая имплементация _XmlBeanFactory_(получает инфу из _XML_ конфигурации).
    BeanFactory обычно используется в приложениях, где ресурсы ограничены, мобльных устройствах. _BeanFactory_ подгружает бины по требованию, в отличии от _ApplicationContext_.
    по сути устарел в пользу ApplicationContext.
- ApplicationContext - продвинутая версия контейнера, расширяеет BeanFactory. Загружает бины сразу при старте приложения. Используется в большинстве прилаг. 
    - поддерживает _message resolution_ и интернационализацию с помощью интерфейса _MessageSource_(например в спринге есть готовая реализация  _ResourceBundleMessageSource_).
    - Поддерживает автоматическую регистрацию _BeanPostProcessor_ and _BeanFactoryPostProcessor_;
    - Поддерживает обработку событий встроеных(_ContextStartedEvent_, _ContextStoppedEvent_, _ContextClosedEvent_, and _RequestHandledEvent_) и кастомных.
- По умолчанию, все бины в спринге _Singleton_

#### Настройка бинов в контейнере
##### Java-Based configuration
Бины создаются в конфигурационных классах, в специальных методах;
- **@Configuration** указывает на класс конфигурации;
- **@Bean** указывает на метод в классе конфигурации, который возвращает новый Spring Bean;
##### Annotation-Based configuration 
Всё ещё есть XML, в котором выставляется флаг `<context:annotation-config/>` и затем бины настраиваются аннотациями `@Component`, `@Controller`, `@Service`,
 `@Repository`, `@Autowired`, and `@Qualifier`.
#### Жизненный цикл бинов
###### Донастройка бина, после его окончательной инициализации
- Можно в момент инъекции через конструктор, в конструкторе;
- Пометить аннотацией `@PostConstruct` метод, который должен довыполнить инициализацию бина, может быть даже транзакционным;
- Имплементация интерфейса `InitializingBean`, из которого нужно реализовать метод `afterPropertiesSet()`;
###### Выполнение кода перед удалением бина
- Пометить аннотацией `@PreDestroy` метод, который должен довыполнить инициализацию бина, может быть даже транзакционным;
- Имплементация интерфейса `DisposableBean`, из которого нужно реализовать метод `destroy()`;
#### Bean Scoping
- _Singleton_, по умолчанию все бины такие;
- _Prototype_, каждый вызов _getBean()_ вернёт новый объект;
- _Request_, бин будет единственным в рамках жизненного цикла HTTP реквеста. Имеет смысл только в Web реализациях _ApplicationContext_;
- _Session_, бин будет единственным в рамках HTTP сессии. Имеет смысл только в Web реализациях _ApplicationContext_;
- _GlobalSession_, бин будет единственным в рамках глобальной HTTP сессии. Имеет смысл только в Web реализациях _ApplicationContext_(обычно в портлетных приложениях);
- _SimpleThread_, нужно регать вручную, в книге написано - связывает бин с конкретным инстансом _ThreadLocal_;
#### Конфигурация
- `@PropertySource` если стоит над классом конфигурации, то все проперти из этого источника будут помещены в контекст Спринга(На самом деле _Environment_) и их можно будет внедрять с помощью _@Value_ например;
#### ApplicationContextAware интерфейс
Нужен для(По комменту с индусского форума, нихера непонятно): 
- To inject prototype scoped bean in a singleton bean:
- To access Spring bean from some other code which is not within the Spring container:
- а вообще Если нужно, чтобы бин имел доступ к _ApplicationContext_, можно ему заимплементить _ApplicationContextAware_. Хз почему не заинжектить по старинке.
## Web
### ServletContextListener
У _ServletContextListener_ есть 2 метода:
 - `contextInitialized()` код в реализации этого метода будет выполнен до регистрации сервлетов и фильтров, но послесоздания _ServletContext_;
 - `contextDestroyed()` код в реализации этого метода будет выполнен после уничтожения сервлетов и фильтров, перед уничтожением _ServletContext_;
Пример реализации это Springовый _ContextLoaderListener_;
### DispatcherServlet
- Каждый _DispatcherServlet_ создаёт свой собственный экземпляр _WebApplicationContext_, который наследует основной _ApplicationContext_.