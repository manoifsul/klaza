<template>
    <q-page>

        <div class="bg-primary text-white">

            <q-tabs v-model="tab" align="justify">

                <q-tab v-for="(q, i) in prova.questao" :key="`Tab/${q.idQuestao}`" :name="`Tab/${q.idQuestao}`" class="text-white text-bold" :label="`Pergunta ${i+1}`"> 
                
                    <q-icon v-if="finalizada && showCorretas[q.idQuestao].correta" name="fas fa-check"/>
                    <q-icon v-if="finalizada && !showCorretas[q.idQuestao].correta && !showCorretas[q.idQuestao].file" name="fas fa-times"/>
                    <q-icon v-if="finalizada && !showCorretas[q.idQuestao].correta && showCorretas[q.idQuestao].file" name="fas fa-question"/>
                
                </q-tab>

            </q-tabs>

        </div>

        <q-page class="box br-0 q-pa-md">

            <q-tab-panels v-model="tab" animated transition-prev="jump-up" transition-next="jump-up" class="bg-transparent">

                <q-tab-panel v-for="(q, i) in prova.questao" :key="`Tab/${q.idQuestao}`" :name="`Tab/${q.idQuestao}`">

                    <div class="fit row wrap justify-between items-start content-start">

                        <div class="col-9">

                            <div class="boxQuestao br-20 q-mb-xl text-white text-center text-h4">{{q.pergunta}}</div>

                            <div class="boxQuestao br-20 q-mb-xl text-white text-center text-h4 full-width wrap justify-center content-center">

                                <div v-if="q.tipo == 0" class="column">

                                    <q-input autofocus outlined rounded label-color="white" input-class="text-white" placeholder="Digite a resposta" v-model="models[q.idQuestao]" type="textarea" :disable="finalizada"/>

                                </div>

                                <div v-if="q.tipo == 1" class="column">

                                    <q-radio v-for="a in q.questaoAlternativa" :key="`Radio/${a.idQuestaoAlternativa}`" v-model="models[q.idQuestao]" :val="a.idQuestaoAlternativa" :label="a.resposta" :disable="finalizada"/>

                                </div>

                                <div v-if="q.tipo == 2" class="column">

                                    <q-checkbox :false-value="null" v-for="a in q.questaoAlternativa" :key="`Check/${a.idQuestaoAlternativa}`" v-model="models[`${q.idQuestao}/${a.idQuestaoAlternativa}`]" :label="a.resposta" :disable="finalizada" />

                                </div>

                                <div v-if="q.tipo == 3" class="column">

                                    <q-file v-model="models[q.idQuestao]" label-color="white" label="Adicione o(s) anexo(s)" outlined counter use-chips append multiple :disable="finalizada">

                                        <template v-slot:prepend>
                                            <q-icon color="white" name="fas fa-upload" />
                                        </template>

                                    </q-file>

                                </div>

                            </div>

                            <div v-if="finalizada" class="boxQuestao br-20 q-mb-xl text-white text-center text-h4">

                                Corretas:
                                
                                <span>{{ q.questaoCorreta.questaoAlternativa.map(a => a.resposta).join(", ") }}</span>

                            </div>

                        </div>
                        <div class="col-2 h-100">

                            <div class="boxInfo br-20 h-100 text-center text-white">

                                <div class="text-h4 text-bold">Questão</div>
                                <div class="text-h5 text-bold q-mb-xl">{{prova.questao.findIndex(q => q.idQuestao == parseInt(tab.split("/")[1]))+1}} / {{prova.questao.length}}</div>

                                <div v-if="!finalizada" class="q-mb-xl">

                                    <div class="text-h5 text-bold">Tempo restante</div>
                                    <div class="text-h5 text-bold">{{timer}}</div>

                                </div>

                                <q-btn v-if="!finalizada" class="bg-primary" @click="finalizarSwal()">Finalizar</q-btn>
                                <q-btn v-if="finalizada" class="bg-primary" to="/home">Voltar ao home</q-btn>

                            </div>

                        </div>

                    </div>

                </q-tab-panel>

            </q-tab-panels>

        </q-page>

    </q-page>
</template>

<script lang="ts">

import { Vue, Component } from 'vue-property-decorator';
import { DB } from '../middlewares/DBContector'
import { uid } from 'quasar'

import moment from 'moment'
import * as DBTypes from 'src/@types/DB';
import { showCorretas } from 'src/@types/vue';

@Component({

    components: { }

})
export default class Prova extends Vue {

    db = new DB(this.$axios, this.$store)

    prova: DBTypes.Prova = { idProva: 0, nome: "", prazo: new Date(), descricao: "", inicio: new Date(), tempo: 0, tentativas: 0, professor: [], administrador: [], arquivo: [], questao: [], materia: { idMateria: 0, nome: "", idProfessor: [] }, resposta: [], idTurma: 0 }

    uid = uid

    tab = ""

    models = {}

    timer = ""

    idProva = parseInt(this.$route.params.idProva)
    nTentativa = parseInt(this.$route.params.nTentativa)

    finalizada = false

    showCorretas: showCorretas = {}

    interval!: NodeJS.Timeout

    async created() {

        await this.db.prova.get(this.idProva)

        const provas = this.$store.state.trabalhos as DBTypes.Prova[]
        const i = provas.findIndex(t => (t != undefined && t.idProva == this.idProva) )

        if (i != -1) {

            this.prova = provas[i]

            this.tab = `Tab/${this.prova.questao[0].idQuestao}`

        }
        else { this.swal404(); return }

        const respostas = this.prova.resposta.filter(r => ( r.idAluno == this.$store.state.idUser && r.nroTentativa == this.nTentativa ))

        if (respostas.length > 0) { this.swalJaRealizado(); return }

        let duracao = moment.duration(this.prova.tempo, 'seconds')
        this.timer = `${duracao.hours()}:${duracao.minutes()}:${duracao.seconds()}`

        this.interval = setInterval(() => {

            duracao = moment.duration(duracao.asSeconds()-1, 'seconds')
            this.timer = `${duracao.hours()}:${duracao.minutes()}:${duracao.seconds()}`

            if (duracao.asMilliseconds() <= 0) { 

                clearInterval(this.interval)

                this.$swal.fire({

                    title: "Tempo esgotado",
                    text: "O tempo para realização acabou, a tentativa sera encerrada",
                     customClass: {

                        popup: "bg-primary",
                        content: "text-white",
                        title: "text-white",
                        confirmButton: "bg-white text-black",

                    }

                }).then(r => {

                    this.finalizar()

                })

            }

        }, 1000)

    }

    async finalizar() {

        clearInterval(this.interval)

        await this.db.prova.get(this.idProva)

        const provas = this.$store.state.trabalhos as DBTypes.Prova[]
        const i = provas.findIndex(t => t.idProva == parseInt(this.$route.params.idTrabalho))

        this.prova = provas[i]

        const total = this.prova.questao.length
        let certas = 0
       
        this.prova.questao.forEach(async q => {

            console.log(q)

            let corretas = (q.questaoCorreta.questaoAlternativa.length > 0) ? q.questaoCorreta.questaoAlternativa.map(a => a.idQuestaoAlternativa) : []

            console.log(corretas)

            if (q.tipo == 0) {

                const textCorretas = q.questaoAlternativa.filter(q => corretas.includes(q.idQuestaoAlternativa)).map(a => a.resposta)

                if (textCorretas.includes(this.models[q.idQuestao])) { 
                    
                    certas++ 
                
                    this.showCorretas[q.idQuestao] = { correta: true, file: false }

                }
                else { this.showCorretas[q.idQuestao] = { correta: false, file: false } }

                const resposta = (await this.db.resposta.create({

                    idResposta: -1,
                    questao: q,
                    resposta: (this.models[q.idQuestao] == undefined) ? "" : this.models[q.idQuestao],
                    nroTentativa: this.nTentativa,
                    idAluno: this.$store.state.idUser

                }))

                this.prova.resposta.push(resposta)

            }

            if (q.tipo == 1) { 

                if (this.models[q.idQuestao] == corretas[0]) { 
                    
                    certas++ 
                    
                    this.showCorretas[q.idQuestao] = { correta: true, file: false }
                
                }
                else { this.showCorretas[q.idQuestao] = { correta: false, file: false } } 

                console.log(corretas)

                const resposta = (await this.db.resposta.create({

                    idResposta: -1,
                    questao: q,
                    resposta: (this.models[q.idQuestao] == undefined) ? "" : this.models[q.idQuestao],
                    nroTentativa: this.nTentativa,
                    idAluno: this.$store.state.idUser

                }))

                this.prova.resposta.push(resposta)

            }

            if (q.tipo == 2) { 
            
                const respostas: number[] = []

                q.questaoAlternativa.map(a => a.idQuestaoAlternativa).forEach(id => {

                    if (this.models[`${q.idQuestao}/${id}`] == true) { respostas.push(id) }

                })

                if (respostas.sort().toString() == corretas.sort().toString()) { 
                    
                    certas++  

                    this.showCorretas[q.idQuestao] = { correta: true, file: false }
                
                }
                else { this.showCorretas[q.idQuestao] = { correta: false, file: false } } 

                const resposta = (await this.db.resposta.create({

                    idResposta: -1,
                    questao: q,
                    resposta: respostas.sort().toString(),
                    nroTentativa: this.nTentativa,
                    idAluno: this.$store.state.idUser

                }))

                this.prova.resposta.push(resposta)

            }

            if (q.tipo == 3) { this.showCorretas[q.idQuestao] = { correta: false, file: true } }

        })

        const nota = (certas/total)*10

        console.log("Nota ->", nota)

        await this.db.prova.update(this.prova)

        await this.db.nota.create({

            idNotaProva: -1,
            valor: nota,
            prova: this.prova,
            idAluno: this.$store.state.idUser

        } as DBTypes.NotaProva, "prova")

        this.$swal.fire({

            title: "Tentativa finalizada",
            text: `Sua nota foi ${nota}`,
            customClass: {

                popup: "bg-primary",
                content: "text-white",
                title: "text-white",
                confirmButton: "bg-white text-black",

            }

        })

        this.finalizada = true

    }

    finalizarSwal() {

        this.$swal.fire({

            icon: "question",
            title: "Finalizar",
            text: "Tem certeza que quer finalizar a tentativa",
            showConfirmButton: true,
            showDenyButton: true,
            confirmButtonText: "Sim",
            denyButtonText: "Não",
            customClass: {

                popup: "bg-primary",
                content: "text-white",
                title: "text-white",
                confirmButton: "bg-white text-black"

            }

        }).then(r => { if (r.isConfirmed) { this.finalizar() } })

    }

    swal404() {

        this.$swal.fire({

            title: "Não encontrado",
            text: "Esse trabalho não foi encontrado",
            confirmButtonText: "Voltar ao Home",
            customClass: {

                popup: "bg-primary",
                content: "text-white",
                title: "text-white",
                confirmButton: "bg-white text-black",

            }

        }).then(swal => { this.$router.push("/home") })

    }

    swalJaRealizado() {

        this.$swal.fire({

            title: "Já realizada",
            text: "Essa tentativa ja foi realiza",
            confirmButtonText: "Voltar ao Home",
            customClass: {

                popup: "bg-primary",
                content: "text-white",
                title: "text-white",
                confirmButton: "bg-white text-black",

            }

        }).then(swal => { this.$router.push("/home") })

    }

}

</script>

<style lang="scss">

body {

    background-image: url("../assets/Fundo Home.png");
    background-size: cover;

}

div.boxQuestao {

    border: #3b8c2e solid 2px;
    width: 100%;
    padding: 15px;

}

div.boxInfo {

    border: #3b8c2e solid 2px;
    width: 100%;
    padding: 15px 0px 15px 0px;

}

</style>