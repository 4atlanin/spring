package by.me.spring.configuration.async;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

public class MyDefferedResultProcessingInterceptor implements DeferredResultProcessingInterceptor
{

    public <T> void beforeConcurrentHandling( NativeWebRequest request, DeferredResult<T> deferredResult)
        throws Exception {
        System.out.println("MyDefferedResultProcessingInterceptor#beforeConcurrentHandling");
    }

    public <T> void preProcess(NativeWebRequest request, DeferredResult<T> deferredResult)
        throws Exception {
        System.out.println("MyDefferedResultProcessingInterceptor#preProcess");
    }
    
    public <T> void postProcess(NativeWebRequest request, DeferredResult<T> deferredResult,
        Object concurrentResult) throws Exception {
        System.out.println("MyDefferedResultProcessingInterceptor#postProcess");
    }
    
    public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> deferredResult)
        throws Exception {

        return true;
    }


    public <T> boolean handleError(NativeWebRequest request, DeferredResult<T> deferredResult,
        Throwable t) throws Exception {

        return true;
    }


    public <T> void afterCompletion(NativeWebRequest request, DeferredResult<T> deferredResult)
        throws Exception {
        System.out.println("MyDefferedResultProcessingInterceptor#afterCompletion");
    }
}
