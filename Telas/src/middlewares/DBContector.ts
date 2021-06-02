import { AxiosInstance } from 'axios'
import { Administrador, Aluno, Arquivo, Atividade, Aula, Discord, Materia, NotaProva, Professor, Prova, Questao, QuestaoAlternativa, QuestaoCorreta, Resposta, Trabalho, Turma } from '../@types/DB'
import { Store } from 'vuex'
import { StateInterface } from 'src/store'

const testArquivo: Arquivo[] = [

    {

        idArquivo: 0,
        link: "linkbakana.com"

    },
    {

        idArquivo: 1,
        link: "linkblz.com"

    }

]

const testDiscord: Discord[] = [

    {

        idDiscord: 0,
        notificacoes: "",
        provasTrabalhos: "",

    },
    {

        idDiscord: 1,
        notificacoes: "",
        provasTrabalhos: "",

    },
    {

        idDiscord: 2,
        notificacoes: "",
        provasTrabalhos: "",

    },


]

const testMateria: Materia[] = [

    {

        idMateria: 0,
        nome: "Historia"

    },
    {

        idMateria: 1,
        nome: "Protugues"

    },
    {

        idMateria: 2,
        nome: "Historia"

    }

]

const testProfessor: Professor[] = [

    {

        idProfessor: 0,
        email: "remor.oliveira@gmail.com.br",
        matricula: "49857848984",
        nome: "Remor Oliveira",
        senha: "1234",
        materia: [testMateria[0], testMateria[1]]

    }

]

const testAdministrador: Administrador[] = [

    {

        idAdministrador: 0,
        email: "remor.oliveira@gmail.com.br",
        nome: "Remor Oliveira",
        senha: "1234",
        login: "remor.oliveira@gmail.com.br"

    }

]

const testQuestaoAlternativa: QuestaoAlternativa[] = [

    {

        idQuestaoAlternativa: 0,
        resposta: "95"

    },
    {

        idQuestaoAlternativa: 1,
        resposta: "1"

    },
    {

        idQuestaoAlternativa: 2,
        resposta: "1+1"

    },
    {

        idQuestaoAlternativa: 3,
        resposta: "Bola"

    },
    {

        idQuestaoAlternativa: 4,
        resposta: "Arroz"

    },
    {

        idQuestaoAlternativa: 5,
        resposta: "Maça"

    },

]

const testQuestaoCorreta: QuestaoCorreta[] = [

    {

        idQuestaoCorreta: 0,
        questaoAlternativa: [testQuestaoAlternativa[2]]

    },
    {

        idQuestaoCorreta: 1,
        questaoAlternativa: [testQuestaoAlternativa[4], testQuestaoAlternativa[5]]

    }

]

const testQuestao: Questao[] = [

    {

        idQuestao: 0,
        pergunta: "Quanto é 1+1?",
        questaoAlternativa: [testQuestaoAlternativa[0], testQuestaoAlternativa[1], testQuestaoAlternativa[2]],
        questaoCorreta: [testQuestaoCorreta[0]],
        tipo: 1,

    },
    {

        idQuestao: 1,
        pergunta: "Selecione o que n é 'Bola'",
        questaoAlternativa: [testQuestaoAlternativa[3], testQuestaoAlternativa[4], testQuestaoAlternativa[5]],
        questaoCorreta: [testQuestaoCorreta[1]],
        tipo: 1,

    }

]

const testResposta: Resposta[] = [

    {

        idResposta: 0,
        nroTentativa: 1,
        questao: testQuestao[0],
        resposta: "1",
        idAluno: 0

    },
    {

        idResposta: 1,
        nroTentativa: 3,
        questao: testQuestao[1],
        resposta: "1",
        idAluno: 1

    },

]

const testProva: Prova[] = [

    {

        idProva: 0,
        nome: "Prova Legal",
        prazo: new Date(),
        arquivo: [],
        descricao: "É legal mesmo",
        inicio: new Date(),
        questao: [testQuestao[0]],
        tempo: 9000,
        tentativas: 3,
        administrador: [],
        professor: [testProfessor[0]],
        resposta: [testResposta[0]],
        materia: testMateria[0]

    },
    {

        idProva: 1,
        nome: "Prova Legal 2",
        prazo: new Date(),
        arquivo: [],
        descricao: "É legal mesmo x2",
        inicio: new Date(),
        questao: [testQuestao[1]],
        tempo: 18000,
        tentativas: 1,
        professor: [],
        administrador: [testAdministrador[0]],
        resposta: [testResposta[1]],
        materia: testMateria[1]

    }

]

const testNotaProva: NotaProva[] = [

    {

        idNotaProva: 0,
        prova: testProva[0],
        valor: 10

    },
    {

        idNotaProva: 1,
        prova: testProva[1],
        valor: 4

    }

]

const testTrabalho: Trabalho[] = [

    {

        idTrabalho: 0,
        administrador: [],
        arquivo: [],
        descricao: "Trabalho Legal",
        inicio: new Date(),
        nome: "Trabalho Legal",
        prazo: new Date(),
        professor: [testProfessor[0]],
        questao: [],
        tempo: 9000,
        tentativas: 0,
        tipo: 4,
        materia: testMateria[1],
        resposta: []

    }

]

const testAula: Aula[] = [

    {

        idAula: 0,
        arquivo: [testArquivo[0]],
        descricao: "AAAAAAAAA",
        inicio: new Date(),
        link: "aaaa.com",
        materia: testMateria[0],
        nome: "A",

    }

]

const testAtividade: Atividade[] = [

    {

        idAtividade: 0,
        administrador: [],
        arquivo: [testArquivo[1]],
        descricao: "Opa",
        inicio: new Date(),
        nome: "Eba",
        professor: [testProfessor[0]],
        resposta: [],
        materia: testMateria[0]

    }

]


const testTurma: Turma[] = [

    {

        idTurma: 0,
        atividade: [testAtividade[0]],
        discord: testDiscord[0],
        materia: testMateria[2],
        nome: "Turminha do Barrulho",
        prova: [testProva[0], testProva[1]],
        trabalho: [testTrabalho[0]],
        aula: [testAula[0]],
        professor: [testProfessor[0]]

    }

]

const testAluno: Aluno[] = [

    {

        idAluno: 0,
        email: "emanuel.scherer@meta.com.br",
        matricula: "01785infq",
        nome: "Emanuel Scherer",
        notasProvas: [testNotaProva[0]],
        notasTrabalhos: [],
        senha: "abc",
        turma: [testTurma[0]]

    },
    {

        idAluno: 1,
        email: "vitor.nascimento@gmail.com",
        matricula: "20589infq",
        nome: "Vitor Nascimento",
        notasProvas: [testNotaProva[1]],
        notasTrabalhos: [],
        senha: "opa123",
        turma: [testTurma[0]]

    },
    {

        idAluno: 2,
        email: "remor.oliveira@ifsul.edu.br",
        matricula: "321361695",
        nome: "Remor Oliveira",
        notasProvas: [],
        notasTrabalhos: [],
        senha: "0987654321",
        turma: [testTurma[0]]

    }

]

export class DB {

    private baseUrl = "localhost:3000"
    private axios: AxiosInstance
    private store: Store<StateInterface>

    constructor(axios: AxiosInstance, store: Store<StateInterface>) {

        this.axios = axios
        this.store = store

    }

    dias = {

        get: () => {}

    }

    aluno = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/aluno/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    //ADD ALGUMA MANIPULACAO PARA ALUNO + ADD NO VUEX

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/alunos`,
                    method: "GET"

                }).then(r => {

                    //ADD ALGUMA MANIPULACAO PARA ALUNO + ADD NO VUEX

                })

            }

        },

        create: async (aluno: Aluno) => {

            await this.axios({

                url: `${this.baseUrl}/aluno`,
                method: "POST",
                data: aluno

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ALUNO + ADD NO VUEX

            })

        },

        update: async (id: string, aluno: Aluno) => {

            await this.axios({

                url: `${this.baseUrl}/aluno/${id}`,
                method: "PUT",
                data: aluno

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ALUNO + ADD NO VUEX

            })

        },

        delete: async (id: string) => {

            await this.axios({

                url: `${this.baseUrl}/aluno/${id}`,
                method: "DELETE"

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ALUNO + ADD NO VUEX

            })

        }

    }

    professor = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/professor/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    //ADD ALGUMA MANIPULACAO PARA PROFESSOR + ADD NO VUEX

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/professores`,
                    method: "GET"

                }).then(r => {

                    //ADD ALGUMA MANIPULACAO PARA PROFESSOR + ADD NO VUEX

                })

            }

        },

        create: async (professor: Professor) => {

            await this.axios({

                url: `${this.baseUrl}/professor`,
                method: "POST",
                data: professor

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA PROFESSOR + ADD NO VUEX

            })

        },

        update: async (id: string, professor: Professor) => {

            await this.axios({

                url: `${this.baseUrl}/professor/${id}`,
                method: "PUT",
                data: professor

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA PROFESSOR + ADD NO VUEX

            })

        },

        delete: async (id: string) => {

            await this.axios({

                url: `${this.baseUrl}/professor/${id}`,
                method: "DELETE"

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA PROFESSOR + ADD NO VUEX

            })

        }

    }

    administrador = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/administrador/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/administradores`,
                    method: "GET"

                }).then(r => {

                    //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

                })

            }

        },

        create: async (administrador: Administrador) => {

            await this.axios({

                url: `${this.baseUrl}/administrador`,
                method: "POST",
                data: administrador

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        },

        update: async (id: string, administrador: Administrador) => {

            await this.axios({

                url: `${this.baseUrl}/administrador/${id}`,
                method: "PUT",
                data: administrador

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        },

        delete: async (id: string) => {

            await this.axios({

                url: `${this.baseUrl}/administrador/${id}`,
                method: "DELETE"

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        }

    }

    aula = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/aula/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    //ADD ALGUMA MANIPULACAO PARA AULA + ADD NO VUEX

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/aulas`,
                    method: "GET"

                }).then(r => {

                    //ADD ALGUMA MANIPULACAO PARA AULA + ADD NO VUEX

                })

            }

        },

        create: async (administrador: Administrador) => {

            await this.axios({

                url: `${this.baseUrl}/aula`,
                method: "POST",
                data: administrador

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        },

        update: async (id: string, administrador: Administrador) => {

            await this.axios({

                url: `${this.baseUrl}/administrador/${id}`,
                method: "PUT",
                data: administrador

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        },

        delete: async (id: string) => {

            await this.axios({

                url: `${this.baseUrl}/administrador/${id}`,
                method: "DELETE"

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        }

    }

}