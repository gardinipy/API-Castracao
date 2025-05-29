package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.InsumosManejoDTO;
import br.com.fatec.apiCastracao.modelo.InsumosManejo;
import br.com.fatec.apiCastracao.repository.InsumosManejoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InsumosManejoController {
    @Autowired
    InsumosManejoRepository repositorioInsumosManejo;

    @PostMapping("/insumosmanejo")
    public ResponseEntity<InsumosManejo> salvarInsumosManejo(@RequestBody InsumosManejoDTO insumosManejoDTO){
        var insumosManejoModelo = new InsumosManejo();
        BeanUtils.copyProperties(insumosManejoDTO, insumosManejoModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioInsumosManejo.save(insumosManejoModelo));
    }

    @GetMapping("/insumosmanejo")
    public ResponseEntity<List<InsumosManejo>> getAllInsumosManejos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioInsumosManejo.findAll());
    }

    @GetMapping("/insumosmanejo/{id}")
    public ResponseEntity<Object> getInsumosManejoPorId(@PathVariable(value="id") Integer id){
        Optional<InsumosManejo> insumosManejo = repositorioInsumosManejo.findById(id);
        if (insumosManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("InsumosManejo de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(insumosManejo.get());
    }

    @PutMapping("/insumosmanejo/{id}")
    public ResponseEntity<Object> atualizarInsumosManejo(@PathVariable(value="id") Integer id,
                                                       @RequestBody InsumosManejoDTO insumosManejoDTO){
        Optional<InsumosManejo> insumosManejo = repositorioInsumosManejo.findById(id);
        if (insumosManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("InsumosManejo de id "+id+" não existe.");
        }
        BeanUtils.copyProperties(insumosManejoDTO, insumosManejo.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioInsumosManejo.save(insumosManejo.get()));
    }

    @DeleteMapping ("/insumosmanejo/{id}")
    public ResponseEntity<Object> excluirInsumosManejo(@PathVariable(value="id") Integer id){
        Optional<InsumosManejo> insumosManejo = repositorioInsumosManejo.findById(id);
        if (insumosManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("InsumosManejo de id "+id+" não existe.");
        }
        repositorioInsumosManejo.delete(insumosManejo.get());
        return ResponseEntity.status(HttpStatus.OK).body("InsumosManejo id "+id+" removido com sucesso!");
    }
}
