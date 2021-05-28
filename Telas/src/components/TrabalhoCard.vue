<template>
    
    <div>

        <div class="bg-accent text-white relative-position br-10 text-bold q-pb-md shadow-5 cursor-pointer q-mb-lg" @click="open = true" v-ripple>

            <div class="w-fit q-ml-sm">{{getTime()}}</div>

            <div :class="`${sizeText} text-bold text-left q-ml-md`">Trabalho: {{event.name}} - {{event.materia.name}}</div>

        </div>

        <q-dialog v-model="open" full-width> <TrabalhoDialog :nome="event.name" :materia="event.materia" :finish="event.finish" :id="event.id" :maxTempo="event.maxTempo" :descricao="event.descricao" :maxTentativas="event.maxTentativas" :start="event.start" :questoes="event.questoes" :respostas="event.respostas"/> </q-dialog>

    </div>

</template>

<script lang='ts'>

import { Vue, Component, Prop } from 'vue-property-decorator'

import TrabalhoDialog from 'src/components/TrabalhoDialog.vue'


import moment from "moment";
import { TrabalhoCardType } from './models';

@Component({

    components: { TrabalhoDialog }

})
export default class TrabalhoCard extends Vue {

    @Prop() event!: TrabalhoCardType

    open = false

    getTime() { return moment(this.event.start).format("hh:mm A") }

    get sizeText() { 

        if (this.$q.screen.gt.md) { return "text-h3" }
        else if (this.$q.screen.gt.xs) { return "text-h4" }
        else { return "text-h6" }

    }

}

</script>