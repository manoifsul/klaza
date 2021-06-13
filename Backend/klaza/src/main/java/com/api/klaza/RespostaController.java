package com.api.klaza;

import com.api.daos.RespostaDao;
import com.api.dtos.RespostaDto;
import com.api.entities.Resposta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class RespostaController {

    private static final Logger log = LoggerFactory.getLogger(RespostaController.class);
    private RespostaDao respostaDao;

    public RespostaController(RespostaDao respostaDao) {
        this.respostaDao = respostaDao;
    }

    @PostMapping(path = "/resposta", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Resposta> post(@RequestBody RespostaDto dto) {
        log.info("POST /resposta");

            Resposta r = new Resposta();

            r.setIdResposta(dto.getIdResposta());
            r.setResposta(dto.getResposta());
            r.setIdAluno(dto.getIdAluno());
            r.setQuestao(dto.getQuestao());

            Resposta respostaRetorno = respostaDao.adicionar(r);

            log.info(respostaRetorno.toString());
            log.info("Resposta " + dto.getResposta() + " criada");
            return new ResponseEntity<>(respostaRetorno, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping(path = "/resposta/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody Resposta r) {
        log.info("PUT /resposta/" + id);
            respostaDao.editar(r);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/resposta/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /resposta/" + id);

        try {
            respostaDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar Resposta com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
