package es.nextdigital.demo.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsultingInDTO implements Serializable {

    @NotNull
    private Long userId;

    @NotBlank
    private String iban;

    @NotBlank
    private String move;
}
