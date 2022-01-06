package co.com.davito.randomproyect.controllers;

import co.com.davito.randomproyect.Repositories.RandomRepository;
import co.com.davito.randomproyect.collections.Random;
import co.com.davito.randomproyect.dtos.RandomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api")
public class RandomController {

    private RandomRepository repository;

    @Autowired
    public RandomController(RandomRepository repository) {
        this.repository = repository;
    }

    @PostMapping("crear")
    public Mono<Random> post(@RequestBody RandomDTO randomDTO){
        return Mono.just(new Random())
                .map(entity ->{
                   entity.setDate(new Date());
                   entity.setOriginalList(randomDTO.getList());
                   return entity;
                }).map(entity -> {
                    var list = Stream.of(randomDTO.getList().split(","))
                            .map(p -> p.trim())
                            .collect(Collectors.toList());
                    var randomList = list.stream().collect(Collectors.joining(","));
                    entity.setRandomList(randomList);
                    return entity;
                }).flatMap(repository::save);
    }

    @GetMapping("get")
    public
}
