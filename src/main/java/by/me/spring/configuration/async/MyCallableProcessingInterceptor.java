package by.me.spring.configuration.async;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import java.util.concurrent.Callable;

public class MyCallableProcessingInterceptor implements CallableProcessingInterceptor
{
    public <T> void beforeConcurrentHandling( NativeWebRequest request, Callable<T> task ) throws Exception
    {
        System.out.println("MyCallableProcessingInterceptor#beforeConcurrentHandling");
    }

    public <T> void preProcess( NativeWebRequest request, Callable<T> task ) throws Exception
    {
        System.out.println("MyCallableProcessingInterceptor#preProcess");
    }

    public <T> void postProcess( NativeWebRequest request, Callable<T> task,
        Object concurrentResult ) throws Exception
    {
        System.out.println("MyCallableProcessingInterceptor#postProcess");
    }

    public <T> Object handleTimeout( NativeWebRequest request, Callable<T> task ) throws Exception
    {
        return RESULT_NONE;
    }

    public <T> Object handleError( NativeWebRequest request, Callable<T> task, Throwable t ) throws Exception
    {
        return RESULT_NONE;
    }

    public <T> void afterCompletion( NativeWebRequest request, Callable<T> task ) throws Exception
    {
        System.out.println("MyCallableProcessingInterceptor#afterCompletion");
    }
}
