package by.me.spring.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MyWebConfigurer implements WebMvcConfigurer
{
    private final MyHandlerInterceptor myHandlerInterceptor;

    @Override
    public void addInterceptors( InterceptorRegistry registry )
    {
        registry.addInterceptor( myHandlerInterceptor );
    }    
}
