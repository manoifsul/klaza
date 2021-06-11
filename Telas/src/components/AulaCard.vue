<template>
    
    <div>

        <div class="bg-accent text-white relative-position br-10 text-bold q-pb-md shadow-5 cursor-pointer q-mb-lg" @click="open = true" v-ripple>

            <div class="w-fit q-ml-sm">{{getTime()}}</div>

            <div :class="`${sizeText} text-bold text-left q-ml-md`">Aula: {{event.nome}} - {{event.materia.nome}}</div>

        </div>

        <q-dialog v-model="open" full-width> <AulaDialog :aula="event" :editProp="false" :add="false"/> </q-dialog>

    </div>

</template>

<script lang='ts'>

import { Vue, Component, Prop } from 'vue-property-decorator'
import * as DBTypes from '../@types/DB'

import AulaDialog from 'src/components/AulaDialog.vue'

import moment from "moment";

@Component({

    components: { AulaDialog }

})
export default class AulaCard extends Vue {

    @Prop() event!: DBTypes.Aula

    open = false

    getTime() { return moment(this.event.inicio).format("hh:mm A") }

    get sizeText() { 

        if (this.$q.screen.gt.md) { return "text-h3" }
        else if (this.$q.screen.gt.xs) { return "text-h4" }
        else { return "text-h6" }

    }

}

</script>