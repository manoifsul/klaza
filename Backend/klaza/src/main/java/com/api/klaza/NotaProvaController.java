package com.api.klaza;

import com.api.daos.NotaProvaDao;
import com.api.dtos.NotaProvaDto;
import com.api.entities.NotaProva;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class NotaProvaController {

    private static final Logger log = LoggerFactory.getLogger(NotaProvaController.class);
    private NotaProvaDao notaProvaDao;

    public NotaProvaController(NotaProvaDao notaProvaDao) {
        this.notaProvaDao = notaProvaDao;
    }

    @PostMapping(path = "/nota_prova", consumes = "application/json", produces = "application/json")
    public ResponseEntity<NotaProva> post(@RequestBody NotaProvaDto dto) {
        log.info("POST /nota_prova");

            NotaProva np = new NotaProva();

            np.setIdNotaProva(dto.getIdNotaProva());
            np.setValor(dto.getValor());
            np.setProva(dto.getProva());
            np.setIdAluno(dto.getIdAluno());

            NotaProva notaProvaRetorno = notaProvaDao.adicionar(np);

            log.info(notaProvaRetorno.toString());
            log.info("NotaProva " + dto.getValor() + " criado");
            return new ResponseEntity<>(notaProvaRetorno, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping(path = "/nota_prova/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody NotaProva np) {
        log.info("PUT /nota_prova/" + id);

            notaProvaDao.editar(np);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/nota_prova/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /nota_prova/" + id);

        try {
            notaProvaDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar NotaProva com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
