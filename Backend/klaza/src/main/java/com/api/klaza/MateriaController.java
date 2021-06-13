package com.api.klaza;

import com.api.daos.MateriaDao;
import com.api.dtos.MateriaDto;
import com.api.entities.Materia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class MateriaController {

    private static final Logger log = LoggerFactory.getLogger(MateriaController.class);
    private MateriaDao materiaDao;

    public MateriaController(MateriaDao materiaDao) {
        this.materiaDao = materiaDao;
    }

    @PostMapping(path = "/materia", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Materia> post(@RequestBody MateriaDto dto) {
        log.info("POST /materia");

            Materia m = new Materia();

            m.setNome(dto.getNome());

            Materia materiaRetorno = materiaDao.adicionar(m);

            log.info(materiaRetorno.toString());
            log.info("Materia " + dto.getNome() + " criada");
            return new ResponseEntity<>(materiaRetorno, HttpStatus.OK);
    }

    // Buscar por todos os Usuarios
    @GetMapping(path = "/materia/{id:[0-9]+}")
    public ResponseEntity<List<MateriaDto>> getAll() {
        log.info("GET /materia");

        List<Materia> allMaterias = materiaDao.buscar();
        List<MateriaDto> allMateriasDto = new ArrayList<MateriaDto>();

        for (Materia m : allMaterias) {
            MateriaDto dto = new MateriaDto(m.getIdMateria(), m.getNome(), m.getIdProfessor());

            allMateriasDto.add(dto);
        }
        return new ResponseEntity<>(allMateriasDto, HttpStatus.OK);
    }

    // Buscar Usuario pelo id
    @GetMapping(path = "/materia/{id:[0-9]+}")
    public ResponseEntity<MateriaDto> getMateriaById(@PathVariable("id") long id) {
        log.info("GET /materia/" + id);

        Materia m = materiaDao.buscarPorId(id);
        MateriaDto dto = new MateriaDto(m.getIdMateria(), m.getNome(), m.getIdProfessor());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping(path = "/materia/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody Materia m) {
        log.info("PUT /materia/" + id);

            materiaDao.editar(m);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/materia/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /materia/" + id);

        try {
            materiaDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar Materia com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
