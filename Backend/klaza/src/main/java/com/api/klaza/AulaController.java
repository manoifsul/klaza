package com.api.klaza;

import com.api.daos.AulaDao;
import com.api.dtos.AulaDto;
import com.api.entities.Aula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class AulaController {


    private static final Logger log = LoggerFactory.getLogger(AulaController.class);
    private AulaDao aulaDao;

    public AulaController(AulaDao aulaDao) {
        this.aulaDao = aulaDao;
    }

    @PostMapping(path = "/aula", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Aula> post(@RequestBody AulaDto dto) {
        log.info("POST /aula");

            Aula a = new Aula();

            a.setNome(dto.getNome());
            a.setMateria(dto.getMateria());
            a.setDescricao(dto.getDescricao());
            a.setInicio(dto.getInicio());
            a.setLink(dto.getLink());

            Aula aulaRetorno = aulaDao.adicionar(a);

            log.info(aulaRetorno.toString());
            log.info("Aula " + dto.getNome() + " criada");
            return new ResponseEntity<>(aulaRetorno, HttpStatus.OK);
    }

    // Buscar por todos os Usuarios
    @GetMapping(path = "/aula/{id:[0-9]+}")
    public ResponseEntity<List<AulaDto>> getAll() {
        log.info("GET /aula");

        List<Aula> allAulas = aulaDao.buscar();
        List<AulaDto> allAulasDto = new ArrayList<AulaDto>();

        for (Aula a : allAulas) {
            AulaDto dto = new AulaDto(a.getIdAula(), a.getNome(), a.getMateria(), a.getDescricao(), a.getInicio(), a.getLink(), a.getArquivo(), a.getIdturma());

            allAulasDto.add(dto);
        }
        return new ResponseEntity<>(allAulasDto, HttpStatus.OK);
    }

    // Buscar Usuario pelo id
    @GetMapping(path = "/aula/{id:[0-9]+}")
    public ResponseEntity<AulaDto> getAulaById(@PathVariable("id") long id) {
        log.info("GET /aula/" + id);

        Aula a = aulaDao.buscarPorId(id);
        AulaDto dto = new AulaDto(a.getIdAula(), a.getNome(), a.getMateria(), a.getDescricao(), a.getInicio(), a.getLink(), a.getArquivo(), a.getIdturma());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping(path = "/aula/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody Aula a) {
        log.info("PUT /aula/" + id);

            aulaDao.editar(a);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/aula/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /aula/" + id);

        try {
            aulaDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar Aula com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
