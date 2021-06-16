package com.api.klaza;

import com.api.daos.ProvaDao;
import com.api.dtos.ProvaDto;
import com.api.entities.Prova;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProvaController {

    private static final Logger log = LoggerFactory.getLogger(ProvaController.class);
    private ProvaDao provaDao;

    public ProvaController(ProvaDao provaDao) {
        this.provaDao = provaDao;
    }

    @PostMapping(path = "/prova", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Prova> post(@RequestBody ProvaDto dto) {
        log.info("POST /prova");

            Prova p = new Prova();

            p.setNome(dto.getNome());
            p.setPrazo(dto.getPrazo());
            p.setDescricao(dto.getDescricao());
            p.setInicio(dto.getInicio());
            p.setTempo(dto.getTempo());
            p.setTentativas(dto.getTentativas());
            p.setIdTurma(dto.getIdTurma());
            p.setProfessor(dto.getProfessor());
            p.setMateria(dto.getMateria());

            Prova provaRetorno = provaDao.adicionar(p);

            log.info(provaRetorno.toString());
            log.info("Prova " + dto.getNome() + " criada");
            return new ResponseEntity<>(provaRetorno, HttpStatus.OK);
    }

    // Buscar por todos os Usuarios
    @GetMapping(path = "/provas")
    public ResponseEntity<List<ProvaDto>> getAll() {
        log.info("GET /provas");

        List<Prova> allProvas = provaDao.buscar();
        List<ProvaDto> allProvasDto = new ArrayList<ProvaDto>();

        for (Prova p : allProvas) {
            ProvaDto dto = new ProvaDto(p.getIdProva(), p.getNome(), p.getPrazo(), p.getDescricao(), p.getInicio(), p.getTempo(), p.getTentativas(),
                   p.getArquivo(), p.getAdministrador(), p.getProfessor(), p.getQuestao(), p.getResposta(), p.getMateria(), p.getIdTurma());

            allProvasDto.add(dto);
        }
        return new ResponseEntity<>(allProvasDto, HttpStatus.OK);
    }

    // Buscar Usuario pelo id
    @GetMapping(path = "/prova/{id:[0-9]+}")
    public ResponseEntity<ProvaDto> getProvaById(@PathVariable("id") long id) {
        log.info("GET /prova/" + id);

        Prova p = provaDao.buscarPorId(id);
        ProvaDto dto = new ProvaDto(p.getIdProva(), p.getNome(), p.getPrazo(), p.getDescricao(), p.getInicio(), p.getTempo(), p.getTentativas(),
                p.getArquivo(), p.getAdministrador(), p.getProfessor(), p.getQuestao(), p.getResposta(), p.getMateria(), p.getIdTurma());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping(path = "/prova/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody Prova p) {
        log.info("PUT /prova/" + id);



            provaDao.editar(p);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/prova/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /prova/" + id);

        try {
            provaDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar Prova com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
