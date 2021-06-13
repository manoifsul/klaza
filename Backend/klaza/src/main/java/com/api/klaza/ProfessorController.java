package com.api.klaza;

import com.api.daos.ProfessorDao;
import com.api.dtos.ProfessorDto;
import com.api.entities.Professor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class ProfessorController {

    private static final Logger log = LoggerFactory.getLogger(ProfessorController.class);
    private ProfessorDao professorDao;

    public ProfessorController(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    @PostMapping(path = "/professor", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Professor> post(@RequestBody ProfessorDto dto) {
        log.info("POST /professor");

        if (professorDao.buscarPorMatricula(dto.getMatricula()).getMatricula() != "") {
            log.warn("Professor com matricula " + dto.getMatricula() + " ja existe");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            Professor p = new Professor();

            p.setNome(dto.getNome());
            p.setMatricula(dto.getMatricula());
            p.setSenha(dto.getSenha());
            p.setEmail(dto.getEmail());

            Professor professorRetorno = professorDao.adicionar(p);

            log.info(professorRetorno.toString());
            log.info("professor " + dto.getNome() + " criado");
            return new ResponseEntity<>(professorRetorno, HttpStatus.OK);
        }
    }

    // Buscar por todos os Usuarios
    @GetMapping(path = "/professor/{id:[0-9]+}")
    public ResponseEntity<List<ProfessorDto>> getAll() {
        log.info("GET /professor");

        List<Professor> allProfessores = professorDao.buscar();
        List<ProfessorDto> allProfessoresDto = new ArrayList<ProfessorDto>();

        for (Professor p : allProfessores) {
            ProfessorDto dto = new ProfessorDto(p.getIdProfessor(), p.getNome(), p.getMatricula(), p.getSenha(), p.getEmail(), p.getMateria(), p.getIdTurmas());

            allProfessoresDto.add(dto);
        }
        return new ResponseEntity<>(allProfessoresDto, HttpStatus.OK);
    }

    // Buscar Usuario pelo id
    @GetMapping(path = "/professor/{id:[0-9]+}")
    public ResponseEntity<ProfessorDto> getProfessorById(@PathVariable("id") long id) {
        log.info("GET /professor/" + id);

        Professor p = professorDao.buscarPorId(id);
        ProfessorDto dto = new ProfessorDto(p.getIdProfessor(), p.getNome(), p.getMatricula(), p.getSenha(), p.getEmail(), p.getMateria(), p.getIdTurmas());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping(path = "/professor/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody Professor p) {
        log.info("PUT /professor/" + id);

        if (professorDao.buscarPorMatricula(p.getMatricula()) != null) {
            log.warn("Professor com matricula " + p.getMatricula() + " ja existe");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            professorDao.editar(p);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/professor/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /professor/" + id);

        try {
            professorDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar o usuario com o id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
