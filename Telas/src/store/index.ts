import { store } from 'quasar/wrappers';
import { Administrador, Aluno, Atividade, Aula, Materia, Professor, Prova, Trabalho, Turma } from 'src/@types/DB';
import { DayCardType } from 'src/components/models';
import Vuex from 'vuex';

export interface StateInterface {

    idUser: number,
    typeUser: "aluno" | "professor" | "administrador",

    materias: Materia[],
    materiasProfessor: Materia[]

    days: DayCardType[]
    allDays: DayCardType[]

    alunos: Aluno[]
    professores: Professor[]
    administradores: Administrador[]

    provas: Prova[]
    trabalhos: Trabalho[]
    atividades: Atividade[]
    aulas: Aula[]

    turmas: Turma[]

}

export default store(function ({ Vue }) {
    Vue.use(Vuex);

    const Store = new Vuex.Store<StateInterface>({

        state: {

            idUser: 0,
            typeUser: "professor",

            administradores: [],
            professores: [],
            alunos: [],

            provas: [],
            trabalhos: [],
            atividades: [],
            aulas: [],

            days: [],
            allDays: [],

            materias: [],
            materiasProfessor: [],

            turmas: [],

        }

    });

    return Store;
    
});