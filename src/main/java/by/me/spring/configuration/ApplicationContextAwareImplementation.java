package by.me.spring.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

@Component
public class ApplicationContextAwareImplementation implements ApplicationContextAware
{
    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    @Override
    public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException
    {
        out.println( "Application context was injected to ApplicationContextAware#setApplicationContext" );
        this.applicationContext = applicationContext;
    }
}
