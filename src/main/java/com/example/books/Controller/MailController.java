package com.example.books.model;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailSender sender;

    public MailController(MailSender sender) {
        this.sender = sender;
    }

    @GetMapping()
    public ResponseEntity<String> send(@PathParam("text") String text,
                               @PathParam("toEmail") String toEmail,
                               @PathParam("fromEmail") String fromEmail,
                               @PathParam("subject") String subject){
        try{
            sender.sendEmail(text,toEmail,fromEmail,subject);
            return  ResponseEntity.ok("Сообщение успешно отправлено!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Введенные данные некорректны!");
        }
    }
}
