<template>
    <q-page>

        <q-page class="lt-sm">

            <div class="q-my-lg">

                <span class="text-bold text-h5 text-white lt-sm">Proximos Dias</span>
                <span class="text-bold text-h4 text-white sm">Proximos Dias</span>

            </div>

            <div v-if="($store.state.typeUser == 'professor') || ($store.state.typeUser == 'administrador')" class="row wrap justify-around q-ma-md q-gutter-sm">

                <q-btn color="primary" label="Nova Aula" class="br-10"/>
                <q-btn color="primary" label="Nova Atividade" class="br-10"/>
                <q-btn color="primary" label="Novo Trabalho" class="br-10"/>
                <q-btn color="primary" label="Nova Prova" class="br-10"/>

            </div>

            <div class="column wrap justify-between lt-sm items-center">

                <div class="w-100">

                    <DayCard v-for="d in $store.state.days" :key="d.id" :day="d.day" :atividades="d.atividades" :aulas="d.aulas" :provas="d.provas" :trabalhos="d.trabalhos" />

                </div>

            </div>

        </q-page>

        <q-page class="q-pa-md sm">

            <div class="q-my-lg">

                <span class="text-bold text-h4 text-white">Proximos Dias</span>

            </div>

            <div v-if="($store.state.typeUser == 'professor') || ($store.state.typeUser == 'administrador')" class="row wrap justify-around q-ma-md q-gutter-sm">

                <q-btn color="primary" label="Nova Aula" class="br-10"/>
                <q-btn color="primary" label="Nova Atividade" class="br-10"/>
                <q-btn color="primary" label="Novo Trabalho" class="br-10"/>
                <q-btn color="primary" label="Nova Prova" class="br-10"/>

            </div>

            <div class="column wrap justify-between items-center">

                <div class="w-100">

                    <DayCard v-for="d in $store.state.days" :key="d.id" :day="d.day" :atividades="d.atividades" :aulas="d.aulas" :provas="d.provas" :trabalhos="d.trabalhos" />

                </div>

            </div>

        </q-page>

        <q-page class="q-pa-md gt-sm">

            <div class="q-my-lg">

                <span class="text-bold text-h4 text-white">Proximos Dias</span>

            </div>

            <div v-if="($store.state.typeUser == 'professor') || ($store.state.typeUser == 'administrador')" class="row wrap justify-around q-ma-md q-gutter-sm">

                <q-dialog v-model="dialogAula" full-width> <AulaDialog nome="" materia="" dia="" descricao="" :editProp="true"/> </q-dialog>
                <q-dialog v-model="dialogAtividade" full-width> <AtividadeDialog nome="" materia="" dia="" descricao="" :editProp="true"/> </q-dialog>
                <q-dialog v-model="dialogTrabalho" full-width> <TrabalhoDialog nome="" materia="" dia="" descricao="" :editProp="true"/> </q-dialog>
                <q-dialog v-model="dialogProva" full-width> <ProvaDialog nome="" materia="" dia="" descricao="" :editProp="true"/> </q-dialog>

                <q-btn color="primary" label="Nova Aula" class="br-10" @click="dialogAula = true"/>
                <q-btn color="primary" label="Nova Atividade" class="br-10"  @click="dialogAtividade = true"/>
                <q-btn color="primary" label="Novo Trabalho" class="br-10"  @click="dialogTrabalho = true"/>
                <q-btn color="primary" label="Nova Prova" class="br-10"  @click="dialogProva = true"/>

            </div>

            <div class="row wrap justify-between">

                <div class="w-100">

                    <DayCard v-for="d in $store.state.days" :key="d.id" :day="d.day" :atividades="d.atividades" :aulas="d.aulas" :provas="d.provas" :trabalhos="d.trabalhos"/>

                </div>

            </div>

        </q-page>

    </q-page>
</template>

<script lang="ts">

import { Vue, Component } from 'vue-property-decorator';
import { DB } from '../middlewares/DBContector'

import DayCard from '../components/DayCard.vue'
import AulaDialog from '../components/AulaDialog.vue'
import AtividadeDialog from '../components/AtividadeDialog.vue'
import TrabalhoDialog from '../components/TrabalhoDialog.vue'
import ProvaDialog from '../components/ProvaDialog.vue'

import moment from 'moment'

@Component({

    components: { DayCard, AulaDialog, AtividadeDialog, TrabalhoDialog, ProvaDialog }

})
export default class Home extends Vue {
   
    date = moment().format("YYYY/MM/DD")

    dialogAula = false
    dialogAtividade = false
    dialogTrabalho = false
    dialogProva = false

    created() {

        const db = new DB(this.$axios, this.$store)

        db.dias.get()

    }

}

</script>

<style lang="scss">

body {

    background-image: url("../assets/Fundo Home.png");
    background-size: cover;

}

</style>