package by.me.spring.configuration;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice
{
    @Override
    public boolean supports( MethodParameter returnType, Class converterType )
    {
        return "getLM".equals( ( returnType.getMethod().getName() ) );
    }

    // позволяет изменить респонс после контроллера, но до его коммита
    @Override
    public Object beforeBodyWrite( Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response )
    {
        return "Extended body " + body;
    }
}