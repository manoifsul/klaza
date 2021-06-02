<template>

    <div>

        <div v-if="type == 0">

            <div v-for="q in Questao.questaoAlternativa" :key="`Questao@${q.idQuestaoAlternativa}`" class="row">

                <q-input class="col-grow" v-model="q.resposta" autofocus outlined label-color="white" input-class="text-white" label="Resposta" type="textarea" placeholder="Digite a resposta" required/>
                <q-btn @click="parent.removeResposta(q.idQuestaoAlternativa)" flat dense icon="fas fa-trash" />

            </div>

        </div>

        <div v-if="type == 1" class="column">

            <div v-for="q in Questao.questaoAlternativa" :key="`Questao@${q.idQuestaoAlternativa}`" class="row">

                <q-radio v-model="modelRadio" @input="changeRadio()" :val="q.idQuestaoAlternativa" color="white"/>
                <q-input v-model="q.resposta" class="col-grow" outlined label-color="white" input-class="text-white" placeholder="Digite a resposta" required/>
                <q-btn @click="parent.removeResposta(q.idQuestaoAlternativa)" flat dense icon="fas fa-trash" />

            </div>

        </div>

        <div v-if="type == 2" class="column">

            <div v-for="q in Questao.questaoAlternativa" :key="`Questao@${q.idQuestaoAlternativa}`" class="row">

                <q-checkbox v-model="parent.modelCorreta" :val="q.idQuestaoAlternativa" color="white" />
                <q-input v-model="q.resposta" class="col-grow" outlined label-color="white" input-class="text-white" placeholder="Digite a resposta" required/>
                <q-btn @click="parent.removeResposta(q.idQuestaoAlternativa)" flat dense icon="fas fa-trash" />

            </div>

        </div>

    </div>

</template>

<script lang="ts">

import { Vue, Component, Prop } from 'vue-property-decorator';

import * as DBTypes from "src/@types/DB";
import { qSelectOptions } from 'src/@types/vue';

import ItemQuestao from './ItemQuestao.vue';

@Component({

    components: {}

})
export default class Alternativas extends Vue {

    @Prop() Questao!: DBTypes.Questao
    @Prop() type!: number

    a = this.Questao.questaoAlternativa

    parent = this.$parent as ItemQuestao

    modelRadio = this.parent.modelCorreta[0]

    changeRadio() { this.parent.modelCorreta[0] = this.modelRadio }

}

</script>