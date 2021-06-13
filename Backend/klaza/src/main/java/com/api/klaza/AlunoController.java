package com.api.klaza;

import com.api.daos.AlunoDao;
import com.api.dtos.AlunoDto;
import com.api.entities.Aluno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AlunoController {

    private static final Logger log = LoggerFactory.getLogger(AlunoController.class);
    private AlunoDao alunoDao;

    public AlunoController(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }


    @PostMapping(path = "/aluno", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Aluno> post(@RequestBody AlunoDto dto) {
        log.info("POST /aluno");

        if (alunoDao.buscarPorMatricula(dto.getMatricula()).getMatricula() != "") {
            log.warn("Aluno com matricula " + dto.getMatricula() + " ja existe");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            Aluno a = new Aluno();

            a.setNome(dto.getNome());
            a.setMatricula(dto.getMatricula());
            a.setSenha(dto.getSenha());
            a.setEmail(dto.getEmail());

            Aluno alunoRetorno = alunoDao.adicionar(a);

            log.info(alunoRetorno.toString());
            log.info("Aluno " + dto.getNome() + " criado");
            return new ResponseEntity<>(alunoRetorno, HttpStatus.OK);
        }
    }

    // Buscar por todos os Usuarios
    @GetMapping(path = "/aluno")
    public ResponseEntity<List<AlunoDto>> getAll() {
        log.info("GET /aluno");

        List<Aluno> allAlunos = alunoDao.buscar();
        List<AlunoDto> allAlunosDto = new ArrayList<AlunoDto>();

        for (Aluno a : allAlunos) {
            AlunoDto dto = new AlunoDto(a.getIdAluno(), a.getNome(), a.getMatricula(), a.getSenha(), a.getEmail(), a.getNotaTrabalho(), a.getNotaProva(), a.getIdTurmas());

            allAlunosDto.add(dto);
        }
        return new ResponseEntity<>(allAlunosDto, HttpStatus.OK);
    }

    // Buscar Usuario pelo id
    @GetMapping(path = "/aluno/{id:[0-9]+}")
    public ResponseEntity<AlunoDto> getAlunoById(@PathVariable("id") long id) {
        log.info("GET /aluno/" + id);

        Aluno a = alunoDao.buscarPorId(id);
        AlunoDto dto = new AlunoDto(a.getIdAluno(), a.getNome(), a.getMatricula(), a.getSenha(), a.getEmail(), a.getNotaTrabalho(), a.getNotaProva(), a.getIdTurmas());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // atualizar Usuario pelo id
    @PutMapping("aluno/{id:[0-9]+")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @  RequestBody Aluno a) {
        log.info("PUT /aluno/" + id);

        if (alunoDao.buscarPorMatricula(a.getMatricula()) != null) {
            log.warn("Professor com matricula " + a.getMatricula() + " ja existe");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            alunoDao.editar(a);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // deletar Usuario pelo id
    @DeleteMapping(path = "/aluno/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /aluno/" + id);

        try {
            alunoDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar Aluno com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
