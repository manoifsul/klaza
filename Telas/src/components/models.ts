export interface Day {

    day: string
    events: Event[]
    id: number

}

export interface Event {

    type: "Aula" | "Trabalho" | "Prova" | "Atividade"
    time: string
    name: string
    materia: string
    id: number

}