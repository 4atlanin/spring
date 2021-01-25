package by.me.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlingController
{
    @GetMapping( "/exception" )
    public ResponseEntity throwException()
    {
        throw new IllegalArgumentException( "string" );
    }

    @ExceptionHandler( { IllegalArgumentException.class } )  // может стоять в @ControllerAdvice, может 
    public ResponseEntity<String> exceptionHandler( RuntimeException ex )
    {
        System.out.println( "@ExceptionHandler - " + ex );
        return ResponseEntity.ok( "string" );
    }
}
