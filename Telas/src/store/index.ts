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

    const Store = new Vuex.Store<StateInterface>({
        
        state: {

            idUser: 123,
            typeUser: "Professor",

            materias: [{id: 95, name: "Historia", discord: { notificacao: "", trabalhos_provas: "" }}, {id: 12, name: "Ingles", discord: { notificacao: "", trabalhos_provas: "" }}],
            materiasProfessor: [{id: 95, name: "Historia", discord: { notificacao: "", trabalhos_provas: "" }}, {id: 12, name: "Ingles", discord: { notificacao: "", trabalhos_provas: "" }}],

            days: [
        
                {
        
                    day: "06/05",
                    aulas: [
        
                        {
        
                            dia: new Date,
                            name: "Opa",
                            materia: {id: 132, name: "aaa", discord: { notificacao: "", trabalhos_provas: "" }},
                            id: 123,
                            descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
        
                        }
        
                    ],
                    trabalhos: [
        
                        {
        
                            name: "Opa",
                            materia: {id: 132, name: "aaa", discord: { notificacao: "", trabalhos_provas: "" }},
                            id: 123,
                            descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            start: new Date,
                            finish: new Date,
                            maxTentativas: 95,
                            maxTempo: 0,
                            questoes: [{id: 1298, question: "Quanto é 1+1?", alternativas: [{id: 0, resposta: "95"}, {id: 12, resposta: "1"}, {id: 5, resposta: "1+1"}], correta: [5], type: 0}],
                            respostas: [
        
                                { id: 0, aluno: { id: 1, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
                                { id: 1, aluno: { id: 2, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 1, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
                                { id: 2, aluno: { id: 3, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 2, resposta: "e" }, resposta: "opa", num_tentativa: 2 },
                                { id: 3, aluno: { id: 4, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 3, resposta: "e" }, resposta: "opa", num_tentativa: 2 },
                                { id: 4, aluno: { id: 5, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 4, resposta: "e" }, resposta: "opa", num_tentativa: 2 },
                                { id: 5, aluno: { id: 6, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 5, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
                                { id: 6, aluno: { id: 7, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 6, resposta: "e" }, resposta: "opa", num_tentativa: 3 },
                                { id: 7, aluno: { id: 98, nome: "3", matricula: "aasdasd22", senha: 'oaisjdasoidj', email: "a89sd498", notasTrabalhos: [], notasProvas: [] }, questao: { id: 1, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
        
                            ],
        
                        }
        
                    ],
                    atividades: [
        
                        {
                        
                            dia: new Date,
                            name: "Opa",
                            materia: {id: 132, name: "aaa", discord: { notificacao: "", trabalhos_provas: "" }},
                            id: 123,
                            descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        
                        }
        
                    ],
                    provas: [
        
                        {
        
                            name: "Opa",
                            materia: {id: 132, name: "aaa", discord: { notificacao: "", trabalhos_provas: "" }},
                            id: 123,
                            descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            start: new Date,
                            finish: new Date,
                            maxTentativas: 95,
                            maxTempo: 9223372036854775807,
                            questoes: [{id: 1298, question: "Quanto é 1+1?", alternativas: [{id: 0, resposta: "95"}, {id: 12, resposta: "1"}, {id: 5, resposta: "1+1"}], correta: [5], type: 0}],
                            tentativas: [ {id: 0, aluno: { id: 0, nome: "opa", matricula: "aa", senha: "aosiduaosd", email: "awseaee", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "opa" }, resposta: "opa", num_tentativa: 1} ],
        
                        }
        
                    ],
                    id: 1
        
                },
                {
        
                    day: "06/05",
                    aulas: [
        
                        {
        
                            dia: new Date,
                            name: "Opa",
                            materia: {id: 132, name: "aaa", discord: { notificacao: "", trabalhos_provas: "" }},
                            id: 123,
                            descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
        
                        }
        
                    ],
                    trabalhos: [
        
                        {
        
                            name: "Opa",
                            materia: {id: 132, name: "aaa", discord: { notificacao: "", trabalhos_provas: "" }},
                            id: 123,
                            descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            start: new Date,
                            finish: new Date,
                            maxTentativas: 95,
                            maxTempo: 1000000000000,
                            questoes: [{id: 0, question: "Quanto é 1+1?", alternativas: [{id: 0, resposta: "95"}, {id: 12, resposta: "1"}, {id: 5, resposta: "1+1"}], correta: [5], type: 1}],
                            respostas: [
        
                                { id: 0, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 0 },
                                { id: 1, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 0 },
                                { id: 2, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
                                { id: 3, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
                                { id: 4, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 0 },
                                { id: 5, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 3 },
                                { id: 6, aluno: { id: 98, nome: "3", matricula: "aasdasd22", senha: 'oaisjdasoidj', email: "a89sd498", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
        
                            ],
        
                        }
        
                    ],
                    atividades: [
        
                        {
                        
                            dia: new Date,
                            name: "Opa",
                            materia: {id: 132, name: "aaa", discord: { notificacao: "", trabalhos_provas: "" }},
                            id: 123,
                            descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        
                        }
        
                    ],
                    provas: [
        
                        {
        
                            name: "Opa",
                            materia: {id: 132, name: "aaa", discord: { notificacao: "", trabalhos_provas: "" }},
                            id: 123,
                            descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            start: new Date,
                            finish: new Date,
                            maxTentativas: 95,
                            maxTempo: 1000000000000,
                            questoes: [{id: 0, question: "Quanto é 1+1?", alternativas: [{id: 0, resposta: "95"}, {id: 12, resposta: "1"}, {id: 5, resposta: "1+1"}], correta: [5], type: 1}],
                            tentativas: [{ id: 0, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 0 }],
        
                        }
        
                    ],
                    id: 99
        
                },
                {
        
                    day: "11/05",
                    provas: [],
                    aulas: [],
                    trabalhos: [],
                    atividades: [],
                    id: 956146879879
        
                },
        
            ]

        }

    });

    return Store;
});
