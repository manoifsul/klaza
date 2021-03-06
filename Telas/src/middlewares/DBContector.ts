import { AxiosInstance } from 'axios'
import { Administrador, Aluno, Arquivo, Atividade, Aula, Discord, Materia, NotaProva, NotaTrabalho, Professor, Prova, Questao, QuestaoAlternativa, QuestaoCorreta, Resposta, Trabalho, Turma } from '../@types/DB'
import { Store } from 'vuex'
import { StateInterface } from 'src/store'
import { DayCardType } from 'src/components/models'
import moment from 'moment'
import { uid } from 'quasar'


// ESSAS VARIAVIES SÃO SÓ PARA TESTE, FORAM USADAS QUANDO N TINHA SPRING

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
        nome: "Historia",
        idProfessor: [0],

    },
    {

        idMateria: 1,
        nome: "Protugues",
        idProfessor: [0],

    },
    {

        idMateria: 2,
        nome: "Geografia",
        idProfessor: [0],

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
        idTurmas: [0]

    }

]

const testAdministrador: Administrador[] = [

    {

        idAdministrador: 0,
        email: "remor.oliveira@gmail.com.br",
        nome: "Remor Oliveira",
        senha: "1234",
        login: "remor.oliveira@gmail.com.br"

    },
    {

        idAdministrador: 1,
        email: "emanuel@gmail.com.br",
        nome: "Emanuel",
        senha: "as65d4as6",
        login: "emanuel@gmail.com.br"

    },
    {

        idAdministrador: 2,
        email: "vitor@gmail.com.br",
        nome: "Vitor",
        senha: "eba",
        login: "vitor@gmail.com.br"

    },
    {

        idAdministrador: 3,
        email: "blz@gmail.com.br",
        nome: "Blz",
        senha: "kk",
        login: "blz@gmail.com.br"

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
        questaoCorreta: testQuestaoCorreta[0],
        tipo: 1,

    },
    {

        idQuestao: 1,
        pergunta: "Selecione o que n é 'Bola'",
        questaoAlternativa: [testQuestaoAlternativa[3], testQuestaoAlternativa[4], testQuestaoAlternativa[5]],
        questaoCorreta: testQuestaoCorreta[1],
        tipo: 2,

    },
    {

        idQuestao: 2,
        pergunta: "Blz?",
        questaoAlternativa: [],
        questaoCorreta: testQuestaoCorreta[1],
        tipo: 0,

    },
    {

        idQuestao: 3,
        pergunta: "Hehe",
        questaoAlternativa: [],
        questaoCorreta: { idQuestaoCorreta: -1, questaoAlternativa: [] },
        tipo: 3,

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
        nroTentativa: 1,
        questao: testQuestao[1],
        resposta: "4,5",
        idAluno: 0

    },
    {

        idResposta: 2,
        nroTentativa: 1,
        questao: testQuestao[2],
        resposta: "Guoba",
        idAluno: 0

    },
    {

        idResposta: 3,
        nroTentativa: 1,
        questao: testQuestao[3],
        resposta: "https://link.com",
        idAluno: 0

    },
    {

        idResposta: 4,
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
        resposta: testResposta,
        materia: testMateria[2],
        idTurma: 0

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
        materia: testMateria[1],
        idTurma: 1

    }

]

const testNotaProva: NotaProva[] = [

    {

        idNotaProva: 0,
        prova: testProva[0],
        valor: 10,
        idAluno: 0,

    },
    {

        idNotaProva: 1,
        prova: testProva[1],
        valor: 4,
        idAluno: 1

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
        questao: testQuestao,
        tempo: 9000,
        tentativas: 0,
        tipo: 4,
        materia: testMateria[2],
        resposta: testResposta,
        idTurma: 0

    }

]

const testAula: Aula[] = [

    {

        idAula: 0,
        arquivo: [testArquivo[0]],
        descricao: "AAAAAAAAA",
        inicio: moment("06/02/2021").toDate(),
        link: "aaaa.com",
        materia: testMateria[0],
        nome: "A",
        idTurma: 0

    },
    {

        idAula: 1,
        arquivo: [testArquivo[0]],
        descricao: "AAAAAAAAA",
        inicio: moment("06/10/2021").toDate(),
        link: "aaaa.com",
        materia: testMateria[0],
        nome: "A",
        idTurma: 0

    },
    {

        idAula: 2,
        arquivo: [testArquivo[0]],
        descricao: "AAAAAAAAA",
        inicio: moment("06/01/2021").toDate(),
        link: "aaaa.com",
        materia: testMateria[0],
        nome: "A",
        idTurma: 0

    },
    {

        idAula: 3,
        arquivo: [],
        descricao: "AAAAAAAAA",
        inicio: moment("06/10/2021").toDate(),
        link: "aaaa.com",
        materia: testMateria[0],
        nome: "A",
        idTurma: 0

    },
    {

        idAula: 4,
        arquivo: [],
        descricao: "AAAAAAAAA",
        inicio: moment("05/02/2021").toDate(),
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
        materia: testMateria[0],
        idTurma: 0

    }

]

const testNotaTrabalho: NotaTrabalho[] = [

    {

        idNotaTrabalho: 0,
        trabalho: testTrabalho[0],
        valor: 10,
        idAluno: 0,

    },
    {

        idNotaTrabalho: 1,
        trabalho: testTrabalho[1],
        valor: 4,
        idAluno: 1

    }

]

const testAluno: Aluno[] = [

    {

        idAluno: 0,
        email: "emanuel.scherer@meta.com.br",
        matricula: "01785infq",
        nome: "Emanuel Scherer",
        notasProvas: [testNotaProva[0]],
        notasTrabalhos: [testNotaTrabalho[0]],
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
        idTurmas: [1]

    },
    {

        idAluno: 3,
        email: "remor.oliveira@ifsul.edu.br",
        matricula: "321361695",
        nome: "Remor Oliveira",
        notasProvas: [],
        notasTrabalhos: [],
        senha: "0987654321",
        idTurmas: [1]

    },
    {

        idAluno: 4,
        email: "remor.oliveira@ifsul.edu.br",
        matricula: "321361695",
        nome: "Remor Oliveira",
        notasProvas: [],
        notasTrabalhos: [],
        senha: "0987654321",
        idTurmas: [1]

    },
    {

        idAluno: 5,
        email: "remor.oliveira@ifsul.edu.br",
        matricula: "321361695",
        nome: "Remor Oliveira",
        notasProvas: [],
        notasTrabalhos: [],
        senha: "0987654321",
        idTurmas: [1]

    },
    {

        idAluno: 6,
        email: "remor.oliveira@ifsul.edu.br",
        matricula: "321361695",
        nome: "Remor Oliveira",
        notasProvas: [],
        notasTrabalhos: [],
        senha: "0987654321",
        idTurmas: [1]

    },
    {

        idAluno: 7,
        email: "remor.oliveira@ifsul.edu.br",
        matricula: "321361695",
        nome: "Remor Oliveira",
        notasProvas: [],
        notasTrabalhos: [],
        senha: "0987654321",
        idTurmas: [1]

    },
    {

        idAluno: 8,
        email: "remor.oliveira@ifsul.edu.br",
        matricula: "321361695",
        nome: "Remor Oliveira",
        notasProvas: [],
        notasTrabalhos: [],
        senha: "0987654321",
        idTurmas: [1]

    },
    {

        idAluno: 9,
        email: "remor.oliveira@ifsul.edu.br",
        matricula: "321361695",
        nome: "Remor Oliveira",
        notasProvas: [],
        notasTrabalhos: [],
        senha: "0987654321",
        idTurmas: [1]

    },
    {

        idAluno: 10,
        email: "remor.oliveira@ifsul.edu.br",
        matricula: "321361695",
        nome: "Remor Oliveira",
        notasProvas: [],
        notasTrabalhos: [],
        senha: "0987654321",
        idTurmas: [1]

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
        aula: testAula,
        professor: [testProfessor[0]],
        aluno: testAluno

    },
    {

        idTurma: 1,
        atividade: [],
        discord: testDiscord[1],
        materia: testMateria[1],
        nome: "Turminhaaaaaaaa",
        prova: [],
        trabalho: [],
        aula: [],
        professor: [],
        aluno: [testAluno[2]]

    }

]

export class DB {

    private baseUrl = "/api"
    private axios: AxiosInstance
    private store: Store<StateInterface>

    constructor(axios: AxiosInstance, store: Store<StateInterface>) {

        this.axios = axios
        this.store = store

    }

    dias = {

        get: async () => {

            await this.turma.get()

            const days: DayCardType[] = []
            const allDays: DayCardType[] = []

            if (this.store.state.typeUser == "aluno") {

                const turmasAluno = this.store.state.turmas.filter(t => t.aluno.map(a => a.idAluno).includes(this.store.state.idUser))

                turmasAluno.forEach(t => {

                    new Set(t.aula.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const aulas = t.aula.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].aulas = aulas
                                allDays[i].aulas = aulas

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: aulas,
                                    atividades: [],
                                    trabalhos: [],
                                    provas: []
            
                                })

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: aulas,
                                    atividades: [],
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].aulas = aulas

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: aulas,
                                    atividades: [],
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }

                    })

                    new Set(t.atividade.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const atividades = t.atividade.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].atividades = atividades
                                allDays[i].atividades = atividades

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: atividades,
                                    trabalhos: [],
                                    provas: []
            
                                })

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: atividades,
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].atividades = atividades

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: atividades,
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }

                    })

                    new Set(t.trabalho.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const trabalhos = t.trabalho.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].trabalhos = trabalhos
                                allDays[i].trabalhos = trabalhos

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: trabalhos,
                                    provas: []
            
                                })

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: trabalhos,
                                    provas: []
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].trabalhos = trabalhos

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: trabalhos,
                                    provas: []
            
                                })

                            }

                        }

                    })

                    new Set(t.prova.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const provas = t.prova.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].provas = provas
                                allDays[i].provas = provas

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: [],
                                    provas: provas
            
                                })

                                
                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: [],
                                    provas: provas
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].provas = provas

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: [],
                                    provas: provas
            
                                })

                            }

                        }

                    })

                })

            }

            if (this.store.state.typeUser == "professor") {

                console.log(this.store.state.turmas)

                await this.materia.get()

                const turmasProfessor = this.store.state.turmas.filter(t => t.professor.map(a => a.idProfessor).includes(this.store.state.idUser))

                turmasProfessor.forEach(t => {

                    new Set(t.aula.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const aulas = t.aula.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].aulas = aulas
                                allDays[i].aulas = aulas

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: aulas,
                                    atividades: [],
                                    trabalhos: [],
                                    provas: []
            
                                })

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: aulas,
                                    atividades: [],
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].aulas = aulas

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: aulas,
                                    atividades: [],
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }

                    })

                    new Set(t.atividade.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const atividades = t.atividade.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].atividades = atividades
                                allDays[i].atividades = atividades

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: atividades,
                                    trabalhos: [],
                                    provas: []
            
                                })

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: atividades,
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].atividades = atividades

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: atividades,
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }

                    })

                    new Set(t.trabalho.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const trabalhos = t.trabalho.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].trabalhos = trabalhos
                                allDays[i].trabalhos = trabalhos

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: trabalhos,
                                    provas: []
            
                                })

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: trabalhos,
                                    provas: []
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].trabalhos = trabalhos

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: trabalhos,
                                    provas: []
            
                                })

                            }

                        }

                    })

                    new Set(t.prova.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const provas = t.prova.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].provas = provas
                                allDays[i].provas = provas

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: [],
                                    provas: provas
            
                                })

                                
                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: [],
                                    provas: provas
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].provas = provas

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: [],
                                    provas: provas
            
                                })

                            }

                        }

                    })

                })

            }

            if (this.store.state.typeUser == "administrador") {

                await this.materia.get()

                const turmasAdministrador = this.store.state.turmas

                turmasAdministrador.forEach(t => {

                    new Set(t.aula.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const aulas = t.aula.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].aulas = aulas
                                allDays[i].aulas = aulas

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: aulas,
                                    atividades: [],
                                    trabalhos: [],
                                    provas: []
            
                                })

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: aulas,
                                    atividades: [],
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].aulas = aulas

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: aulas,
                                    atividades: [],
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }

                    })

                    new Set(t.atividade.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const atividades = t.atividade.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].atividades = atividades
                                allDays[i].atividades = atividades

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: atividades,
                                    trabalhos: [],
                                    provas: []
            
                                })

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: atividades,
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].atividades = atividades

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: atividades,
                                    trabalhos: [],
                                    provas: []
            
                                })

                            }

                        }

                    })

                    new Set(t.trabalho.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const trabalhos = t.trabalho.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].trabalhos = trabalhos
                                allDays[i].trabalhos = trabalhos

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: trabalhos,
                                    provas: []
            
                                })

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: trabalhos,
                                    provas: []
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].trabalhos = trabalhos

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: trabalhos,
                                    provas: []
            
                                })

                            }

                        }

                    })

                    new Set(t.prova.map(a => a.inicio.getTime())).forEach(time => {

                        const date = moment(time).format("DD/MM/YYYY")
                        const i = days.findIndex(d => d.day == date)
                        const provas = t.prova.filter(a => a.inicio.getTime() == time)

                        if (moment(time).isSameOrAfter(moment(), 'D')) {

                            if (i != -1) {

                                days[i].provas = provas
                                allDays[i].provas = provas

                            }
                            else {

                                days.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: [],
                                    provas: provas
            
                                })

                                
                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: [],
                                    provas: provas
            
                                })

                            }

                        }
                        else {

                            const i = allDays.findIndex(d => d.day == date)

                            if (i != -1) {

                                allDays[i].provas = provas

                            }
                            else {

                                allDays.push({

                                    id: uid(),
                                    day: date,
                                    aulas: [],
                                    atividades: [],
                                    trabalhos: [],
                                    provas: provas
            
                                })

                            }

                        }

                    })

                })

            }

            this.store.state.days = days.sort((a, b) => {

                const splitA = a.day.split("/")
                const splitB = b.day.split("/")

                const momentA = moment(`${splitA[1]}/${splitA[0]}/${splitA[2]}`)
                const momentB = moment(`${splitB[1]}/${splitB[0]}/${splitA[2]}`)

                if (momentA.isBefore(momentB)) { return -1 }
                if (momentA.isSame(momentB)) { return 0 }
                if (momentA.isAfter(momentB)) { return 1 }

                return 0

            })

            this.store.state.allDays = allDays.sort((a, b) => {

                const splitA = a.day.split("/")
                const splitB = b.day.split("/")

                const momentA = moment(`${splitA[1]}/${splitA[0]}/${splitA[2]}`)
                const momentB = moment(`${splitB[1]}/${splitB[0]}/${splitA[2]}`)

                if (momentA.isBefore(momentB)) { return -1 }
                if (momentA.isSame(momentB)) { return 0 }
                if (momentA.isAfter(momentB)) { return 1 }

                return 0

            })

        }

    }

    aluno = {

        get: async (id?: number) => {
           
            if (id != undefined) {

                await this.axios({

                    url: `${this.baseUrl}/aluno/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const aluno: Aluno = r.data

                    const i = this.store.state.alunos.findIndex(a => a.idAluno == id)

                    if (i != -1) { this.store.state.alunos[i] = aluno }
                    else { this.store.state.alunos.push(aluno) }

                })

                // ISSO AKI É PARA TESTE

                // const aluno: Aluno = testAluno[id]

                // const i = this.store.state.alunos.findIndex(a => a.idAluno == id)

                // if (i != -1) { this.store.state.alunos[i] = aluno }
                // else { this.store.state.alunos.push(aluno) }

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/alunos`,
                    method: "GET"

                }).then(r => {

                    const aluno: Aluno[] = r.data

                    this.store.state.alunos = aluno

                })

                // ISSO AKI É PARA TESTE

                // const aluno: Aluno[] = testAluno

                // this.store.state.alunos = aluno

            }

        },

        create: (aluno: Aluno): Promise<Aluno> => {

            return new Promise(async resolve => {

                await this.axios({

                    url: `${this.baseUrl}/aluno`,
                    method: "POST",
                    data: aluno

                }).then(r => {
                    
                    const i = this.store.state.alunos.findIndex(a => a.idAluno == aluno.idAluno)

                    if (i != -1) { this.store.state.alunos[i] = r.data }
                    else { this.store.state.alunos.push(r.data) }

                    resolve(r.data)

                })

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
           
            if (id != undefined) {

                await this.axios({

                    url: `${this.baseUrl}/professor/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const professor: Professor = r.data

                    const i = this.store.state.professores.findIndex(a => a.idProfessor == id)

                    if (i != -1) { this.store.state.professores[i] = professor }
                    else { this.store.state.professores.push(professor) }

                })

                // ISSO AKI É PARA TESTE

                // const professor: Professor = testProfessor[id]

                // const i = this.store.state.professores.findIndex(a => a.idProfessor == id)

                // if (i != -1) { this.store.state.professores[i] = professor }
                // else { this.store.state.professores.push(professor) }

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/professores`,
                    method: "GET"

                }).then(r => {

                    const professores: Professor[] = r.data

                    this.store.state.professores = professores

                })
                
                // ISSO AKI É PARA TESTE

                // const professores: Professor[] = testProfessor

                // this.store.state.professores = professores

            }

        },

        create: (professor: Professor): Promise<Professor> => {

            return new Promise(async resolve => {

                await this.axios({

                    url: `${this.baseUrl}/professor`,
                    method: "POST",
                    data: professor
    
                }).then(r => {
    
                    const i = this.store.state.professores.findIndex(a => a.idProfessor == professor.idProfessor)
    
                    if (i != -1) { this.store.state.professores[i] = r.data }
                    else { this.store.state.professores.push(r.data) }

                    resolve(r.data)
    
                })

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
           
            if (id != undefined) {

                await this.axios({

                    url: `${this.baseUrl}/administrador/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const administrador: Administrador = r.data

                    const i = this.store.state.administradores.findIndex(a => a.idAdministrador == id)

                    if (i != -1) { this.store.state.administradores[i] = administrador }
                    else { this.store.state.administradores.push(administrador) }

                })

                // ISSO AKI É PARA TESTE

                // const administrador: Administrador = testAdministrador[id]

                // const i = this.store.state.administradores.findIndex(a => a.idAdministrador == id)

                // if (i != -1) { this.store.state.administradores[i] = administrador }
                // else { this.store.state.administradores.push(administrador) }

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/administradores`,
                    method: "GET"

                }).then(r => {

                    const administradores: Administrador[] = r.data

                    this.store.state.administradores = administradores

                })

                // ISSO AKI É PARA TESTE
                // this.store.state.administradores = testAdministrador

            }

        },

        create: (administrador: Administrador) : Promise<Administrador> => {

            return new Promise(async resolve => {

                await this.axios({

                    url: `${this.baseUrl}/administrador`,
                    method: "POST",
                    data: administrador
    
                }).then(r => {
                    
                    const adm = r.data

                    const i = this.store.state.administradores.findIndex(a => a.idAdministrador == administrador.idAdministrador)
    
                    if (i != -1) { this.store.state.administradores[i] = adm }
                    else { this.store.state.administradores.push(adm) }

                    resolve(adm)
    
                })

            })

        },

        update: async (administrador: Administrador) => {

            // console.log("UPDATO administrador", administrador)

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
           
            if (id != undefined) {

                await this.axios({

                    url: `${this.baseUrl}/aula/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const aula: Aula = r.data

                    aula.inicio = new Date(aula.inicio)

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

                    const aulas: Aula[] = r.data

                    aulas.forEach(a => a.inicio = new Date(a.inicio))

                    this.store.state.aulas = aulas

                })

            }

        },

        create: (aula: Aula): Promise<Aula> => {

            return new Promise(async resolve => {

                await this.axios({

                    url: `${this.baseUrl}/aula`,
                    method: "POST",
                    data: aula

                }).then(r => {
                    
                    const i = this.store.state.aulas.findIndex(a => a.idAula == aula.idAula)

                    if (i != -1) { this.store.state.aulas[i] = r.data }
                    else { this.store.state.aulas.push(r.data) }

                    resolve(r.data)

                    this.dias.get()

                })

            })

            this.dias.get()

        },

        update: (aula: Aula): Promise<Aula> => {

            return new Promise(async resolve => {

                await this.axios({

                    url: `${this.baseUrl}/aula/${aula.idAula}`,
                    method: "PUT",
                    data: aula

                }).then(r => {
                    
                    const i = this.store.state.aulas.findIndex(a => a.idAula == aula.idAula)

                    if (i != -1) { this.store.state.aulas[i] = aula }
                    else { this.store.state.aulas.push(aula) }
                    
                    resolve(r.data)

                })

                this.dias.get()

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

            this.dias.get()

        }

    }

    atividade = {

        get: async (id?: number) => {
           
            if (id != undefined) {

                await this.axios({

                    url: `${this.baseUrl}/atividade/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const atividade: Atividade = r.data

                    atividade.inicio = new Date(atividade.inicio)

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

                    const atividades: Atividade[] = r.data

                    atividades.forEach(a => a.inicio = new Date(a.inicio))

                    this.store.state.atividades = atividades

                })

            }

        },

        create: async (atividade: Atividade) => {

            await this.axios({

                url: `${this.baseUrl}/atividade`,
                method: "POST",
                data: atividade

            }).then(r => {
                
                const i = this.store.state.atividades.findIndex(a => a.idAtividade == atividade.idAtividade)

                if (i != -1) { this.store.state.atividades[i] = atividade }
                else { this.store.state.atividades.push(atividade) }

            })

        },

        update: async (atividade: Atividade) => {

            await this.axios({

                url: `${this.baseUrl}/atividade/${atividade.idAtividade}`,
                method: "PUT",
                data: atividade

            }).then(r => {
                
                const atividade: Atividade = r.data

                const i = this.store.state.atividades.findIndex(a => a.idAtividade == atividade.idAtividade)

                if (i != -1) { this.store.state.atividades[i] = atividade }
                else { this.store.state.atividades.push(atividade) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/atividade/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.atividades.findIndex(a => a.idAtividade == id)

                if (i != -1) { this.store.state.atividades.splice(i, 1) }

            })

        }

    }

    trabalho = {

        get: async (id?: number) => {
           
            if (id != undefined) {

                await this.axios({

                    url: `${this.baseUrl}/trabalho/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const trabalho: Trabalho = r.data

                    trabalho.inicio = new Date(trabalho.inicio)
                    trabalho.prazo = new Date(trabalho.prazo)

                    const i = this.store.state.trabalhos.findIndex(a => a.idTrabalho == id)

                    if (i != -1) { this.store.state.trabalhos[i] = trabalho }
                    else { this.store.state.trabalhos.push(trabalho) }

                })

                // ISSO AKI É PARA TESTE
                // const trabalho: Trabalho = testTrabalho[id]

                // const i = this.store.state.trabalhos.findIndex(a => a.idTrabalho == id)

                // if (i != -1) { this.store.state.trabalhos[i] = trabalho }
                // else { this.store.state.trabalhos.push(trabalho) }


            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/trabalhos`,
                    method: "GET"

                }).then(r => {

                    const trabalhos: Trabalho[] = r.data

                    trabalhos.forEach(t => t.inicio = new Date(t.inicio))
                    trabalhos.forEach(t => t.prazo = new Date(t.prazo))

                    this.store.state.trabalhos = trabalhos

                })

            }

        },

        create: (trabalho: Trabalho): Promise<Trabalho> => {

            return new Promise(async resolve => {

                await this.axios({

                    url: `${this.baseUrl}/trabalho`,
                    method: "POST",
                    data: trabalho

                }).then(r => {
                    
                    const i = this.store.state.trabalhos.findIndex(a => a.idTrabalho == trabalho.idTrabalho)

                    if (i != -1) { this.store.state.trabalhos[i] = r.data }
                    else { this.store.state.trabalhos.push(r.data) }

                    resolve(r.data)

                })

            })

            this.dias.get()

        },

        update: (trabalho: Trabalho): Promise<Trabalho> => {

            return new Promise(async resolve => {

                await this.axios({

                    url: `${this.baseUrl}/trabalho/${trabalho.idTrabalho}`,
                    method: "PUT",
                    data: trabalho

                }).then(r => {

                    const i = this.store.state.trabalhos.findIndex(a => a.idTrabalho == trabalho.idTrabalho)

                    if (i != -1) { this.store.state.trabalhos[i] = trabalho }
                    else { this.store.state.trabalhos.push(trabalho) }

                    resolve(trabalho)

                })

            })

            // ISSO AKI É PARA TESTE
            // const i = this.store.state.trabalhos.findIndex(a => a.idTrabalho == trabalho.idTrabalho)

            // if (i != -1) { this.store.state.trabalhos[i] = trabalho }
            // else { this.store.state.trabalhos.push(trabalho) }

            this.dias.get()

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/trabalho/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.trabalhos.findIndex(a => a.idTrabalho == id)

                if (i != -1) { this.store.state.trabalhos.splice(i, 1) }

            })

            this.dias.get()

        }

    }

    prova = {

        get: async (id?: number) => {
           
            if (id != undefined) {

                await this.axios({

                    url: `${this.baseUrl}/prova/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const prova: Prova = r.data

                    prova.inicio = new Date(prova.inicio)
                    prova.prazo = new Date(prova.prazo)

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

                    const provas: Prova[] = r.data

                    provas.forEach(p => p.inicio = new Date(p.inicio))
                    provas.forEach(p => p.prazo = new Date(p.prazo))

                    this.store.state.provas = provas

                })

            }

        },

        create: (prova: Prova): Promise<Prova> => {

            return new Promise(async resolve => {

                await this.axios({

                    url: `${this.baseUrl}/prova`,
                    method: "POST",
                    data: prova

                }).then(r => {
                    
                    const i = this.store.state.provas.findIndex(a => a.idProva == prova.idProva)

                    if (i != -1) { this.store.state.provas[i] = r.data }
                    else { this.store.state.provas.push(r.data) }

                    resolve(r.data)

                })

                this.dias.get()

            })

        },

        update: async (prova: Prova) => {

            await this.axios({

                url: `${this.baseUrl}/prova/${prova.idProva}`,
                method: "PUT",
                data: prova

            }).then(r => {

                const i = this.store.state.provas.findIndex(a => a.idProva == prova.idProva)

                if (i != -1) { this.store.state.provas[i] = prova }
                else { this.store.state.provas.push(prova) }

            })

            this.dias.get()

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/prova/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.provas.findIndex(a => a.idProva == id)

                if (i != -1) { this.store.state.provas.splice(i, 1) }

            })

            this.dias.get()

        }

    }

    turma = {

        get: async (id?: number) => {
           
            if (id != undefined) {

                await this.axios({

                    url: `${this.baseUrl}/turma/${id}`,
                    method: "GET"

                }).then(r => {

                    let turma: Turma = r.data

                    turma.aula.forEach(a => a.inicio = new Date(a.inicio))
                    turma.atividade.forEach(a => a.inicio = new Date(a.inicio))
                    turma.trabalho.forEach(t => { t.inicio = new Date(t.inicio); t.prazo = new Date(t.prazo) })
                    turma.prova.forEach(p => { p.inicio = new Date(p.inicio); p.prazo = new Date(p.prazo) })

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

                    const turmas: Turma[] = r.data

                    turmas.forEach(t => t.aula.forEach(a => a.inicio = new Date(a.inicio)))
                    turmas.forEach(t => t.atividade.forEach(a => a.inicio = new Date(a.inicio)))
                    turmas.forEach(t => t.trabalho.forEach(t => { t.inicio = new Date(t.inicio); t.prazo = new Date(t.prazo) }))
                    turmas.forEach(t => t.prova.forEach(p => { p.inicio = new Date(p.inicio); p.prazo = new Date(p.prazo) }))

                    this.store.state.turmas = turmas

                })

                // ISSO AKI É PARA TESTE 

                // const turmas: Turma[] = testTurma //r.data

                // this.store.state.turmas = turmas

            }

        },

        create: (turma: Turma): Promise<Turma> => {

            return new Promise(async resolve => {

                await this.axios({

                    url: `${this.baseUrl}/turma`,
                    method: "POST",
                    data: turma

                }).then(r => {
                    
                    const i = this.store.state.turmas.findIndex(a => a.idTurma == turma.idTurma)

                    if (i != -1) { this.store.state.turmas[i] = r.data }
                    else { this.store.state.turmas.push(r.data) }

                    resolve(r.data)

                })

            })

        },

        update: async (turma: Turma) => {

            await this.axios({

                url: `${this.baseUrl}/turma/${turma.idTurma}`,
                method: "PUT",
                data: turma

            }).then(r => {

                const i = this.store.state.provas.findIndex(a => a.idProva == turma.idTurma)

                if (i != -1) { this.store.state.turmas[i] = turma }
                else { this.store.state.turmas.push(turma) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/turma/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.materias.findIndex(a => a.idMateria == id)

                if (i != -1) { this.store.state.materias.splice(i, 1) }

            })

        }

    }

    materia = {

        get: async (id?: number) => {
           
            if (id != undefined) {

                await this.axios({

                    url: `${this.baseUrl}/materia/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    const materia: Materia = r.data

                    const i = this.store.state.materias.findIndex(a => a.idMateria == id)

                    if (i != -1) { this.store.state.materias[i] = materia }
                    else { this.store.state.materias.push(materia) }

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/materias`,
                    method: "GET"

                }).then(r => {
  
                const materias: Materia[] = r.data

                this.store.state.materias = materias

                })

                // ISSO AKI É PARA TESTE
                // const materias: Materia[] = testMateria //r.data

                // this.store.state.materias = materias

                // this.store.state.materiasProfessor = materias.filter(m => m.idProfessor.includes(this.store.state.idUser))

            }

        },

        create: async (materia: Materia) => {

            await this.axios({

                url: `${this.baseUrl}/materia`,
                method: "POST",
                data: materia

            }).then(r => {
                
                const i = this.store.state.materias.findIndex(a => a.idMateria == materia.idMateria)

                if (i != -1) { this.store.state.materias[i] = materia }
                else { this.store.state.materias.push(materia) }

            })

        },

        update: async (materia: Materia) => {

            await this.axios({

                url: `${this.baseUrl}/materia/${materia.idMateria}`,
                method: "PUT",
                data: materia

            }).then(r => {

                const i = this.store.state.materias.findIndex(a => a.idMateria == materia.idMateria)

                if (i != -1) { this.store.state.materias[i] = materia }
                else { this.store.state.materias.push(materia) }

            })

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/materia/${id}`,
                method: "DELETE"

            }).then(r => {
                
                const i = this.store.state.materias.findIndex(a => a.idMateria == id)

                if (i != -1) { this.store.state.materias.splice(i, 1) }

            })

        }

    }

    nota = {

        create: (nota: NotaProva | NotaTrabalho, tipo: "trabalho" | "prova"): Promise<NotaProva | NotaTrabalho> => {

            return new Promise(async resolve => {

                if (tipo == "trabalho") {

                    const newNota = nota as NotaTrabalho

                    // await this.axios({

                    //     url: `${this.baseUrl}/notatrabalho`,
                    //     method: "POST",
                    //     data: nota
        
                    // }).then(r => {
                        
                    //     const i = this.store.state.alunos.findIndex(a => a.idAluno == newNota.idAluno)
        
                    //     if (i != -1) { this.store.state.alunos[i].notasTrabalhos.push(newNota) }

                    //     resolve(newNota)
        
                    // })

                    resolve(newNota)

                }

                if (tipo == "prova") {

                    const newNota = nota as NotaProva

                    // await this.axios({

                    //     url: `${this.baseUrl}/notaprova`,
                    //     method: "POST",
                    //     data: nota
        
                    // }).then(r => {
                        
                    //     const i = this.store.state.alunos.findIndex(a => a.idAluno == newNota.idAluno)
        
                    //     if (i != -1) { this.store.state.alunos[i].notasProvas.push(newNota) }

                    //     resolve(newNota)
        
                    // })

                    resolve(newNota)

                }

            })

        },

        update: async (nota: NotaProva | NotaTrabalho, tipo: "trabalho" | "prova") => {

            if (tipo == "trabalho") {

                const newNota = nota as NotaTrabalho

                await this.axios({

                    url: `${this.baseUrl}/notatrabalho`,
                    method: "PUT",
                    data: nota
    
                }).then(r => {
                    
                    const i = this.store.state.alunos.findIndex(a => a.idAluno == newNota.idAluno)
                    const x = this.store.state.alunos[i].notasTrabalhos.findIndex(n => n.idNotaTrabalho == newNota.idNotaTrabalho)

                    if (i != -1) { 
                        
                        if (x != -1) { this.store.state.alunos[i].notasTrabalhos[x] = newNota }
                        else { this.store.state.alunos[i].notasTrabalhos.push(newNota)  }

                    }
    
                })

            }

            if (tipo == "prova") {

                const newNota = nota as NotaProva

                await this.axios({

                    url: `${this.baseUrl}/notaprova`,
                    method: "PUT",
                    data: nota
    
                }).then(r => {
                    
                    const i = this.store.state.alunos.findIndex(a => a.idAluno == newNota.idAluno)
                    const x = this.store.state.alunos[i].notasProvas.findIndex(n => n.idNotaProva == newNota.idNotaProva)

                    if (i != -1) { 
                        
                        if (x != -1) { this.store.state.alunos[i].notasProvas[x] = newNota }
                        else { this.store.state.alunos[i].notasProvas.push(newNota)  }

                    }
    
                })

            }

        },

        delete: async (id: number, tipo: "trabalho" | "prova") => {

            if (tipo == "trabalho") {

                await this.axios({

                    url: `${this.baseUrl}/notatrabalho/${id}`,
                    method: "DELETE"
    
                }).then(r => {
                    
                    const i = this.store.state.alunos.findIndex(a => a.notasTrabalhos.map(n => n.idNotaTrabalho).includes(id))
                    const x = this.store.state.alunos[i].notasTrabalhos.findIndex(n => n.idNotaTrabalho == id)
    
                    if (i != -1) { 
                        
                        if (x != -1) { this.store.state.alunos[i].notasTrabalhos.splice(x, 1)  }

                    }
    
                })

            }

            if (tipo == "prova") {

                await this.axios({

                    url: `${this.baseUrl}/notaprova/${id}`,
                    method: "DELETE"
    
                }).then(r => {
                    
                    const i = this.store.state.alunos.findIndex(a => a.notasProvas.map(n => n.idNotaProva).includes(id))
                    const x = this.store.state.alunos[i].notasProvas.findIndex(n => n.idNotaProva == id)
    
                    if (i != -1) { 
                        
                        if (x != -1) { this.store.state.alunos[i].notasProvas.splice(x, 1)  }

                    }
    
                })

            }

        }

    }

    resposta = {

        create: (resposta: Resposta): Promise<Resposta> => {

            return new Promise(async resolve => {

                await this.axios({

                    url: `${this.baseUrl}/resposta`,
                    method: "POST",
                    data: resposta
    
                }).then(r => { resolve(resposta) })

            })


        },

        update: async (resposta: Resposta) => {

            await this.axios({

                url: `${this.baseUrl}/resposta`,
                method: "PUT",
                data: resposta

            }).then(r => {})

        },

        delete: async (id: number) => {

            await this.axios({

                url: `${this.baseUrl}/resposta/${id}`,
                method: "DELETE"

            }).then(r => {})

        }

    }

    login = (tipo: "aluno" | "professor" | "administrador", login: string, senha: string): Promise<boolean> => {

        // this.axios({url: "/api/login/aluno", method: 'post'}).then(r => {console.log("eeeee", r)}).catch(e => {console.log("aaaaaa", e)})

        return new Promise(resolve => {

            this.axios({

                url: `${this.baseUrl}/login/${tipo}`,
                method: 'POST',
                data: { login: login, senha: senha },

            }).then(r => {

                if (tipo == "aluno") {

                    const aluno: Aluno = r.data

                    const i = this.store.state.alunos.findIndex(a => a.idAluno == aluno.idAluno)

                    this.store.state.idUser = aluno.idAluno
                    this.store.state.typeUser = tipo

                    if (i != -1) { this.store.state.alunos[i] = aluno }
                    else { this.store.state.alunos.push(aluno) }

                    resolve(true)

                }

                if (tipo == "professor") {

                    const professor: Professor = r.data

                    console.log(professor)

                    const i = this.store.state.professores.findIndex(p => p.idProfessor == professor.idProfessor)

                    this.store.state.idUser = professor.idProfessor
                    this.store.state.typeUser = tipo

                    if (i != -1) { this.store.state.professores[i] = professor }
                    else { this.store.state.professores.push(professor) }

                    resolve(true)

                }

                if (tipo == "administrador") {

                    const administrador: Administrador = r.data

                    const i = this.store.state.administradores.findIndex(a => a.idAdministrador == administrador.idAdministrador)

                    this.store.state.idUser = administrador.idAdministrador
                    this.store.state.typeUser = tipo

                    if (i != -1) { this.store.state.administradores[i] = administrador }
                    else { this.store.state.administradores.push(administrador) }

                    resolve(true)

                }

            })
            .catch(e => { resolve(false) })

        })

    }

}