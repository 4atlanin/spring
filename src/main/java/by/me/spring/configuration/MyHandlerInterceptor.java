package by.me.spring.configuration;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@NoArgsConstructor
public class MyHandlerInterceptor implements HandlerInterceptor  // или расширить  HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception
    {
        System.out.println( "preHandle of MyHandlerInterceptor" );
        response.addHeader( "bibis", "pekush" );
        return true;   // если тру, то цепочка выполнения продолжится, иначе в контроллер, или следующие интерцепторы не попадём
    }

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception
    {
        response.addHeader( "pekush", "bibis" );   // слишком поздно модифицировать респонс
        System.out.println( "postHandle of MyHandlerInterceptor" );
    }

    @Override
    public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception
    {
        System.out.println( "afterCompletion of MyHandlerInterceptor" );
    }
}
