import { store } from 'quasar/wrappers';
import { Materia } from 'src/@types/DB';
import { DayCardType } from 'src/components/models';
import Vuex from 'vuex';

export interface StateInterface {

    idUser: number,
    typeUser: "Aluno" | "Professor" | "Administrador",

    materias: Materia[],
    materiasProfessor: Materia[]

    days: DayCardType[]

}

export default store(function ({ Vue }) {
    Vue.use(Vuex);

    const Store = new Vuex.Store<StateInterface>({});

    return Store;
});
