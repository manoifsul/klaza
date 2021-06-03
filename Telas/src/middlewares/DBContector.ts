import { AxiosInstance } from 'axios'
import { Administrador, Aluno, Arquivo, Atividade, Aula, Discord, Materia, NotaProva, Professor, Prova, Questao, QuestaoAlternativa, QuestaoCorreta, Resposta, Trabalho, Turma } from '../@types/DB'
import { Store } from 'vuex'
import { StateInterface } from 'src/store'
import { DayCardType } from 'src/components/models'

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
        materia: [testMateria[0], testMateria[1]],
        turmas: [0]

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
        resposta: [],
        idTurma: 0

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
        idTurma: 0

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
        materia: testMateria[0],
        idTurma: 0

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
        idTurmas: [0]

    },
    {

        idAluno: 1,
        email: "vitor.nascimento@gmail.com",
        matricula: "20589infq",
        nome: "Vitor Nascimento",
        notasProvas: [testNotaProva[1]],
        notasTrabalhos: [],
        senha: "opa123",
        idTurmas: [0]

    },
    {

        idAluno: 2,
        email: "remor.oliveira@ifsul.edu.br",
        matricula: "321361695",
        nome: "Remor Oliveira",
        notasProvas: [],
        notasTrabalhos: [],
        senha: "0987654321",
        idTurmas: [0]

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
        professor: [testProfessor[0]],
        aluno: testAluno

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

        get: async () => {

            this.turma.get()

            const days: DayCardType[] = []

            if (this.store.state.typeUser == "aluno") {

                const turmasAluno = this.store.state.turmas.filter(t => t.aluno.map(a => a.idAluno).includes(this.store.state.idUser))


            }




            this.store.state.days = days

        }

    }

    aluno = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/aluno/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const aluno: Aluno = testAluno[id] //r.data

                    const i = this.store.state.alunos.findIndex(a => a.idAluno == id)

                    if (i != -1) { this.store.state.alunos[i] = aluno }
                    else { this.store.state.alunos.push(aluno) }

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/alunos`,
                    method: "GET"

                }).then(r => {

                    const aluno: Aluno[] = testAluno //r.data

                    this.store.state.alunos = aluno

                })

            }

        },

        create: async (aluno: Aluno) => {

            await this.axios({

                url: `${this.baseUrl}/aluno`,
                method: "POST",
                data: aluno

            }).then(r => {
                
                const i = this.store.state.alunos.findIndex(a => a.idAluno == aluno.idAluno)

                if (i != -1) { this.store.state.alunos[i] = aluno }
                else { this.store.state.alunos.push(aluno) }

            })

        },

        update: async (aluno: Aluno) => {

            await this.axios({

                url: `${this.baseUrl}/aluno/${aluno.idAluno}`,
                method: "PUT",
                data: aluno

            }).then(r => {
                
                const i = this.store.state.alunos.findIndex(a => a.idAluno == aluno.idAluno)

                if (i != -1) { this.store.state.alunos[i] = aluno }
                else { this.store.state.alunos.push(aluno) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/aluno/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.alunos.findIndex(a => a.idAluno == id)

                if (i != -1) { this.store.state.alunos.splice(i, 1) }

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
                    
                    const professor: Professor = testProfessor[id] //r.data

                    const i = this.store.state.professores.findIndex(a => a.idProfessor == id)

                    if (i != -1) { this.store.state.professores[i] = professor }
                    else { this.store.state.professores.push(professor) }

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/professores`,
                    method: "GET"

                }).then(r => {

                    const professores: Professor[] = testProfessor //r.data

                    this.store.state.professores = professores

                })

            }

        },

        create: async (professor: Professor) => {

            await this.axios({

                url: `${this.baseUrl}/professor`,
                method: "POST",
                data: professor

            }).then(r => {

                const i = this.store.state.professores.findIndex(a => a.idProfessor == professor.idProfessor)

                if (i != -1) { this.store.state.professores[i] = professor }
                else { this.store.state.professores.push(professor) }

            })

        },

        update: async (professor: Professor) => {

            await this.axios({

                url: `${this.baseUrl}/professor/${professor.idProfessor}`,
                method: "PUT",
                data: professor

            }).then(r => {
                
                const i = this.store.state.professores.findIndex(a => a.idProfessor == professor.idProfessor)

                if (i != -1) { this.store.state.professores[i] = professor }
                else { this.store.state.professores.push(professor) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/professor/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.professores.findIndex(a => a.idProfessor == id)

                if (i != -1) { this.store.state.professores.splice(i, 1) }

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
                    
                    const administrador: Administrador = testAdministrador[id] //r.data

                    const i = this.store.state.administradores.findIndex(a => a.idAdministrador == id)

                    if (i != -1) { this.store.state.administradores[i] = administrador }
                    else { this.store.state.administradores.push(administrador) }

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/administradores`,
                    method: "GET"

                }).then(r => {

                    const administradores: Administrador[] = testAdministrador //r.data

                    this.store.state.administradores = administradores

                })

            }

        },

        create: async (administrador: Administrador) => {

            await this.axios({

                url: `${this.baseUrl}/administrador`,
                method: "POST",
                data: administrador

            }).then(r => {
                
                const i = this.store.state.administradores.findIndex(a => a.idAdministrador == administrador.idAdministrador)

                if (i != -1) { this.store.state.administradores[i] = administrador }
                else { this.store.state.administradores.push(administrador) }

            })

        },

        update: async (administrador: Administrador) => {

            await this.axios({

                url: `${this.baseUrl}/administrador/${administrador.idAdministrador}`,
                method: "PUT",
                data: administrador

            }).then(r => {
                
                const i = this.store.state.administradores.findIndex(a => a.idAdministrador == administrador.idAdministrador)

                if (i != -1) { this.store.state.administradores[i] = administrador }
                else { this.store.state.administradores.push(administrador) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/administrador/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.administradores.findIndex(a => a.idAdministrador == id)

                if (i != -1) { this.store.state.administradores.splice(i, 1) }

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
                    
                    const aula: Aula = testAula[id] //r.data

                    const i = this.store.state.aulas.findIndex(a => a.idAula == id)

                    if (i != -1) { this.store.state.aulas[i] = aula }
                    else { this.store.state.aulas.push(aula) }

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/aulas`,
                    method: "GET"

                }).then(r => {

                    const aulas: Aula[] = testAula //r.data

                    this.store.state.aulas = aulas

                })

            }

        },

        create: async (aula: Aula) => {

            await this.axios({

                url: `${this.baseUrl}/aula`,
                method: "POST",
                data: aula

            }).then(r => {
                
                const i = this.store.state.aulas.findIndex(a => a.idAula == aula.idAula)

                if (i != -1) { this.store.state.aulas[i] = aula }
                else { this.store.state.aulas.push(aula) }

            })

        },

        update: async (aula: Aula) => {

            await this.axios({

                url: `${this.baseUrl}/aula/${aula.idAula}`,
                method: "PUT",
                data: aula

            }).then(r => {
                
                const i = this.store.state.aulas.findIndex(a => a.idAula == aula.idAula)

                if (i != -1) { this.store.state.aulas[i] = aula }
                else { this.store.state.aulas.push(aula) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/aula/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.aulas.findIndex(a => a.idAula == id)

                if (i != -1) { this.store.state.aulas.splice(i, 1) }

            })

        }

    }

    atividade = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/atividade/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const atividade: Atividade = testAtividade[id] //r.data

                    const i = this.store.state.atividades.findIndex(a => a.idAtividade == id)

                    if (i != -1) { this.store.state.atividades[i] = atividade }
                    else { this.store.state.atividades.push(atividade) }

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/atividades`,
                    method: "GET"

                }).then(r => {

                    const atividades: Atividade[] = testAtividade //r.data

                    this.store.state.atividades = atividades

                })

            }

        },

        create: async (aula: Aula) => {

            await this.axios({

                url: `${this.baseUrl}/aula`,
                method: "POST",
                data: aula

            }).then(r => {
                
                const i = this.store.state.aulas.findIndex(a => a.idAula == aula.idAula)

                if (i != -1) { this.store.state.aulas[i] = aula }
                else { this.store.state.aulas.push(aula) }

            })

        },

        update: async (aula: Aula) => {

            await this.axios({

                url: `${this.baseUrl}/aula/${aula.idAula}`,
                method: "PUT",
                data: aula

            }).then(r => {
                
                const atividade: Atividade = testAtividade[aula.idAula] //r.data

                const i = this.store.state.atividades.findIndex(a => a.idAtividade == aula.idAula)

                if (i != -1) { this.store.state.atividades[i] = atividade }
                else { this.store.state.atividades.push(atividade) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/aula/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.aulas.findIndex(a => a.idAula == id)

                if (i != -1) { this.store.state.aulas.splice(i, 1) }

            })

        }

    }

    trabalho = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/trabalho/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const trabalho: Trabalho = testTrabalho[id] //r.data

                    const i = this.store.state.trabalhos.findIndex(a => a.idTrabalho == id)

                    if (i != -1) { this.store.state.trabalhos[i] = trabalho }
                    else { this.store.state.trabalhos.push(trabalho) }

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/trabalhos`,
                    method: "GET"

                }).then(r => {

                    const trabalhos: Trabalho[] = testTrabalho //r.data

                    this.store.state.trabalhos = trabalhos

                })

            }

        },

        create: async (trabalho: Trabalho) => {

            await this.axios({

                url: `${this.baseUrl}/trabalho`,
                method: "POST",
                data: trabalho

            }).then(r => {
                
                const i = this.store.state.trabalhos.findIndex(a => a.idTrabalho == trabalho.idTrabalho)

                if (i != -1) { this.store.state.trabalhos[i] = trabalho }
                else { this.store.state.trabalhos.push(trabalho) }

            })

        },

        update: async (trabalho: Trabalho) => {

            await this.axios({

                url: `${this.baseUrl}/trabalho/${trabalho.idTrabalho}`,
                method: "PUT",
                data: trabalho

            }).then(r => {

                const i = this.store.state.trabalhos.findIndex(a => a.idTrabalho == trabalho.idTrabalho)

                if (i != -1) { this.store.state.trabalhos[i] = trabalho }
                else { this.store.state.trabalhos.push(trabalho) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/trabalho/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.trabalhos.findIndex(a => a.idTrabalho == id)

                if (i != -1) { this.store.state.trabalhos.splice(i, 1) }

            })

        }

    }

    prova = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/prova/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const prova: Prova = testProva[id] //r.data

                    const i = this.store.state.provas.findIndex(a => a.idProva == id)

                    if (i != -1) { this.store.state.provas[i] = prova }
                    else { this.store.state.provas.push(prova) }

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/provas`,
                    method: "GET"

                }).then(r => {

                    const provas: Prova[] = testProva //r.data

                    this.store.state.provas = provas

                })

            }

        },

        create: async (prova: Prova) => {

            await this.axios({

                url: `${this.baseUrl}/prova`,
                method: "POST",
                data: prova

            }).then(r => {
                
                const i = this.store.state.provas.findIndex(a => a.idProva == prova.idProva)

                if (i != -1) { this.store.state.provas[i] = prova }
                else { this.store.state.provas.push(prova) }

            })

        },

        update: async (prova: Prova) => {

            await this.axios({

                url: `${this.baseUrl}/trabalho/${prova.idProva}`,
                method: "PUT",
                data: prova

            }).then(r => {

                const i = this.store.state.provas.findIndex(a => a.idProva == prova.idProva)

                if (i != -1) { this.store.state.provas[i] = prova }
                else { this.store.state.provas.push(prova) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/prova/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.provas.findIndex(a => a.idProva == id)

                if (i != -1) { this.store.state.provas.splice(i, 1) }

            })

        }

    }

    turma = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/turma/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const turma: Turma = testTurma[id] //r.data

                    const i = this.store.state.turmas.findIndex(a => a.idTurma == id)

                    if (i != -1) { this.store.state.turmas[i] = turma }
                    else { this.store.state.turmas.push(turma) }

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/turmas`,
                    method: "GET"

                }).then(r => {

                    const turmas: Turma[] = testTurma //r.data

                    this.store.state.turmas = turmas

                })

            }

        },

        create: async (prova: Prova) => {

            await this.axios({

                url: `${this.baseUrl}/prova`,
                method: "POST",
                data: prova

            }).then(r => {
                
                const i = this.store.state.provas.findIndex(a => a.idProva == prova.idProva)

                if (i != -1) { this.store.state.provas[i] = prova }
                else { this.store.state.provas.push(prova) }

            })

        },

        update: async (prova: Prova) => {

            await this.axios({

                url: `${this.baseUrl}/trabalho/${prova.idProva}`,
                method: "PUT",
                data: prova

            }).then(r => {

                const i = this.store.state.provas.findIndex(a => a.idProva == prova.idProva)

                if (i != -1) { this.store.state.provas[i] = prova }
                else { this.store.state.provas.push(prova) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/prova/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.provas.findIndex(a => a.idProva == id)

                if (i != -1) { this.store.state.provas.splice(i, 1) }

            })

        }

    }

    login = (tipo: "aluno" | "professor" | "administrador", login: string, senha: string) => {

        this.axios({

            url: `${this.baseUrl}/login`,
            method: 'POST',
            data: { login: login, senha: senha, tipo: tipo }

        }).then(r => {

            if (tipo == "aluno") {

                const aluno: Aluno = testAluno[0] //r.data

                const i = this.store.state.alunos.findIndex(a => a.idAluno == aluno.idAluno)

                this.store.state.idUser = aluno.idAluno
                this.store.state.typeUser = tipo

                if (i != -1) { this.store.state.alunos[i] = aluno }
                else { this.store.state.alunos.push(aluno) }

            }

            if (tipo == "professor") {

                const professor: Professor = testProfessor[0] //r.data

                const i = this.store.state.professores.findIndex(p => p.idProfessor == professor.idProfessor)

                this.store.state.idUser = professor.idProfessor
                this.store.state.typeUser = tipo

                if (i != -1) { this.store.state.professores[i] = professor }
                else { this.store.state.professores.push(professor) }

            }

            if (tipo == "administrador") {

                const administrador: Administrador = testAdministrador[0] //r.data

                const i = this.store.state.administradores.findIndex(a => a.idAdministrador == administrador.idAdministrador)

                this.store.state.idUser = administrador.idAdministrador
                this.store.state.typeUser = tipo

                if (i != -1) { this.store.state.administradores[i] = administrador }
                else { this.store.state.administradores.push(administrador) }

            }

        })

    }

}