package at.spengergasse.legado.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@Embeddable
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User
{
    private String username;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private String email;



}
