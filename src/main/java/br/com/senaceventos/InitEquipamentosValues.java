package br.com.senaceventos;

import br.com.senaceventos.Entities.Equipamento;
import br.com.senaceventos.Repositories.IEquipamentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InitEquipamentosValues {
    /*
        Inserção de valores de teste na inicialização;
    */
    @Bean
    CommandLineRunner commandLineRunner(IEquipamentoRepository equipamentoRepository){
        return args -> {
            Equipamento eqpmnt1 = new Equipamento(
                    "Projetor VX-01",
                    "modelo 01, cuidado com uso."
            );

            Equipamento eqpmnt2 = new Equipamento(
                    "Monitor LCD",
                    "fragil."
            );

            equipamentoRepository.saveAll(
                    List.of(eqpmnt1, eqpmnt2)
            );
        };
    }
}
