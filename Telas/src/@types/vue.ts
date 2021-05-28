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