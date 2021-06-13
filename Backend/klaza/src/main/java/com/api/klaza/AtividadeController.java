package com.api.klaza;

import com.api.daos.AtividadeDao;
import com.api.dtos.AtividadeDto;
import com.api.entities.Atividade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class AtividadeController {

    private static final Logger log = LoggerFactory.getLogger(AtividadeController.class);
    private AtividadeDao atividadeDao;

    public AtividadeController(AtividadeDao atividadeDao) {
        this.atividadeDao = atividadeDao;
    }

    @PostMapping(path = "/atividade", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Atividade> post(@RequestBody AtividadeDto dto) {
        log.info("POST /atividade");

            Atividade a = new Atividade();

            a.setNome(dto.getNome());
            a.setDescricao(dto.getDescricao());
            a.setInicio(dto.getInicio());
            a.setIdTurma(dto.getIdTurma());

            Atividade atividadeRetorno = atividadeDao.adicionar(a);

            log.info(atividadeRetorno.toString());
            log.info("Atividade " + dto.getNome() + " criada");
            return new ResponseEntity<>(atividadeRetorno, HttpStatus.OK);
    }

    // Buscar por todos os Usuarios
    @GetMapping(path = "/atividade/{id:[0-9]+}")
    public ResponseEntity<List<AtividadeDto>> getAll() {
        log.info("GET /atividade");

        List<Atividade> allAtividades = atividadeDao.buscar();
        List<AtividadeDto> allAtividadesDto = new ArrayList<AtividadeDto>();

        for (Atividade a : allAtividades) {
            AtividadeDto dto = new AtividadeDto(a.getIdAtividade(), a.getNome(), a.getDescricao(), a.getInicio(), a.getArquivo(), a.getAdministrador(),
                    a.getProfessor(), a.getResposta(), a.getMateria(), a.getIdTurma());

            allAtividadesDto.add(dto);
        }
        return new ResponseEntity<>(allAtividadesDto, HttpStatus.OK);
    }

    // Buscar Usuario pelo id
    @GetMapping(path = "/atividade/{id:[0-9]+}")
    public ResponseEntity<AtividadeDto> getAtividadeById(@PathVariable("id") long id) {
        log.info("GET /atividade/" + id);

        Atividade a = atividadeDao.buscarPorId(id);
        AtividadeDto dto = new AtividadeDto(a.getIdAtividade(), a.getNome(), a.getDescricao(), a.getInicio(), a.getArquivo(), a.getAdministrador(),
                a.getProfessor(), a.getResposta(), a.getMateria(), a.getIdTurma());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping(path = "/atividade/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody Atividade a) {
        log.info("PUT /atividade/" + id);

            atividadeDao.editar(a);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/atividade/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /atividade/" + id);

        try {
            atividadeDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar Atividade com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
