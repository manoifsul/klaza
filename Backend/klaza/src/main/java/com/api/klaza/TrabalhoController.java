package com.api.klaza;

import com.api.daos.TrabalhoDao;
import com.api.dtos.TrabalhoDto;
import com.api.entities.Trabalho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class TrabalhoController {

    private static final Logger log = LoggerFactory.getLogger(TrabalhoController.class);
    private TrabalhoDao trabalhoDao;

    public TrabalhoController(TrabalhoDao trabalhoDao) {
        this.trabalhoDao = trabalhoDao;
    }

    @PostMapping(path = "/trabalho", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Trabalho> post(@RequestBody TrabalhoDto dto) {
        log.info("POST /trabalho");

            Trabalho t = new Trabalho();

            t.setNome(dto.getNome());
            t.setPrazo(dto.getPrazo());
            t.setDescricao(dto.getDescricao());
            t.setInicio(dto.getInicio());
            t.setTempo(dto.getTempo());
            t.setTentativas(dto.getTentativas());
            t.setTipo(dto.getTipo());
            t.setIdTurma(dto.getIdTurma());

            Trabalho trabalhoRetorno = trabalhoDao.adicionar(t);

            log.info(trabalhoRetorno.toString());
            log.info("Trabalho " + dto.getNome() + " criado");
            return new ResponseEntity<>(trabalhoRetorno, HttpStatus.OK);
    }

    // Buscar por todos os Usuarios
    @GetMapping(path = "/trabalho/{id:[0-9]+}")
    public ResponseEntity<List<TrabalhoDto>> getAll() {
        log.info("GET /trabalho");

        List<Trabalho> allTrabalhos = trabalhoDao.buscar();
        List<TrabalhoDto> allTrabalhosDto = new ArrayList<TrabalhoDto>();

        for (Trabalho t : allTrabalhos) {
            TrabalhoDto dto = new TrabalhoDto(t.getIdTrabalho(), t.getNome(), t.getPrazo(), t.getDescricao(), t.getInicio(), t.getTempo(), t.getTentativas(),
                    t.getTipo(), t.getProfessor(), t.getAdministrador(), t.getArquivo(), t.getQuestao(), t.getMateria(), t.getResposta(), t.getIdTurma());

            allTrabalhosDto.add(dto);
        }
        return new ResponseEntity<>(allTrabalhosDto, HttpStatus.OK);
    }

    // Buscar Usuario pelo id
    @GetMapping(path = "/trabalho/{id:[0-9]+}")
    public ResponseEntity<TrabalhoDto> getTrabalhoById(@PathVariable("id") long id) {
        log.info("GET /trabalho/" + id);

        Trabalho t = trabalhoDao.buscarPorId(id);
        TrabalhoDto dto = new TrabalhoDto(t.getIdTrabalho(), t.getNome(), t.getPrazo(), t.getDescricao(), t.getInicio(), t.getTempo(), t.getTentativas(),
                t.getTipo(), t.getProfessor(), t.getAdministrador(), t.getArquivo(), t.getQuestao(), t.getMateria(), t.getResposta(), t.getIdTurma());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping(path = "/trabalho/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody Trabalho t) {
        log.info("PUT /trabalho/" + id);

            trabalhoDao.editar(t);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/trabalho/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /trabalho/" + id);

        try {
            trabalhoDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar Trabalho com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
