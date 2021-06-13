package com.api.klaza;

import com.api.daos.AdministradorDao;
import com.api.dtos.AdministradorDto;
import com.api.entities.Administrador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class AdministradorController {

    private static final Logger log = LoggerFactory.getLogger(AdministradorController.class);
    private AdministradorDao administradorDao;

    public AdministradorController(AdministradorDao administradorDao) {
        this.administradorDao = administradorDao;
    }

    @PostMapping(path = "/administrador", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Administrador> post(@RequestBody AdministradorDto dto) {
        log.info("POST /administrador");

        if (administradorDao.buscarPorLogin(dto.getLogin()).getLogin() != "") {
            log.warn("Administrador com login " + dto.getLogin() + " ja existe");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            Administrador a = new Administrador();

            a.setNome(dto.getNome());
            a.setLogin(dto.getLogin());
            a.setSenha(dto.getSenha());
            a.setEmail(dto.getEmail());

            Administrador administradorRetorno = administradorDao.adicionar(a);

            log.info(administradorRetorno.toString());
            log.info("Administrador " + dto.getNome() + " criado");
            return new ResponseEntity<>(administradorRetorno, HttpStatus.OK);
        }
    }

    // Buscar por todos os Administradores
    @GetMapping(path = "/administrador")
    public ResponseEntity<List<AdministradorDto>> getAll() {
        log.info("GET /administrador");

        List<Administrador> allAdministradores = administradorDao.buscar();
        List<AdministradorDto> allAdministradoresDto = new ArrayList<AdministradorDto>();

        for (Administrador a : allAdministradores) {
            AdministradorDto dto = new AdministradorDto(a.getIdAdministrador(), a.getNome(), a.getLogin(), a.getSenha(), a.getEmail());

            allAdministradoresDto.add(dto);
        }
        return new ResponseEntity<>(allAdministradoresDto, HttpStatus.OK);
    }

    // Buscar Administrador pelo id
    @GetMapping(path = "/administrador/{id:[0-9]+}")
    public ResponseEntity<AdministradorDto> getAlunoById(@PathVariable("id") long id) {
        log.info("GET /aluno/" + id);

        Administrador a = administradorDao.buscarPorId(id);
        AdministradorDto dto = new AdministradorDto(a.getIdAdministrador(), a.getNome(), a.getLogin(), a.getSenha(), a.getEmail());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // atualizar Administrador pelo id
    @PutMapping(path = "/administrador/{id:[0-9]+}")
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody Administrador a) {
        log.info("PUT /administrador/" + id);

        if (administradorDao.buscarPorLogin(a.getLogin()) != null) {
            log.warn("Administrador com matricula " + a.getLogin() + " ja existe");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            administradorDao.editar(a);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // deletar Administrador pelo id
    @DeleteMapping(path = "/administrador/{id:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        log.info("DELETE /administrador/" + id);

        try {
            administradorDao.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Nao foi possivel deletar Administrador com id " + id);
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
