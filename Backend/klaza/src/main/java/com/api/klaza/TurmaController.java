package com.api.klaza;

import com.api.daos.TurmaDao;
import com.api.dtos.TurmaDto;
import com.api.entities.Turma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TurmaController {

    private static final Logger log = LoggerFactory.getLogger(TurmaController.class);
    private TurmaDao turmaDao;

    public TurmaController(TurmaDao turmaDao) {
        this.turmaDao = turmaDao;
    }

    @PostMapping(path = "/turma", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Turma> post(@RequestBody TurmaDto dto) {
        log.info("POST /turma");

            Turma t = new Turma();

            t.setNome(dto.getNome());
            t.setMateria(dto.getMateria());
            t.setDiscord(dto.getDiscord());
            t.setAluno(dto.getAluno());
            t.setProfessor(dto.getProfessor());
            Turma turmaRetorno = turmaDao.adicionar(t);

            log.info(turmaRetorno.toString());
            log.info("Turma " + dto.getNome() + " criada");
            return new ResponseEntity<>(turmaRetorno, HttpStatus.OK);
    }

    // Buscar por todos os Usuarios
    @GetMapping(path = "/turmas")
    public ResponseEntity<List<TurmaDto>> getAll() {
        log.info("GET /turmas");

        List<Turma> allTurmas = turmaDao.buscar();
        List<TurmaDto> allTurmasDto = new ArrayList<TurmaDto>();

        for (Turma t : allTurmas) {
            TurmaDto dto = new TurmaDto(t.getIdTurma(), t.getNome(), t.getAula(), t.getTrabalho(), t.getAtividade(), t.getProva(), t.getAluno(), t.getProfessor(), t.getMateria(), t.getDiscord());

            allTurmasDto.add(dto);
        }
        return new ResponseEntity<>(allTurmasDto, HttpStatus.OK);
    }

    // Buscar Usuario pelo id
    @GetMapping(path = "/turma/{id:[0-9]+}")
    public ResponseEntity<TurmaDto> getTurmaById(@PathVariable("id") long id) {
        log.info("GET /turma/" + id);

        Turma t = turmaDao.buscarPorId(id);
        TurmaDto dto = new TurmaDto(t.getIdTurma(), t.getNome(), t.getAula(), t.getTrabalho(), t.getAtividade(), t.getProva(), t.getAluno(), t.getProfessor(), t.getMateria(), t.getDiscord());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping(path = "/turma/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody Turma t) {
        log.info("PUT /turma/" + id);

            turmaDao.editar(t);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/turma/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /turma/" + id);

        try {
            turmaDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar Turma com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
