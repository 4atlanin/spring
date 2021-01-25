package by.me.spring.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static java.lang.System.*;

@Slf4j
@Component
public class CustomServletContextListener implements ServletContextListener
{
    @Override
    public void contextInitialized( ServletContextEvent sce) {
        out.println("Метод CustomServletContextListener#contextInitialized() будет вызван перед регистрацией сервлетов и фильтров, но после создания ServletContext.");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        out.println("Метод CustomServletContextListener#contextInitialized() будет вызван после уничтожения сервлетов и фильтров, но до уничтожения ServletContext.");
    }
}
