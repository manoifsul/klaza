<template>

    <div class="box fit q-pa-md contentTrabalho">

        <form @submit.prevent.stop="save()">

            <div class="q-gutter-x-sm row wrap justify-end q-mb-md">

                <q-btn v-if="$store.state.typeUser != 'aluno' && editProp != true" @click="edit()" class="br-10" padding="sm" color="primary" icon="fas fa-pen"/>
                <q-btn v-if="$store.state.typeUser != 'aluno' && editProp != true" @click="remove()" class="br-10" padding="sm" color="primary" icon="fas fa-trash"/>
                <q-btn class="br-10" padding="sm" color="primary" icon="fas fa-times" v-close-popup/>

            </div>

            <div class="q-mb-md">

                <q-tabs v-model="tab" class="text-white" align="justify">

                    <q-tab name="i" label="Informações" />
                    <q-tab v-if="$store.state.typeUser != 'aluno' && vEdit" name="q" label="Questões" />
                    <q-tab v-if="!vEdit" name="t" label="Tentativas" />
            
                </q-tabs>

            </div>

            <q-tab-panels v-if="!vEdit && !add && vTurma" v-model="tab" animated swipeable vertical transition-prev="jump-up" transition-next="jump-up" class="bg-transparent">

                <q-tab-panel name="i">

                    <div class="column text-bold text-h4 text-white q-gutter-y-md">

                        <span>Trabalho: {{vNome}}</span>
                        <span>Turma: {{vTurma.nome}} - {{vTurma.materia.nome}}</span>
                        <span>Data: {{vStart}} a {{vFinish}}</span>
                        <span v-if="vMaxTentativas > 0">
                            
                            Maximo de tentativas: {{vMaxTentativas}}
                            
                        </span>
                        <span v-else>

                            Maximo de tentativas: Sem maximo

                        </span>
                        <span>Tempo maximo para resolver: {{vMaxTempo}}</span>
                        <span>Anexos: <a v-for="f in vProva.arquivo" :key="uid()" :href="f.link">{{f.link}}</a> </span>
                        <span>Descrição:</span>
                        <div class="descricao br-20">{{vDescricao}}</div>        

                    </div>
                
                </q-tab-panel>

                <q-tab-panel name="t">

                    <ListTentativas :respostas="vProva.resposta" />

                </q-tab-panel>

            </q-tab-panels>

            <q-tab-panels v-model="tab" animated swipeable vertical transition-prev="jump-up" transition-next="jump-up" class="bg-transparent" v-else>

                <q-tab-panel name="i">

                    <div class="column text-bold text-h4 q-gutter-y-md">

                        <q-input outlined v-model="modelNome" label-color="white" input-class="text-white" label="Prova" placeholder="Digite o nome da prova" required/>
                        <q-select outlined input-class="text-white" label-color="white" v-model="modelTurma" placeholder="Digite a turma da prova" :options="optionsTurmas" label="Turma" use-chips use-input />
                        <q-select outlined input-class="text-white" label-color="white" v-model="modelProfessores" placeholder="Digite o(s) nome(s) dos professor(es)" :options="optionsProfessores" label="Professores" use-chips use-input multiple />
                        <div class="fit row wrap justify-evenly">

                            <q-input ref="inpSDate" outlined label-color="white" input-class="text-white" v-model="sDate" :rules="[patthenTime]" label="Data inicio" required>
                            <template v-slot:prepend>
                                <q-icon name="event" class="cursor-pointer" color="white">
                                <q-popup-proxy transition-show="scale" transition-hide="scale">
                                    <q-date v-model="sDate" mask="DD/MM/YYYY HH:mm">
                                    <div class="row items-center justify-end">
                                        <q-btn v-close-popup label="Fechar" color="primary" flat />
                                    </div>
                                    </q-date>
                                </q-popup-proxy>
                                </q-icon>
                            </template>

                            <template v-slot:append>
                                <q-icon name="access_time" class="cursor-pointer" color="white">
                                <q-popup-proxy transition-show="scale" transition-hide="scale">
                                    <q-time v-model="sDate" mask="DD/MM/YYYY HH:mm">
                                    <div class="row items-center justify-end">
                                        <q-btn v-close-popup label="Fechar" color="primary" flat />
                                    </div>
                                    </q-time>
                                </q-popup-proxy>
                                </q-icon>
                            </template>
                            </q-input>

                            <q-input ref="inpFDate" outlined label-color="white" input-class="text-white" v-model="fDate" :rules="[patthenTime]" label="Data final" required>
                                <template v-slot:prepend>
                                    <q-icon name="event" class="cursor-pointer" color="white">
                                    <q-popup-proxy transition-show="scale" transition-hide="scale">
                                        <q-date v-model="fDate" mask="DD/MM/YYYY HH:mm" :options="optionsFDate">
                                        <div class="row items-center justify-end">
                                            <q-btn v-close-popup label="Fechar" color="primary" flat />
                                        </div>
                                        </q-date>
                                    </q-popup-proxy>
                                    </q-icon>
                                </template>

                                <template v-slot:append>
                                    <q-icon name="access_time" class="cursor-pointer" color="white">
                                    <q-popup-proxy transition-show="scale" transition-hide="scale">
                                        <q-time v-model="fDate" mask="DD/MM/YYYY HH:mm">
                                        <div class="row items-center justify-end">
                                            <q-btn v-close-popup label="Fechar" color="primary" flat />
                                        </div>
                                        </q-time>
                                    </q-popup-proxy>
                                    </q-icon>
                                </template>
                            </q-input>

                        </div>
                        <q-select outlined input-class="text-white" label-color="white" v-model="modelMaxTempo" :options="optionsMaxTempo" label="Limite de tempo" required />
                        <q-input ref="inpTentativas" outlined v-model="vMaxTentativas" label-color="white" input-class="text-white" label="Maximo de tenativas (0 = sem limite)" type="number" :rules="[val => val >= 0 && val <= 99]" required />
                        <q-file v-model="modelFiles" label-color="white" label="Adicione o(s) anexo(s)" outlined counter use-chips append multiple>

                            <template v-slot:prepend>
                                <q-icon color="white" name="fas fa-upload" />
                            </template>

                        </q-file>
                        <q-input outlined v-model="modelDescricao" label-color="white" input-class="text-white" label="Descrição" type="textarea" placeholder="Digite a descrição da prova" required/>

                    </div>
                    <div class="row justify-center q-mt-md">

                        <q-btn v-if="vEdit" type="submit" label="Salvar" color="primary"/>

                    </div>

                </q-tab-panel>

                <q-tab-panel name="q" class="text-white">

                    <q-list>

                        <ItemQuestao v-for="(q, i) in  vProva.questao" :key="q.idQuestao" :question="q.pergunta" :type="q.tipo" :alternativas="q.questaoAlternativa" :correta="q.questaoCorreta" :id="q.idQuestao" :number="i+1"/>

                    </q-list>

                    <div class="row justify-center q-mt-md">

                        <q-btn v-if="vEdit" label="Adicionar Questão" @click="addQuestão()" color="primary"/>

                    </div>

                </q-tab-panel>

            </q-tab-panels>

        </form>

    </div>

</template>

<script lang="ts">

import { Vue, Component, Prop } from 'vue-property-decorator';
import { qSelectOptions } from 'src/@types/vue'
import * as DBTypes from '../@types/DB'

import moment from 'moment';

import ItemQuestao from 'components/ItemQuestao.vue'
import ListTentativas from 'components/ListTentativas.vue'
import { DB } from 'src/middlewares/DBContector';
import { uid } from 'quasar';
import { Discord } from 'src/middlewares/DiscordConector';

@Component({

    components: { ItemQuestao, ListTentativas },

})
export default class ProvaDialog extends Vue {

    @Prop() prova!: DBTypes.Prova
    @Prop() editProp!: boolean
    @Prop() add!: boolean

    uid = uid

    db = new DB(this.$axios, this.$store)

    vProva = this.prova

    vNome = this.prova.nome
    modelNome = this.vNome

    vStart = moment(this.prova.inicio).format("DD/MM/YYYY hh:mm A")
    sDate = this.vStart

    vFinish = moment(this.prova.prazo).format("DD/MM/YYYY hh:mm A")
    fDate = this.vFinish

    vMaxTentativas = this.prova.tentativas

    vMaxTempo = (this.prova.tempo > 0) ? `${moment.duration(this.prova.tempo, "seconds").hours()} h ${moment.duration(this.prova.tempo, "seconds").minutes()} m` : 'Sem tempo maximo'
    optionsMaxTempo: qSelectOptions[] = [
        
        { label: "Sem Limite", value: 0},
        { label: "1h", value: 60*60},
        { label: "1h 30m", value: 60*60+60*30},
        { label: "2h", value: 60*60*2},
        { label: "2h 30m", value: 60*60*2+60*30},
        { label: "3h", value: 60*60*3},
        
    ]
    modelMaxTempo = this.optionsMaxTempo.filter(v => { return v.value == this.prova.tempo })[0]

    vDescricao = this.prova.descricao
    modelDescricao = this.vDescricao

    vTurma = (this.$store.state.turmas as DBTypes.Turma[]).find(t => t.idTurma == this.prova.idTurma)
    modelTurma: qSelectOptions | null = (this.vTurma != undefined) ? { label: `${this.vTurma?.nome} - ${this.vTurma?.materia.nome}`, value: this.vTurma as DBTypes.Turma} : null
    optionsTurmas: qSelectOptions[] = []

    vProfessores = this.prova.professor
    modelProfessores: qSelectOptions[] = []
    optionsProfessores: qSelectOptions[] = []

    modelFiles = this.prova.arquivo 

    tab = "i"

    vEdit = this.editProp
    vAdd = this.add

    optionsFDate(date: any) { 
    
        const split = this.sDate.split("/")

        const newSdate = moment(`${split[2].split(" ")[0]}/${split[1]}/${split[0]}`).format("YYYY/MM/DD")

        return date > newSdate
        
    }

    async created() { 

        await this.db.professor.get()

        this.vProfessores.forEach(p => { this.modelProfessores.push({ label: p.nome, value: p }) })
        this.$store.state.professores.forEach((p: DBTypes.Professor) => { this.optionsProfessores.push({ label: p.nome, value: p }) })

        this.$store.state.turmas.forEach((t: DBTypes.Turma) => { this.optionsTurmas.push({ label: t.nome, value: t }) })

    }

    edit() { this.vEdit = !this.vEdit }

    async save() {

        if (this.tab == "i") {

            const inpSDate = this.$refs.inpSDate as any
            const inpFDate = this.$refs.inpFDate as any
            const inpTentativas = this.$refs.inpTentativas as any

            inpSDate.validate()
            inpFDate.validate()
            inpTentativas.validate()

            if (inpSDate.hasError || inpFDate.hasError || inpTentativas.hasError ) { return }           

            
            this.vStart = this.sDate
            this.vFinish = this.fDate

            this.vDescricao = this.modelDescricao

            this.vMaxTempo = (this.modelMaxTempo.value > 0) ? `${moment.duration(this.modelMaxTempo.value, "seconds").hours()} h ${moment.duration(this.modelMaxTempo.value, "seconds").minutes()} m` : 'Sem tempo maximo'

            this.vTurma = this.modelTurma?.value as DBTypes.Turma

            this.vProfessores = this.modelProfessores.map(p => p.value as DBTypes.Professor)

            if (this.vAdd) {

                const prova: DBTypes.Prova = {

                    idProva: -1,
                    nome: this.vNome,
                    prazo: moment(this.vFinish, "DD-MM-YYYY").toDate(),
                    descricao: this.vDescricao,
                    inicio: moment(this.vStart, "DD-MM-YYYY").toDate(),
                    tempo: (parseInt(this.vMaxTempo) != NaN) ? parseInt(this.vMaxTempo) : 0,
                    tentativas: this.vMaxTentativas,
                    professor: this.vProfessores,
                    administrador: [],
                    arquivo: [],
                    questao: this.prova.questao,
                    materia: this.vTurma.materia,
                    resposta: [],
                    idTurma: this.vTurma.idTurma

                }

                this.vProva = await this.db.prova.create(prova)

                new Discord((this.modelTurma?.value as DBTypes.Turma).discord.provasTrabalhos, this.$store).prova.add(this.vProva)

            }
            else {

                const prova: DBTypes.Prova = {

                    idProva: this.prova.idProva,
                    nome: this.vNome,
                    prazo: moment(this.vFinish, "DD-MM-YYYY").toDate(),
                    descricao: this.vDescricao,
                    inicio: moment(this.vStart, "DD-MM-YYYY").toDate(),
                    tempo: (parseInt(this.vMaxTempo) != NaN) ? parseInt(this.vMaxTempo) : 0,
                    tentativas: this.vMaxTentativas,
                    professor: this.vProfessores,
                    administrador: [],
                    arquivo: [],
                    questao: this.prova.questao,
                    materia: this.vTurma.materia,
                    resposta: [],
                    idTurma: this.vTurma.idTurma

                }

                this.db.prova.update(prova)

                new Discord((this.modelTurma?.value as DBTypes.Turma).discord.provasTrabalhos, this.$store).prova.update(this.vProva)

            }

        }
        else {

            // SALVA DB QUESTOES

        }

        this.vEdit = false
        this.vAdd = false

    }

    remove() {

        this.$swal.fire({

            title: "Excluir?",
            icon: "question",
            html: `tem certeza que deseja excluir o trabalho '${this.vProva.nome}'?<br>(Essa ação não pode ser desfeita)`,
            target: '.contentTrabalho',
            backdrop: false,
            showConfirmButton: true,
            showDenyButton: true,
            confirmButtonText: 'Sim',
            denyButtonText: 'Não',
            customClass: {

                popup: "bg-primary",
                content: "text-white",
                title: "text-white",
                confirmButton: "bg-white text-black"

            }

        })
        .then(async r => {

            if (r.isConfirmed) {

                await this.db.prova.delete(this.vProva.idProva)

                new Discord((this.modelTurma?.value as DBTypes.Turma).discord.provasTrabalhos, this.$store).prova.delete(this.vProva)

                console.log("Exclui Trabalho")

                this.$router.go(0)

            }
            else {

                console.log("Não Exclui Trabalho")

            }

        })

    }

    addQuestão() {

        this.vProva.questao.push({idQuestao: -(Math.floor(Math.random() * 9999) + 1), pergunta: "", tipo: 0, questaoCorreta: { idQuestaoCorreta: -1, questaoAlternativa: [] }, questaoAlternativa: []})

    }

    patthenTime = (v: string) => /(^-?[\d]+\/[0-1]\d\/\d{4}) ([0-5]\d:[0-5]\d) *(AM|PM)*$/.test(v)

    patthenDiscord = (v: string) => /(https:\/\/discord.com\/api\/webhooks\/)\d+\/\S+/.test(v)

}

</script>

<style lang="scss">

.q-field__native > span {

    color: white !important;

}

</style>