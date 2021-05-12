<template>
  
    <div class="q-mb-xl">

        <div class="bg-primary text-h4 text-bold text-white q-pa-md br-t-10">
            
            {{day}}
            
        </div>
        <div class="box q-pa-lg br-t-0 br-b-10 text-center">

            <div v-if="events.length == 0" :class="sizeTextNull">Nenhum evento encontrado para esse dia </div>

            <EventCard v-for="e in events" :event="e" :key="`${e.type}/${e.id}`"/>

        </div>


    </div>

</template>

<script lang="ts">
import { Vue, Component, Prop,  } from 'vue-property-decorator';

import EventCard from 'components/EventCard.vue'

import { Event } from './models';

@Component({

    components: { EventCard }

})
export default class Day extends Vue {

    @Prop() day!: string
    @Prop() events!: Event[]

    get sizeTextNull() { return (this.$q.screen.gt.xs ? 'text-h4 text-bold text-white' : 'text-h5 text-bold text-white') }

}

</script>