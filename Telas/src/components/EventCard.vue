<template>
    
    <div class="bg-accent text-white relative-position br-10 text-bold q-pb-md shadow-5 cursor-pointer q-mb-lg" @click="click()" v-ripple>

        <div class="w-fit q-ml-sm">{{event.time}}</div>

        <div :class="`${sizeText} text-bold text-left q-ml-md`">{{event.type}}: {{event.name}} - {{event.materia}}</div>

    </div>

</template>

<script lang='ts'>

import { Vue, Component, Prop } from 'vue-property-decorator'

import { Event } from './models'

@Component
export default class AulaCard extends Vue {

    @Prop() event!: Event

    click() { this.$router.push(`/${this.event.type.toLowerCase()}/${this.event.id}`) }

    get sizeText() { 

        if (this.$q.screen.gt.md) { return "text-h3" }
        else if (this.$q.screen.gt.xs) { return "text-h4" }
        else { return "text-h6" }

    }

}

</script>