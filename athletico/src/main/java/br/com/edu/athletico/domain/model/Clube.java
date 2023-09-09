package br.com.edu.athletico.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
public class Clube {

    private String nome;

    private Integer fundacao;

    private String logoUrl;
}
