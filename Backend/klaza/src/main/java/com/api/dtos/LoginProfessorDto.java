package com.api.dtos;

public class LoginProfessorDto {
    private String login;
    private String senha;

    public LoginProfessorDto() {
        this.login = "";
        this.senha = "";
    }

    public LoginProfessorDto(String login, String senha) {
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
        return "LoginProfessorDto{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
