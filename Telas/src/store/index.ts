import { store } from 'quasar/wrappers';
import { Materia } from 'src/@types/DB';
import Vuex from 'vuex';

export interface StateInterface {

    idUser: number,
    typeUser: "Aluno" | "Professor" | "Administrador",
    materias: Materia[],
    materiasProfessor: Materia[]
}

export default store(function ({ Vue }) {
    Vue.use(Vuex);

    const Store = new Vuex.Store<StateInterface>({
        
        state: {

            idUser: 123,
            typeUser: "Professor",
            materias: [{id: 95, name: "Historia"}, {id: 12, name: "Ingles"}],
            materiasProfessor: [{id: 95, name: "Historia"}, {id: 12, name: "Ingles"}]

        }

    });

    return Store;
});
