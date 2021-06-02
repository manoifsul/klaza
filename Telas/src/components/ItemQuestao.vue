<template>

    <div>

        <q-item :class="edit || view ? 'item br-t-10' : 'item br-10'">

            <q-item-section avatar>
            
                <span>{{number}}</span>

            </q-item-section>

            <q-item-section :class="edit ? `w-100` : ''" avatar>
                
                <span v-if="!edit">{{vQuestion}}</span>

                <q-input v-if="edit" v-model="vQuestion" class="w-100" autofocus outlined label-color="white" input-class="text-white" type="textarea" label="Resposta" placeholder="Digite a resposta" required/>

            </q-item-section>

            <q-space />

            <q-item-section side>
                <div class="text-white q-gutter-xs">
                    <q-btn @click="view = !view; edit = false" class="gt-xs" size="12px" flat dense round icon="fas fa-eye" />
                    <q-btn @click="edit = !edit; view = false" class="gt-xs" size="12px" flat dense round icon="fas fa-pen" />
                    <q-btn @click="remove()" size="12px" flat dense round icon="fas fa-trash" />
                </div>
            </q-item-section>

        </q-item>

        <div v-if="view" class="item2 q-pa-md br-b-10 text-h5">

            <div v-if="vType == 0"> 
                
                <span>Tipo questão: Texto</span><br>
                <span>Respostas validas: {{getValidas()}}</span>

            </div>

            <div v-if="vType == 1"> 
                
                <span>Tipo questão: Simples</span><br>
                <span>Resposta valida: {{getValida()}}</span>

            </div>

            <div v-if="vType == 2"> 
                
                <span>Tipo questão: Multipla</span><br>
                <span>Resposta valida: {{getValidas()}}</span>

            </div>

            <div v-if="vType == 3"> 
                
                <span>Tipo questão: Upload</span><br>
                <span>Resposta valida: O aluno deve enviar um arquivo como resposta</span>

            </div>

        </div>

        <form @submit.prevent="save()" v-if="edit" class="item2 q-pa-md br-b-10 text-h5">

            <q-select outlined input-class="text-white" label-color="white" label="Tipo Questão" v-model="modelType" :options="optionsType" />

            <div v-if="modelType.value == 0" class="q-mt-md">

                <span class="q-mb-md">Respostas certas</span>

                <Alternativas :alternativas="modelAlternativas" :type="modelType.value"/>

                <div class="text-center">

                    <q-btn label="Adicionar Resposta" class="q-mt-md" color="primary" @click="addResposta()"/>

                </div>

            </div>

            <div v-if="modelType.value == 1" class="q-mt-md">

                <span class="q-mb-md">Alternativas (marcada esta correta)</span>

                <Alternativas :alternativas="modelAlternativas" :type="modelType.value" />

                <div class="text-center">

                    <q-btn label="Adicionar Alternativa" class="q-mt-md" color="primary" @click="addResposta()"/>

                </div>

            </div>

            <div v-if="modelType.value == 2" class="q-mt-md">

                <span class="q-mb-md">Alternativas (marcadas estão corretas)</span>

                <Alternativas :alternativas="modelAlternativas" :type="modelType.value" />

                <div class="text-center">

                    <q-btn label="Adicionar Alternativa" class="q-mt-md" color="primary" @click="addResposta()"/>

                </div>

            </div>

            <div class="row justify-center q-mt-md">

                <q-btn type="submit" label="Salvar" color="primary"/>

            </div>            

        </form>

    </div>

</template>

<script lang="ts">

import { Vue, Component, Prop } from 'vue-property-decorator';

import Alternativas from 'src/components/Alternativas.vue'

import * as DBTypes from "src/@types/DB";
import { qSelectOptions } from 'src/@types/vue';
import Trabalho from './TrabalhoDialog.vue';

@Component({

    components: { Alternativas }

})
export default class ItemQuestao extends Vue {

    @Prop() question!: string
    @Prop() correta!: DBTypes.QuestaoCorreta[]
    @Prop() alternativas!: DBTypes.QuestaoAlternativa[]
    @Prop() type!: number
    @Prop() number!: number
    @Prop() idTrabalhoProva!: number
    @Prop() id!: number

    vQuestion = this.question
    vCorreta = this.correta
    vAlternativas = this.alternativas
    vType = this.type

    modelCorreta: DBTypes.QuestaoCorreta[] = []
    modelAlternativas:DBTypes.QuestaoAlternativa[] = []

    view = false
    edit = (this.id < 0) ? true : false

    optionsType: qSelectOptions[] = [{value: 0, label: "Texto"}, {value: 1, label: "Simples"}, {value: 2, label: "Multipla"}, {value: 3, label: "Upload"}]
    modelType = this.optionsType.find(o => { return o.value == this.type }) as qSelectOptions

    created() {

        for (let c in this.vCorreta) { this.modelCorreta.push(this.vCorreta[c]) }

        for (let a in this.vAlternativas) { this.modelAlternativas.push({ idQuestaoAlternativa: this.vAlternativas[a].idQuestaoAlternativa, resposta: this.vAlternativas[a].resposta }) }

    }

    getValidas() {

        const validas: string[] = []

        for (let c in this.correta) {

            console.log(this.correta)

            validas.push(this.alternativas.filter(a => { return a.idQuestaoAlternativa == this.correta[c].idQuestaoCorreta })[0].resposta)

        }

        return validas.join(", ")

    }

    getValida() { return this.alternativas.find(a => {return a.idQuestaoAlternativa == this.correta[0].idQuestaoCorreta })?.resposta }

    getCorretas() { return this.alternativas.filter(v => { return this.correta.some(c => c.idQuestaoCorreta == v.idQuestaoAlternativa) }) }

    addResposta() {

        const id = Math.floor(Math.random() * 100) + 1

        this.modelAlternativas.push({ idQuestaoAlternativa: id, resposta: "" })
        if (this.modelType.value == 0) { this.modelCorreta.push({ idQuestaoCorreta: id, questaoAlternativa: this.modelAlternativas }) }

    }

    removeResposta(id: number) {

        const index = this.modelAlternativas.indexOf(this.modelAlternativas.find(v => { return v.idQuestaoAlternativa == id }) as DBTypes.QuestaoAlternativa)

        if (this.modelCorreta.find(c => c.idQuestaoCorreta == id)) { this.modelCorreta.splice(this.modelCorreta.findIndex(c => c.idQuestaoCorreta == id), 1) }

        this.modelAlternativas.splice(index, 1)

    }

    save() {

        if (!this.vQuestion) {

            this.$swal.fire({

                title: "Erro na questão",
                text: "A questão deve ter uma pergunta",
                icon: "error",
                target: '.contentTrabalho',
                backdrop: false,
                customClass: {

                    popup: "bg-primary",
                    content: "text-white",
                    title: "text-white",
                    confirmButton: "bg-white text-black"

                }

            })
        
            return 

        }

        if ( (this.modelType.value == 1 || this.modelType.value == 2) && this.modelAlternativas.length < 2) { 
            
            this.$swal.fire({

                title: "Erro na questão",
                text: "A questão deve ter no minimo duas alternativas",
                icon: "error",
                target: '.contentTrabalho',
                backdrop: false,
                customClass: {

                    popup: "bg-primary",
                    content: "text-white",
                    title: "text-white",
                    confirmButton: "bg-white text-black"

                }

            })
        
            return 
            
        }

        if (this.modelType.value == 1 && this.modelCorreta.length < 1) { 
            
            this.$swal.fire({

                title: "Erro na questão",
                text: "A questão deve ter no minimo uma alternativa correta",
                icon: "error",
                target: '.contentTrabalho',
                backdrop: false,
                customClass: {

                    popup: "bg-primary",
                    content: "text-white",
                    title: "text-white",
                    confirmButton: "bg-white text-black"

                }

            })
        
            return 
            
        }

        if (this.modelType.value == 1 && this.modelCorreta.length == 0) { 
            
            this.$swal.fire({

                title: "Erro na questão",
                text: "A questão deve ter no minimo uma resposta correta",
                icon: "error",
                target: '.contentTrabalho',
                backdrop: false,
                customClass: {

                    popup: "bg-primary",
                    content: "text-white",
                    title: "text-white",
                    confirmButton: "bg-white text-black"

                }

            })
        
            return 
            
        }

        if (this.modelType.value == 2 && this.modelCorreta.length < 2) { 
            
            this.$swal.fire({

                title: "Erro na questão",
                text: "A questão deve ter no minimo duas respostas corretas",
                icon: "error",
                target: '.contentTrabalho',
                backdrop: false,
                customClass: {

                    popup: "bg-primary",
                    content: "text-white",
                    title: "text-white",
                    confirmButton: "bg-white text-black"

                }

            })
        
            return 
            
        }

        if (this.modelType.value == 0 && this.modelAlternativas.length == 0) { 
            
            this.$swal.fire({

                title: "Erro na questão",
                text: "A questão deve ter no minimo uma resposta correta",
                icon: "error",
                target: '.contentTrabalho',
                backdrop: false,
                customClass: {

                    popup: "bg-primary",
                    content: "text-white",
                    title: "text-white",
                    confirmButton: "bg-white text-black"

                }

            })
        
            return 
            
        }



        // SALVA DB

        for (let c in this.modelCorreta) { this.correta = this.modelCorreta }
        for (let a in this.modelAlternativas) { this.alternativas = this.modelAlternativas }

        this.vType = this.modelType.value as number

        this.edit = false
        this.view = true

    }

    remove() {

        const parent = this.$parent.$parent.$parent.$parent as Trabalho

        console.log(parent)

        const index = parent.questoes.indexOf(parent.questoes.find(v => { return v.idQuestao == this.id }) as DBTypes.Questao)

        parent.questoes.splice(index, 1)

    }

}

</script>

<style lang="scss">

.w-100 {

    width: 100% !important;

}

</style>