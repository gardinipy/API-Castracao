package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.AgendamentoDTO;
import br.com.fatec.apiCastracao.modelo.Agendamento;
import br.com.fatec.apiCastracao.modelo.Animal; // Importar Animal
import br.com.fatec.apiCastracao.modelo.Responsavel; // Importar Responsavel
import br.com.fatec.apiCastracao.repository.AgendamentoRepository;
import br.com.fatec.apiCastracao.repository.AnimalRepository; // Importar AnimalRepository
import br.com.fatec.apiCastracao.repository.ResponsavelRepository; // Importar ResponsavelRepository
import jakarta.validation.Valid; // Para validar o DTO
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException; // Para lançar exceções HTTP

import java.util.List;
import java.util.Optional;

@RestController
public class AgendamentoController {

    @Autowired
    AgendamentoRepository repositorioAgendamento;

    @Autowired
    AnimalRepository animalRepository; // Injetar AnimalRepository

    @Autowired
    ResponsavelRepository responsavelRepository; // Injetar ResponsavelRepository

    @PostMapping("/agendamentos")
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody @Valid AgendamentoDTO agendamentoDTO) { // Adicionado @Valid
        var agendamentoModelo = new Agendamento();

        // Buscar Animal pelo ID fornecido no DTO
        Animal animal = animalRepository.findById(agendamentoDTO.animalId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Animal com id " + agendamentoDTO.animalId() + " não encontrado."));

        // Buscar Responsavel pelo ID fornecido no DTO
        Responsavel responsavel = responsavelRepository.findById(agendamentoDTO.responsavelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Responsável com id " + agendamentoDTO.responsavelId() + " não encontrado."));

        // Copiar propriedades simples do DTO para o modelo
        // Excluir 'animalId' e 'responsavelId' pois já foram tratados
        BeanUtils.copyProperties(agendamentoDTO, agendamentoModelo, "animalId", "responsavelId");
        
        // Definir as entidades encontradas no modelo
        agendamentoModelo.setAnimal(animal);
        agendamentoModelo.setResponsavel(responsavel);

        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioAgendamento.save(agendamentoModelo));
    }

    // ... demais métodos (getAllAgendamentos, getAgendamentoPorId, atualizarAgendamento, excluirAgendamento) ...
    // O método atualizarAgendamento também precisaria de lógica similar para buscar Animal e Responsavel pelos IDs
    // se eles puderem ser alterados na atualização.
    
    @GetMapping("/agendamentos")
    public ResponseEntity<List<Agendamento>> getAllAgendamentos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioAgendamento.findAll());
    }

    @GetMapping("/agendamento/{id}")
    public ResponseEntity<Object> getAgendamentoPorId(@PathVariable(value="id") Integer id){
        Optional<Agendamento> agendamento = repositorioAgendamento.findById(id);
        if (agendamento.isEmpty()){
            // Mantendo a resposta original, mas ResponseStatusException também seria uma opção
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(agendamento.get());
    }

    @PutMapping ("/agendamento/{id}")
    public ResponseEntity<Object> atualizarAgendamento(@PathVariable(value="id") Integer id,
                                                  @RequestBody @Valid AgendamentoDTO agendamentoDTO){ // Adicionado @Valid
        Optional<Agendamento> agendamentoOptional = repositorioAgendamento.findById(id);
        if (agendamentoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento de id "+id+" não existe.");
        }
        
        Agendamento agendamentoModelo = agendamentoOptional.get();

        // Buscar e validar Animal se o ID foi fornecido e é diferente do atual (ou se sempre deve ser validado)
        if (agendamentoDTO.animalId() != null && !agendamentoDTO.animalId().equals(agendamentoModelo.getAnimal().getId())) {
            Animal animal = animalRepository.findById(agendamentoDTO.animalId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Animal com id " + agendamentoDTO.animalId() + " não encontrado."));
            agendamentoModelo.setAnimal(animal);
        }

        // Buscar e validar Responsavel se o ID foi fornecido e é diferente do atual
        if (agendamentoDTO.responsavelId() != null && !agendamentoDTO.responsavelId().equals(agendamentoModelo.getResponsavel().getId())) {
            Responsavel responsavel = responsavelRepository.findById(agendamentoDTO.responsavelId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Responsável com id " + agendamentoDTO.responsavelId() + " não encontrado."));
            agendamentoModelo.setResponsavel(responsavel);
        }
        
        // Copiar outras propriedades
        // É importante ter cuidado com BeanUtils.copyProperties em atualizações para não sobrescrever
        // campos que não deveriam ser alterados ou para lidar com nulos do DTO.
        // Uma abordagem mais segura é copiar campo a campo ou usar um DTO específico para atualização.
        agendamentoModelo.setObservacoes(agendamentoDTO.observacoes());
        agendamentoModelo.setDataPrevista(agendamentoDTO.dataPrevista());

        // Retornar HttpStatus.OK para atualizações bem-sucedidas é mais convencional
        return ResponseEntity.status(HttpStatus.OK).body(repositorioAgendamento.save(agendamentoModelo));
    }

    @DeleteMapping ("/agendamento/{id}")
    public ResponseEntity<Object> excluirAgendamento(@PathVariable(value="id") Integer id){
        Optional<Agendamento> agendamento = repositorioAgendamento.findById(id);
        if (agendamento.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento de id "+id+" não existe.");
        }
        repositorioAgendamento.delete(agendamento.get());
        // HttpStatus.NO_CONTENT (204) também é uma boa opção para DELETE bem-sucedido
        return ResponseEntity.status(HttpStatus.OK).body("Agendamento id "+id+" removido com sucesso!");
    }
}
