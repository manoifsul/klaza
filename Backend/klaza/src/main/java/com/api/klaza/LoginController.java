package com.api.klaza;

import com.api.daos.AdministradorDao;
import com.api.daos.AlunoDao;
import com.api.daos.ProfessorDao;
import com.api.dtos.LoginAdministradorDto;
import com.api.dtos.LoginAlunoDto;
import com.api.dtos.LoginProfessorDto;

import com.api.entities.Administrador;
import com.api.entities.Aluno;
import com.api.entities.Professor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private AdministradorDao administradorDao;
    private ProfessorDao professorDao;
    private AlunoDao alunoDao;

    public LoginController(AdministradorDao administradorDao, ProfessorDao professorDao, AlunoDao alunoDao) {
        this.administradorDao = administradorDao;
        this.professorDao = professorDao;
        this.alunoDao = alunoDao;
    }

    @PostMapping(path = "/login/administrador", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Administrador> post(@RequestBody LoginAdministradorDto dto) {
        log.info("POST /login/administrador");

        if (administradorDao.buscarPorLogin(dto.getLogin()).getSenha().equals(dto.getSenha())) {

            Administrador administradorRetorno = administradorDao.buscarPorLogin(dto.getLogin());

            log.info(administradorRetorno.toString());
            log.info("Administrador " + administradorDao.buscarPorLogin(dto.getLogin()).getNome() + " logado");
            return new ResponseEntity<>(administradorRetorno, HttpStatus.OK);
        } else {
            log.warn("Administrador " + dto.getLogin() + " colocou senha errada");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/professor")
    public ResponseEntity<Professor> post(@RequestBody LoginProfessorDto dto) {
        log.info("POST /login/professor");

        if (professorDao.buscarPorMatricula(dto.getLogin()).getSenha().equals(dto.getSenha())) {
            Professor professorRetorno = professorDao.buscarPorMatricula(dto.getLogin());

            log.info(professorRetorno.toString());
            log.info("professor " + professorDao.buscarPorMatricula(dto.getLogin()) + " logado");
            return new ResponseEntity<>(professorRetorno, HttpStatus.OK);
        } else {
            log.warn("Professor " + dto.getLogin() + " colocou senha errada");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(path = "/login/aluno", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Aluno> post(@RequestBody LoginAlunoDto dto) {
        log.info("POST /login/aluno");

        if (alunoDao.buscarPorMatricula(dto.getLogin()).getSenha().equals(dto.getSenha())) {
            Aluno alunoRetorno = alunoDao.buscarPorMatricula(dto.getLogin());

            log.info(alunoRetorno.toString());
            log.info("professor " + alunoDao.buscarPorMatricula(dto.getLogin()) + " logado");
            return new ResponseEntity<>(alunoRetorno, HttpStatus.OK);
        } else {
            log.warn("Aluno " + dto.getLogin() + " colocou senha errada");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
