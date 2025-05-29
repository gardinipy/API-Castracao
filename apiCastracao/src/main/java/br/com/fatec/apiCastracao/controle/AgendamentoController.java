package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.AgendamentoDTO;
import br.com.fatec.apiCastracao.modelo.Agendamento;
import br.com.fatec.apiCastracao.repository.AgendamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AgendamentoController {
    @Autowired
    AgendamentoRepository repositorioAgendamento;

    @PostMapping("/agendamentos")
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO){
        var agendamentoModelo = new Agendamento();
        BeanUtils.copyProperties(agendamentoDTO, agendamentoModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioAgendamento.save(agendamentoModelo));
    }

    @GetMapping("/agendamentos")
    public ResponseEntity<List<Agendamento>> getAllAgendamentos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioAgendamento.findAll());
    }

    @GetMapping("/agendamento/{id}")
    public ResponseEntity<Object> getAgendamentoPorId(@PathVariable(value="id") Integer id){
        Optional<Agendamento> agendamento = repositorioAgendamento.findById(id);
        if (agendamento.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(agendamento.get());
    }

    @PutMapping ("/agendamento/{id}")
    public ResponseEntity<Object> atualizarAgendamento(@PathVariable(value="id") Integer id,
                                                  @RequestBody AgendamentoDTO agendamentoDTO){
        Optional<Agendamento> agendamento = repositorioAgendamento.findById(id);
        if (agendamento.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento de id "+id+" não existe.");
        }
        BeanUtils.copyProperties(agendamentoDTO, agendamento.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioAgendamento.save(agendamento.get()));
    }

    @DeleteMapping ("/agendamento/{id}")
    public ResponseEntity<Object> excluirAgendamento(@PathVariable(value="id") Integer id){
        Optional<Agendamento> agendamento = repositorioAgendamento.findById(id);
        if (agendamento.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento de id "+id+" não existe.");
        }
        repositorioAgendamento.delete(agendamento.get());
        return ResponseEntity.status(HttpStatus.OK).body("Agendamento id "+id+" removido com sucesso!");
    }
}
