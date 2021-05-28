<template>
    <q-page>

        <q-page class="lt-sm">

            <div class="q-my-lg">

                <span class="text-bold text-h5 text-white lt-sm">Proximos Dias</span>
                <span class="text-bold text-h4 text-white sm">Proximos Dias</span>

            </div>

            <div v-if="($store.state.typeAccont == 'Professor') || ($store.state.typeAccont == 'Administrador')" class="row wrap justify-around q-ma-md q-gutter-sm">

                <q-btn color="primary" label="Nova Aula" class="br-10"/>
                <q-btn color="primary" label="Nova Atividade" class="br-10"/>
                <q-btn color="primary" label="Novo Trabalho" class="br-10"/>
                <q-btn color="primary" label="Nova Prova" class="br-10"/>

            </div>

            <div class="column wrap justify-between lt-sm items-center">

                <div class="q-mb-md">
                    
                    <q-date v-model="date" today-btn minimal/>
                    
                </div>

                <div class="w-100">

                    <DayCard v-for="d in $store.state.days" :key="d.id" :day="d.day" :atividades="d.atividades" :aulas="d.aulas" :provas="d.provas" :trabalhos="d.trabalhos" />

                </div>

            </div>

        </q-page>

        <q-page class="q-pa-md sm">

            <div class="q-my-lg">

                <span class="text-bold text-h4 text-white">Proximos Dias</span>

            </div>

            <div v-if="($store.state.typeAccont == 'Professor') || ($store.state.typeAccont == 'Administrador')" class="row wrap justify-around q-ma-md q-gutter-sm">

                <q-btn color="primary" label="Nova Aula" class="br-10"/>
                <q-btn color="primary" label="Nova Atividade" class="br-10"/>
                <q-btn color="primary" label="Novo Trabalho" class="br-10"/>
                <q-btn color="primary" label="Nova Prova" class="br-10"/>

            </div>

            <div class="column wrap justify-between items-center">

                <div class="q-mb-md">
                    
                    <q-date v-model="date" today-btn />
                    
                </div>

                <div class="w-100">

                    <DayCard v-for="d in $store.state.days" :key="d.id" :day="d.day" :atividades="d.atividades" :aulas="d.aulas" :provas="d.provas" :trabalhos="d.trabalhos" />

                </div>

            </div>

        </q-page>

        <q-page class="q-pa-md gt-sm">

            <div class="q-my-lg">

                <span class="text-bold text-h4 text-white">Proximos Dias</span>

            </div>

            <div v-if="($store.state.typeAccont == 'Professor') || ($store.state.typeAccont == 'Administrador')" class="row wrap justify-around q-ma-md q-gutter-sm">

                <q-btn color="primary" label="Nova Aula" class="br-10"/>
                <q-btn color="primary" label="Nova Atividade" class="br-10"/>
                <q-btn color="primary" label="Novo Trabalho" class="br-10"/>
                <q-btn color="primary" label="Nova Prova" class="br-10"/>

            </div>

            <div class="row wrap justify-between">

                <div class="w-75">

                    <DayCard v-for="d in $store.state.days" :key="d.id" :day="d.day" :atividades="d.atividades" :aulas="d.aulas" :provas="d.provas" :trabalhos="d.trabalhos"/>

                </div>

                <div>
                    
                    <q-date v-model="date" today-btn />
                    
                </div>

            </div>

        </q-page>

    </q-page>
</template>

<script lang="ts">

import { DayCardType } from 'src/components/models';
import { Vue, Component } from 'vue-property-decorator';

import DayCard from '../components/DayCard.vue'

import moment from 'moment'

@Component({

    components: { DayCard }

})
export default class Home extends Vue {
   
    date = moment().format("YYYY/MM/DD")

}

</script>

<style lang="scss">

body {

    background-image: url("../assets/Fundo Home.png");
    background-size: cover;

}

</style>