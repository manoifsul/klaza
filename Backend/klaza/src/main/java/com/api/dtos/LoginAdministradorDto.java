package com.api.dtos;

public class LoginAdministradorDto {
    private String login;
    private String senha;

    public LoginAdministradorDto() {
        this.login = "";
        this.senha = "";
    }

    public LoginAdministradorDto(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "LoginAdministradorDto{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
