package by.me.spring.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

// кажется имплементить LastModified должен хэндлер, с контроллером не прокатывает.

@Controller
public class LastModifiedController// implements LastModified
{

    @GetMapping( "/last-modified" )
    public ResponseEntity getLM( @RequestHeader HttpHeaders headers )
    {
        //  throw new RuntimeException( "string" );
        return ResponseEntity.ok()
                             .lastModified( LocalDateTime.now().toEpochSecond( ZoneOffset.UTC ) )    // вернёт LastModified хидер
                             .body( LocalDate.now() );
    }
}
