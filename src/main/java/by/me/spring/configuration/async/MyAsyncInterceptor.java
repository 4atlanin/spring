package by.me.spring.configuration.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyAsyncInterceptor implements AsyncHandlerInterceptor
{
    private static final Object CALLABLE_INTERCEPTOR_KEY = new Object();
    private static final Object DEFFERED_INTERCEPTOR_KEY = new Object();

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
        throws Exception
    {
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager( request );
        asyncManager.registerCallableInterceptor( CALLABLE_INTERCEPTOR_KEY, new MyCallableProcessingInterceptor() );
        asyncManager.registerDeferredResultInterceptor( DEFFERED_INTERCEPTOR_KEY, new MyDefferedResultProcessingInterceptor() );

        return true;
    }

    @Override
    public void afterConcurrentHandlingStarted( HttpServletRequest request, HttpServletResponse response,
        Object handler ) throws Exception
    {
        System.out.println( "afterConcurrentHandlingStarted" );
    }
}
