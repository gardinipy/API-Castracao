package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.AnimalDTO;
import br.com.fatec.apiCastracao.modelo.Animal;
import br.com.fatec.apiCastracao.repository.AnimalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AnimalController {
    @Autowired
    AnimalRepository repositorioAnimal;

    @PostMapping("/animais")
    public ResponseEntity<Animal> salvarAnimal(@RequestBody AnimalDTO animalDTO){
        var animalModelo = new Animal();
        BeanUtils.copyProperties(animalDTO, animalModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioAnimal.save(animalModelo));
    }

    @GetMapping("/animais")
    public ResponseEntity<List<Animal>> getAllAnimals(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioAnimal.findAll());
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<Object> getAnimalPorId(@PathVariable(value="id") Integer id){
        Optional<Animal> animal = repositorioAnimal.findById(id);
        if (animal.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animal.get());
    }

    @PutMapping ("/animal/{id}")
    public ResponseEntity<Object> atualizarAnimal(@PathVariable(value="id") Integer id,
                                                   @RequestBody AnimalDTO animalDTO){
        Optional<Animal> animal = repositorioAnimal.findById(id);
        if (animal.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal de id "+id+" não existe.");
        }
        BeanUtils.copyProperties(animalDTO, animal.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioAnimal.save(animal.get()));
    }

    @DeleteMapping ("/animal/{id}")
    public ResponseEntity<Object> excluirAnimal(@PathVariable(value="id") Integer id){
        Optional<Animal> animal = repositorioAnimal.findById(id);
        if (animal.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal de id "+id+" não existe.");
        }
        repositorioAnimal.delete(animal.get());
        return ResponseEntity.status(HttpStatus.OK).body("Animal id "+id+" removido com sucesso!");
    }
}
