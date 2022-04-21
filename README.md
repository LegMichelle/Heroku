#ReadMe File: 

##(Stand 14.12)
Ich habe keine Vererbung und auch keine One-To-One Relationship in diesem Projekt, weil die Angabe erst später hochgeladen wurde und ich werde diese 2 Sachen dann nachholen. 


##(Stand 26.02)

Im WebAppArtistController ist in dieser Methode:  `public ModelAndView startPage()` ein Builder eingebaut, das dient nur für das Testen

##(Stand 06.03)
Der Artist Part ist komplett fertig. Das Problem, das ich habe, ist, dass der Song Part von dieser Webapplikation 
ein Problem mit Artist hat. Die Fehlermeldung ist, dass er ein String nicht in ein ArtistDto umwandeln:
`` 2022-03-06 01:34:59.349  WARN 31232 --- [qtp677329142-38] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException: Failed to convert value of type 'java.lang.String' to required type 'at.spengergasse.legado.presentation.restapi.ArtistDto'; nested exception is java.lang.IllegalStateException: Cannot convert value of type 'java.lang.String' to required type 'at.spengergasse.legado.presentation.restapi.ArtistDto': no matching editors or conversion strategy found]``
Ich weiß nicht woher dieser String kommt. 
Ansonsten würde es ohne dem Artist funktionieren 


##(Stand 30.03)

Wenn ich bei einem Song einen Artist hinzufüge oder bei der Playlist Songs hinzufügen möchte kommt diese Fehlermeldung:

``` 
org.springframework.web.util.NestedServletException: Request processing failed; 
nested exception is org.springframework.dao.InvalidDataAccessApiUsageException: detached entity passed to persist: 
at.spengergasse.legado.model.Artist; nested exception is org.hibernate.PersistentObjectException: 
detached entity passed to persist: at.spengergasse.legado.model.Artist 
```

** Ich weiß wo der Fehler liegt (Transaktion), ich arbeite auch an der Lösung! **




