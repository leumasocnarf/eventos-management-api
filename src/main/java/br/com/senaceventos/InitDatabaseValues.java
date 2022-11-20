package br.com.senaceventos;

import br.com.senaceventos.models.*;
import br.com.senaceventos.models.enums.TipoColaborador;
import br.com.senaceventos.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Configuration
public class InitDatabaseValues {
    /*
        Inicializacao de valores no DB
     */
    @Bean
    CommandLineRunner commandLineRunner(IEquipamentosRepository equipamentosRepository,
                                        ILocaisRepository locaisRepository,
                                        IColaboradoresRepository colaboradoresRepository,
                                        IAgendasRepository agendasRepository,
                                        IUsuariosRepository usuariosRepository) {
        return args -> {
            var equip1 = new Equipamento();
            var equip2 = new Equipamento();
            var equip3 = new Equipamento();
            var equip4 = new Equipamento();
            var equip5 = new Equipamento();

            equip1.setDescricao("Monitor LCD");
            equip1.setObservacao("fragil");

            equip2.setDescricao("Projetor X");
            equip2.setObservacao("110v");

            equip3.setDescricao("Microfone A");
            equip3.setObservacao("Usa pilhas");

            equip4.setDescricao("Palanque medio");
            equip4.setObservacao("Medio");

            equip5.setDescricao("Caixa de som");
            equip5.setObservacao("Precisa de regua");

            var local1 = new Local();
            var local2 = new Local();

            local1.setDescricao("Hub do Cerrado");
            local1.setObservacao("Entrada na rua 3");

            local2.setDescricao("Faculdade Senac");
            local2.setObservacao("Entrada na rua 7");

            var colab1 = new Colaborador();
            var colab2 = new Colaborador();
            var colab3 = new Colaborador();

            colab1.setTipoColaborador(TipoColaborador.COORDENADOR);
            colab1.setNome("Tiago Silva");

            colab2.setTipoColaborador(TipoColaborador.PROFESSOR);
            colab2.setNome("Gabriel Silva");

            colab3.setTipoColaborador(TipoColaborador.PROFESSOR);
            colab3.setNome("Joao Silva");

            var agenda1 = new Agenda();
            var agenda2 = new Agenda();
            var agenda3 = new Agenda();

            agenda1.setTitulo("GYNTECHMAIS");
            agenda1.setInicio(Date.valueOf("2022-10-03"));
            agenda1.setTermino(Date.valueOf("2022-10-03"));
            agenda1.setHoraInicio(Time.valueOf("08:00:00"));
            agenda1.setHoraTermino(Time.valueOf("16:00:00"));
            agenda1.setObservacao("Aberto ao publico");
            agenda1.setColaborador(colab1);
            agenda1.setLocal(local1);

            agenda2.setTitulo("HACKATON");
            agenda2.setInicio(Date.valueOf("2022-07-08"));
            agenda2.setTermino(Date.valueOf("2022-07-08"));
            agenda2.setHoraInicio(Time.valueOf("08:00:00"));
            agenda2.setHoraTermino(Time.valueOf("20:00:00"));
            agenda2.setObservacao("Aberto ao publico");
            agenda2.setColaborador(colab2);
            agenda2.setLocal(local2);

            agenda3.setTitulo("PANORAMA TECH");
            agenda3.setInicio(Date.valueOf("2022-10-03"));
            agenda3.setTermino(Date.valueOf("2022-10-03"));
            agenda3.setHoraInicio(Time.valueOf("19:00:00"));
            agenda3.setHoraTermino(Time.valueOf("21:00:00"));
            agenda3.setObservacao("Aberto ao publico");
            agenda3.setColaborador(colab3);
            agenda3.setLocal(local2);

            var user1 = new Usuario();
            var user2 = new Usuario();
            var user3 = new Usuario();

            user1.setNome("José");
            user1.setEmail("jose@ex.com");
            user1.setSenha("senha123");
            user1.setTelefone("62911111111");

            user2.setNome("Maria");
            user2.setEmail("maria@ex.com");
            user2.setSenha("segredo123");
            user2.setTelefone("62922222222");

            user3.setNome("Eduardo");
            user3.setEmail("ed@ex.com");
            user3.setSenha("sesame123");
            user3.setTelefone("62933333333");



            equipamentosRepository.saveAll(
                    List.of(equip1, equip2, equip3, equip4, equip5)
            );

            locaisRepository.saveAll(
                    List.of(local1, local2)
            );

            colaboradoresRepository.saveAll(
                    List.of(colab1, colab2, colab3)
            );

            agendasRepository.saveAll(
                    List.of(agenda1, agenda2, agenda3)
            );

            usuariosRepository.saveAll(
                    List.of(user1, user2, user3)
            );

            agenda1.reservarEquipamento(equip1);
            agenda2.reservarEquipamento(equip2);
            agenda3.reservarEquipamento(equip3);

            agendasRepository.saveAll(
                    List.of(agenda1, agenda2, agenda3)
            );
        };
    }
}