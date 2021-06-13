package com.api.klaza;

import com.api.daos.NotaTrabalhoDao;
import com.api.dtos.NotaTrabalhoDto;
import com.api.entities.NotaTrabalho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class NotaTrabalhoController {

    private static final Logger log = LoggerFactory.getLogger(NotaTrabalhoController.class);
    private NotaTrabalhoDao notaTrabalhoDao;

    public NotaTrabalhoController(NotaTrabalhoDao notaTrabalhoDao) {
        this.notaTrabalhoDao = notaTrabalhoDao;
    }

    @PostMapping(path = "/nota_trabalho", consumes = "application/json", produces = "application/json")
    public ResponseEntity<NotaTrabalho> post(@RequestBody NotaTrabalhoDto dto) {
        log.info("POST /nota_trabalho");

            NotaTrabalho nt = new NotaTrabalho();

            nt.setIdNotaTrabalho(dto.getIdNotaTrabalho());
            nt.setValor(dto.getValor());
            nt.setTrabalho(dto.getTrabalho());
            nt.setIdAluno(dto.getIdAluno());

            NotaTrabalho notaTrabalhoRetorno = notaTrabalhoDao.adicionar(nt);

            log.info(notaTrabalhoRetorno.toString());
            log.info("NotaTrabalho " + dto.getValor() + " criado");
            return new ResponseEntity<>(notaTrabalhoRetorno, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping(path = "/nota_trabalho/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody NotaTrabalho nt) {
        log.info("PUT /nota_trabalho/" + id);

            notaTrabalhoDao.editar(nt);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/nota_trabalho/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /nota_trabalho/" + id);

        try {
            notaTrabalhoDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar NotaTrabalho com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
