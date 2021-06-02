<template>
  
    <div class="q-mb-xl">

        <div class="bg-primary text-h4 text-bold text-white q-pa-md br-t-10">
            
            {{day}}
            
        </div>
        <div class="box q-pa-lg br-t-0 br-b-10 text-center">

            <div v-if="aulas.length == 0 && atividades.length == 0 && trabalhos.length == 0 && provas.length == 0" :class="sizeTextNull">Nenhum evento encontrado para esse dia </div>

            <AulaCard v-for="e in aulas" :event="e" :key="`Aula/${e.idAula}`"/>
            <TrabalhoCard v-for="e in trabalhos" :event="e" :key="`Trabalho/${e.idTrabalho}`"/>

        </div>


    </div>

</template>

<script lang="ts">
import { uid } from 'quasar'
import { Vue, Component, Prop,  } from 'vue-property-decorator';

import * as DBTypes from '../@types/DB'

import AulaCard from 'src/components/AulaCard.vue'
import TrabalhoCard from 'src/components/TrabalhoCard.vue'


@Component({

    components: { AulaCard, TrabalhoCard }

})
export default class Day extends Vue {

    @Prop() day!: string
    @Prop() aulas!: DBTypes.Aula[] 
    @Prop() atividades!: DBTypes.Atividade[] 
    @Prop() provas!: DBTypes.Prova[] 
    @Prop() trabalhos!: DBTypes.Trabalho[] 


    uid() { return uid() }

    get sizeTextNull() { return (this.$q.screen.gt.xs ? 'text-h4 text-bold text-white' : 'text-h5 text-bold text-white') }

}

</script>