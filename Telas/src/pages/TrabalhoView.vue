<template>
    <q-page>

        <div class="bg-primary text-white">

            <q-tabs v-model="tab" align="justify">

                <q-tab v-for="(q, i) in trabalho.questao" :key="`Tab/${q.idQuestao}`" :name="`Tab/${q.idQuestao}`" class="text-white text-bold" :label="`Pergunta ${i+1}`"> 
                
                    <q-icon v-if="showCorretas[q.idQuestao].correta" name="fas fa-check"/>
                    <q-icon v-if="!showCorretas[q.idQuestao].correta && !showCorretas[q.idQuestao].file" name="fas fa-times"/>
                    <q-icon v-if="!showCorretas[q.idQuestao].correta && showCorretas[q.idQuestao].file" name="fas fa-question"/>
                
                </q-tab>

            </q-tabs>

        </div>

        <q-page class="box br-0 q-pa-md">

            <q-tab-panels v-model="tab" animated transition-prev="jump-up" transition-next="jump-up" class="bg-transparent">

                <q-tab-panel v-for="(q, i) in trabalho.questao" :key="`Tab/${q.idQuestao}`" :name="`Tab/${q.idQuestao}`">

                    <div class="fit row wrap justify-between items-start content-start">

                        <div class="col-9">

                            <div class="boxQuestao br-20 q-mb-xl text-white text-center text-h4">{{q.pergunta}}</div>

                            <div class="boxQuestao br-20 q-mb-xl text-white text-center text-h4 full-width wrap justify-center content-center">

                                <div v-if="q.tipo == 0" class="column">

                                    <q-input autofocus outlined rounded label-color="white" input-class="text-white" placeholder="Digite a resposta" v-model="models[q.idQuestao]" type="textarea" disable/>

                                </div>

                                <div v-if="q.tipo == 1" class="column">

                                    <q-radio v-for="a in q.questaoAlternativa" :key="`Radio/${a.idQuestaoAlternativa}`" v-model="models[q.idQuestao]" :val="a.idQuestaoAlternativa" :label="a.resposta" disable/>

                                </div>

                                <div v-if="q.tipo == 2" class="column">

                                    <q-checkbox :false-value="null" v-for="a in q.questaoAlternativa" :key="`Check/${a.idQuestaoAlternativa}`" v-model="models[`${q.idQuestao}/${a.idQuestaoAlternativa}`]" :label="a.resposta" disable />

                                </div>

                                <div v-if="q.tipo == 3" class="column">

                                    <!-- <q-file v-model="models[q.idQuestao]" label-color="white" label="Adicione o(s) anexo(s)" outlined counter use-chips append multiple disable>

                                        <template v-slot:prepend>
                                            <q-icon color="white" name="fas fa-upload" />
                                        </template>

                                    </q-file> -->

                                    <a v-for="f in models[q.idQuestao]" :href="f">{{f}}</a>

                                </div>

                            </div>

                            <div v-if="q.tipo != 3" class="boxQuestao br-20 q-mb-xl text-white text-center text-h4">

                                Corretas:
                                
                                <span>{{ q.questaoCorreta.questaoAlternativa.map(a => a.resposta).join(", ") }}</span>

                            </div>

                        </div>
                        <div class="col-2 h-100">

                            <div class="boxInfo br-20 h-100 text-center text-white">

                                <div class="text-h4 text-bold">Questão</div>
                                <div class="text-h5 text-bold q-mb-xl">{{trabalho.questao.findIndex(q => q.idQuestao == parseInt(tab.split("/")[1]))+1}} / {{trabalho.questao.length}}</div>

                                <div class="q-mb-xl">

                                    <div class="text-h5 text-bold">Nota</div>
                                    <div class="text-h5 text-bold">{{nota}} / 10</div>

                                </div>

                                <q-btn class="bg-primary" to="/home">Voltar ao home</q-btn>

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

import * as DBTypes from 'src/@types/DB';
import { showCorretas } from 'src/@types/vue';

@Component({

    components: { }

})
export default class TrabalhoView extends Vue {

    db = new DB(this.$axios, this.$store)

    trabalho: DBTypes.Trabalho = { idTrabalho: 0, nome: "", prazo: new Date(), descricao: "", inicio: new Date(), tempo: 0, tentativas: 0, tipo: 0, professor: [], administrador: [], arquivo: [], questao: [], materia: { idMateria: 0, nome: "", idProfessor: [] }, resposta: [], idTurma: 0}

    uid = uid

    tab = ""

    models = {}

    timer = ""

    idTrabalho = parseInt(this.$route.params.idTrabalho)
    idAluno = parseInt(this.$route.params.idAluno)
    nTentativa = parseInt(this.$route.params.nTentativa)

    showCorretas: showCorretas = {}

    nota = 0

    async created() {

        await this.db.trabalho.get(this.idTrabalho)

        const trabalhos = this.$store.state.trabalhos as DBTypes.Trabalho[]
        const i = trabalhos.findIndex(t => (t != undefined && t.idTrabalho == this.idTrabalho) )

        if (i == -1) { this.swal404(); return }

        this.trabalho = trabalhos[i]

        const respostas = this.trabalho.resposta.filter(r => ( r.idAluno == this.idAluno && r.nroTentativa == this.nTentativa ))

        if (respostas.length == 0) { this.swal404(); return }

        respostas.forEach(r => {

            if (r.questao.tipo == 0) { this.models[r.questao.idQuestao] = r.resposta }

            if (r.questao.tipo == 1) { this.models[r.questao.idQuestao] = parseInt(r.resposta) }

            if (r.questao.tipo == 2) { 
                
                const check = r.resposta.split(',')

                check.forEach(c => {

                    this.models[`${r.questao.idQuestao}/${c}`] = true 

                })

            }

            if (r.questao.tipo == 3) { this.models[r.questao.idQuestao] = r.resposta.split(",") }

        })

        const total = this.trabalho.questao.length
        let certas = 0
       
        this.trabalho.questao.forEach(async q => {

            const corretas = (q.questaoCorreta.questaoAlternativa.length > 0) ? q.questaoCorreta.questaoAlternativa.map(a => a.idQuestaoAlternativa) : [] 

            if (q.tipo == 0) {

                const textCorretas = q.questaoAlternativa.filter(q => corretas.includes(q.idQuestaoAlternativa)).map(a => a.resposta)

                if (textCorretas.includes(this.models[q.idQuestao])) { 
                    
                    certas++ 
                
                    this.showCorretas[q.idQuestao] = { correta: true, file: false }

                }
                else { this.showCorretas[q.idQuestao] = { correta: false, file: false } }

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

                this.trabalho.resposta.push(resposta)

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

                this.trabalho.resposta.push(resposta)

            }

            if (q.tipo == 3) { this.showCorretas[q.idQuestao] = { correta: false, file: true } }

        })

        this.nota = (certas/total)*10

        if (i != -1) {

            this.trabalho = trabalhos[i]

            this.tab = `Tab/${this.trabalho.questao[0].idQuestao}`

        }
        else {

            this.$router.push("/404")

        }
        
    }

    swal404() {

        this.$swal.fire({

            title: "Não encontrada",
            text: "Essa tentativa não foi encontrada",
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