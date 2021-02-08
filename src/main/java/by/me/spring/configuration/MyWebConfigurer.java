package by.me.spring.configuration;

import by.me.spring.configuration.async.MyAsyncInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MyWebConfigurer implements WebMvcConfigurer
{
    private final MyHandlerInterceptor myHandlerInterceptor;
    private final MyAsyncInterceptor myAsyncInterceptor;

    @Override
    public void addInterceptors( InterceptorRegistry registry )
    {
        registry.addInterceptor( myHandlerInterceptor );
        registry.addInterceptor( myAsyncInterceptor );
    }

}
