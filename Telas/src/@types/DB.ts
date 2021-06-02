export interface Materia {

    idMateria: number
    nome: string

}

export interface NotaProva {

    idNotaProva: number
    valor: number
    prova: Prova

}

export interface NotaTrabalho {

    idNotaTrabalho: number
    valor: number
    trabalho: Trabalho

}

export interface Discord {

    idDiscord: number
    notificacoes: string
    provasTrabalhos: string

}

export interface Turma {

    idTurma: number
    nome: string
    aula: Aula[]
    professor: Professor[]
    trabalho: Trabalho[]
    atividade: Atividade[]
    prova: Prova[]
    materia: Materia
    discord: Discord

}

export interface Trabalho {

    idTrabalho: number
    nome: string
    prazo: Date
    descricao: string
    inicio: Date
    tempo: number
    tentativas: number
    tipo: number
    professor: Professor[]
    administrador: Administrador[]
    arquivo: Arquivo[]
    questao: Questao[]

}

export interface Arquivo {

    idArquivo: number
    link: string

}

export interface Questao {

    idQuestao: number
    pergunta: string
    tipo: number // 0 - texto, 1 - simples, 2 - multipla, 4 - upload
    questaoCorreta: QuestaoCorreta[]
    questaoAlternativa: QuestaoAlternativa[]

}

export interface QuestaoCorreta {

    idQuestaoCorreta: number
    questaoAlternativa: QuestaoAlternativa[]

}

export interface QuestaoAlternativa {

    idQuestaoAlternativa: number
    resposta: string

}

export interface Prova {

    idProva: number
    nome: string
    prazo: Date
    descricao: string
    inicio: Date
    tempo: number
    tentativas: number
    professor: Professor[]
    administrador: Administrador[]
    arquivo: Arquivo[]
    questao: Questao[]
    resposta: Resposta[]

}

export interface Aluno {

    idAluno: number
    nome: string
    matricula: string
    senha: string
    email: string
    notasTrabalhos: NotaTrabalho[]
    notasProvas: NotaProva[]
    turma: Turma[]

}

export interface Professor {

    idProfessor: number
    nome: string
    matricula: string
    senha: string
    email: string
    materia: Materia[]

}

export interface Administrador {

    idAdministrador: number
    nome: string
    senha: string
    email: string
    login: string

}

export interface Aula {

    idAula: number
    nome: string
    materia: Materia
    descricao: string
    inicio: Date
    link: string
    arquivo: Arquivo[]

}

export interface NotaTrabalho {



}

export interface NotaProva {



}

export interface Resposta {

    idResposta: number,
    questao: Questao
    resposta: string
    nroTentativa: number

} 

export interface Atividade {

    idAtividade: number
    nome: string
    descricao: string
    inicio: Date
    arquivo: Arquivo[]
    administrador: Administrador[]
    professor: Professor[]
    resposta: Resposta[]

}