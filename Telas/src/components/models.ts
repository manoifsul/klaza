import * as DBTypes from "src/@types/DB";

export interface DayCardType {

    day: string
    aulas: AulaCardType[]
    trabalhos: TrabalhoCardType[]
    atividades: AtividadeCardType[]
    provas: ProvaCardType[]
    id: number

}

export interface AulaCardType {

    dia: Date
    name: string
    materia: DBTypes.Materia
    id: number
    descricao: string
   
}

export interface AtividadeCardType {

    dia: Date
    name: string
    materia: DBTypes.Materia
    id: number
    descricao: string

}

export interface TrabalhoCardType {

    start: Date
    finish: Date
    name: string
    materia: DBTypes.Materia
    id: number
    descricao: string
    maxTentativas: number
    maxTempo: number
    questoes: QuestaoItemType[],
    respostas: DBTypes.Resposta[]

}

export interface ProvaCardType {

    start: Date
    finish: Date
    name: string
    materia: DBTypes.Materia
    id: number
    descricao: string
    maxTentativas: number
    maxTempo: number
    questoes: QuestaoItemType[]
    tentativas: DBTypes.Resposta[]

}

export interface QuestaoItemType {

    id: number,
    question: string,
    correta: number[],
    alternativas: DBTypes.Questao[]
    type: number // 0 - String, 1 - Single, 2 - Multi, 3 - Upload

}
