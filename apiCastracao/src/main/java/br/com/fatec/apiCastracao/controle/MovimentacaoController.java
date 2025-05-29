package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.MovimentacaoDTO;
import br.com.fatec.apiCastracao.modelo.Movimentacao;
import br.com.fatec.apiCastracao.repository.MovimentacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovimentacaoController {
    @Autowired
    MovimentacaoRepository repositorioMovimentacao;

    @PostMapping("/movimentacoes")
    public ResponseEntity<Movimentacao> salvarMovimentacao(@RequestBody MovimentacaoDTO movimentacaoDTO){
        var movimentacaoModelo = new Movimentacao();
        BeanUtils.copyProperties(movimentacaoDTO, movimentacaoModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioMovimentacao.save(movimentacaoModelo));
    }

    @GetMapping("/movimentacoes")
    public ResponseEntity<List<Movimentacao>> getAllMovimentacaos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioMovimentacao.findAll());
    }

    @GetMapping("/movimentacao/{id}")
    public ResponseEntity<Object> getMovimentacaoPorId(@PathVariable(value="id") Integer id){
        Optional<Movimentacao> movimentacao = repositorioMovimentacao.findById(id);
        if (movimentacao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentacao de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(movimentacao.get());
    }

    @PutMapping ("/movimentacao/{id}")
    public ResponseEntity<Object> atualizarMovimentacao(@PathVariable(value="id") Integer id,
                                                  @RequestBody MovimentacaoDTO movimentacaoDTO){
        Optional<Movimentacao> movimentacao = repositorioMovimentacao.findById(id);
        if (movimentacao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentacao de id "+id+" não existe.");
        }
        BeanUtils.copyProperties(movimentacaoDTO, movimentacao.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioMovimentacao.save(movimentacao.get()));
    }

    @DeleteMapping ("/movimentacao/{id}")
    public ResponseEntity<Object> excluirMovimentacao(@PathVariable(value="id") Integer id){
        Optional<Movimentacao> movimentacao = repositorioMovimentacao.findById(id);
        if (movimentacao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentacao de id "+id+" não existe.");
        }
        repositorioMovimentacao.delete(movimentacao.get());
        return ResponseEntity.status(HttpStatus.OK).body("Movimentacao id "+id+" removido com sucesso!");
    }
}
