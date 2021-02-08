package by.me.spring.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
public class AsyncController
{
    private ExecutorService executor
        = Executors.newCachedThreadPool();

    @GetMapping( "/async-deferredresult" )
    public DeferredResult<ResponseEntity<?>> handleReqDefResult()
    {
        DeferredResult<ResponseEntity<?>> defferedResult = new DeferredResult<>();

        // можно так ForkJoinPool.commonPool().submit( () -> deferedResult.setResult( ResponseEntity.ok( this.executeAsyncMethod() ) ) );

        // или так
        CompletableFuture.supplyAsync( this::executeAsyncMethod )
                         .whenCompleteAsync( ( result, throwable ) -> defferedResult.setResult( ResponseEntity.ok( result ) ) );

        return defferedResult;
    }

    @SneakyThrows
    private String executeAsyncMethod()
    {
        System.out.println( "CompletableFuture started" );
        Thread.sleep( 3000 );
        return "CompletableFuture response";
    }

    @GetMapping( "/async-callable" )
    public Callable<String> handleReqCallableResult()
    {
        return () -> {
            executeAsyncMethod();
            return "Callable response";
        };
    }

    @GetMapping( "/response-body-emmiter" )
    public ResponseEntity<ResponseBodyEmitter> handleRbe()
    {
        SseEmitter emitter = new SseEmitter();
        // ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        executor.execute( () -> {
            try
            {
                Thread.sleep( 2000 );
                emitter.send(
                    "emit first message", MediaType.TEXT_PLAIN );
                Thread.sleep( 1000 );
                emitter.send(
                    "emit second message", MediaType.TEXT_PLAIN );
                emitter.complete();
                Thread.sleep( 1000 );
            }
            catch( Exception ex )
            {
                emitter.completeWithError( ex );
            }
        } );
        return new ResponseEntity( emitter, HttpStatus.OK );
    }

    @GetMapping( "/output-stream" )
    public StreamingResponseBody outputStream()
    {
        return this::writeToOutputStream;
    }

    @SneakyThrows
    private void writeToOutputStream( OutputStream outputStream )
    {
        for( int i = 0; i < 300; i += 1 )
        {
            outputStream.write( "QWERTY".getBytes( StandardCharsets.UTF_8 ) );
            Thread.sleep( 50 );
            outputStream.flush();
        }
    }
}
