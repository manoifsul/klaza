import * as DBTypes from 'src/@types/DB'

export interface qSelectOptions {

    label: string,
    value: string | number,
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