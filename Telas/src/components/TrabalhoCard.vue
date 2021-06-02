<template>
    
    <div>

        <div class="bg-accent text-white relative-position br-10 text-bold q-pb-md shadow-5 cursor-pointer q-mb-lg" @click="open = true" v-ripple>

            <div class="w-fit q-ml-sm">{{getTime()}}</div>

            <div :class="`${sizeText} text-bold text-left q-ml-md`">Trabalho: {{event.nome}} - {{event.materia.nome}}</div>

        </div>

        <q-dialog v-model="open" full-width> <TrabalhoDialog :nome="event.nome" :materia="event.materia" :finish="event.prazo" :id="event.idTrabalho" :maxTempo="event.tempo" :descricao="event.descricao" :maxTentativas="event.tentativas" :start="event.inicio" :questoes="event.questao" :respostas="event.resposta"/> </q-dialog>

    </div>

</template>

<script lang='ts'>

import { Vue, Component, Prop } from 'vue-property-decorator'
import * as DBTypes from '../@types/DB'

import TrabalhoDialog from 'src/components/TrabalhoDialog.vue'

import moment from "moment";

@Component({

    components: { TrabalhoDialog }

})
export default class TrabalhoCard extends Vue {

    @Prop() event!: DBTypes.Trabalho

    open = false

    getTime() { return moment(this.event.inicio).format("hh:mm A") }

    get sizeText() { 

        if (this.$q.screen.gt.md) { return "text-h3" }
        else if (this.$q.screen.gt.xs) { return "text-h4" }
        else { return "text-h6" }

    }

}

</script>