<template>
    
    <div>

        <div class="bg-accent text-white relative-position br-10 text-bold q-pb-md shadow-5 cursor-pointer q-mb-lg" @click="open = true" v-ripple>

            <div class="w-fit q-ml-sm">{{getTime()}}</div>

            <div :class="`${sizeText} text-bold text-left q-ml-md`">Aula: {{event.name}} - {{event.materia.name}}</div>

        </div>

        <q-dialog v-model="open" full-width> <Aula :nome="event.name" :materia="event.materia" :dia="event.dia" :descricao="event.descricao"/> </q-dialog>

    </div>

</template>

<script lang='ts'>

import { Vue, Component, Prop } from 'vue-property-decorator'

import Aula from 'components/Aula.vue'


import moment from "moment";
import { AulaCardType } from './models';

@Component({

    components: { Aula }

})
export default class AulaCard extends Vue {

    @Prop() event!: AulaCardType

    open = false

    getTime() { return moment(this.event.dia).format("hh:mm A") }

    get sizeText() { 

        if (this.$q.screen.gt.md) { return "text-h3" }
        else if (this.$q.screen.gt.xs) { return "text-h4" }
        else { return "text-h6" }

    }

}

</script>