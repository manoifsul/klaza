import * as DBTypes from 'src/@types/DB'

export interface qSelectOptions {

    label: string,
    value: string | number | Object,
    disable?: boolean,
    description?: string,
    category?: string
    icon?: string

}

export interface sortTentativas {

    [key: string]: [DBTypes.Resposta[]]

}

export interface sortNotas {

    [key: string]: { 
        
        Provas: { provas: DBTypes.Prova[], notas: DBTypes.NotaProva[] }
        
        Trabalhos: { trabalhos: DBTypes.Trabalho[], notas: DBTypes.NotaTrabalho[] }
    
    }

}

export interface showCorretas {

    [key: string]: {

        correta: boolean
        file: boolean
        
    }

}

export interface qTableColumns {

    name: string
    label: string
    field: string | Function
    required?: boolean
    align?: "left" | "center" | "right"
    sortable?: boolean
    sort?: Function
    sortOrder?: "ad" | "da"
    format?: Function
    style?: string | Function
    classes?: string | Function
    headerStyle?: string
    headerClasses?: string

}

export interface ColumnsTableAdm {

    adm: qTableColumns[]
    aluno: qTableColumns[]
    professor: qTableColumns[]
    turmas: qTableColumns[]

}