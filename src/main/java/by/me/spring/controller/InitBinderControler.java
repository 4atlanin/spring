package by.me.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class InitBinderControler
{
    @Autowired
    @Qualifier( "mvcValidator" )
    private Validator validator;

    @InitBinder              // todo почему-то не работает
    public void initBinder( WebDataBinder binder )
    {
        binder.setValidator( validator );
        binder.addCustomFormatter( new DateFormatter( "yyyy/MM/dd" ) );
    }

    @GetMapping( "/init-binder" )
    public ResponseEntity testInitBinder( @RequestHeader HttpHeaders headers )
    {
        return ResponseEntity.ok()
                             .body( LocalDate.now() );
    }
}
