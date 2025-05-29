package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.ItensManejoDTO;
import br.com.fatec.apiCastracao.modelo.ItensManejo;
import br.com.fatec.apiCastracao.repository.ItensManejoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItensManejoController {
    @Autowired
    ItensManejoRepository repositorioItensManejo;

    @PostMapping("/itensmanejo")
    public ResponseEntity<ItensManejo> salvarItensManejo(@RequestBody ItensManejoDTO itensManejoDTO){
        var itensManejoModelo = new ItensManejo();
        BeanUtils.copyProperties(itensManejoDTO, itensManejoModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioItensManejo.save(itensManejoModelo));
    }

    @GetMapping("/itensmanejo")
    public ResponseEntity<List<ItensManejo>> getAllItensManejos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioItensManejo.findAll());
    }

    @GetMapping("/itensmanejo/{id}")
    public ResponseEntity<Object> getItensManejoPorId(@PathVariable(value="id") Integer id){
        Optional<ItensManejo> itensManejo = repositorioItensManejo.findById(id);
        if (itensManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ItensManejo de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(itensManejo.get());
    }

    @PutMapping ("/itensmanejo/{id}")
    public ResponseEntity<Object> atualizarItensManejo(@PathVariable(value="id") Integer id,
                                                  @RequestBody ItensManejoDTO itensManejoDTO){
        Optional<ItensManejo> itensManejo = repositorioItensManejo.findById(id);
        if (itensManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ItensManejo de id "+id+" não existe.");
        }
        BeanUtils.copyProperties(itensManejoDTO, itensManejo.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioItensManejo.save(itensManejo.get()));
    }

    @DeleteMapping ("/itensmanejo/{id}")
    public ResponseEntity<Object> excluirItensManejo(@PathVariable(value="id") Integer id){
        Optional<ItensManejo> itensManejo = repositorioItensManejo.findById(id);
        if (itensManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ItensManejo de id "+id+" não existe.");
        }
        repositorioItensManejo.delete(itensManejo.get());
        return ResponseEntity.status(HttpStatus.OK).body("ItensManejo id "+id+" removido com sucesso!");
    }
}
