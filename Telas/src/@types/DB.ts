export interface Materia {

    id: number
    name: string
    discord: { notificacao: string, trabalhos_provas: string }

}

export interface Turma {

    id: number
    name: string

}

export interface Questao {

    id: number
    resposta: string

}

export interface Aluno {

    id: number,
    nome: string,
    matricula: string,
    senha: string,
    email: string,
    notasTrabalhos: NotaTrabalho[],
    notasProvas: NotaProva[]

}

export interface Professor {

    id: number,
    nome: string,
    matricula: string,
    senha: string,
    email: string,

}

export interface Administrador {

    id: number,
    nome: string,
    senha: string,
    email: string,

}

export interface Aula {

    id: 

}

export interface NotaTrabalho {



}

export interface NotaProva {



}

export interface Resposta {

    id: number,
    aluno: Aluno
    questao: Questao
    resposta: string
    num_tentativa: number

}