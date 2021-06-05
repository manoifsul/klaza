<template>
    <q-page class="q-pa-md">

        <div class="q-my-lg">

            <span class="text-bold text-h4 text-white">Notas</span>

        </div>

        <q-list class="q-pa-md">

            <q-expansion-item v-for="(nm, m) in sortNotas" :key="uid()" class="item br-10 text-white" :label="m" expand-icon-class="text-white">

                <div>
                   
                    Provas

                </div> 

                <q-item v-if="nm.Provas.provas.length == 0">

                    Nenhuma prova encontrada

                </q-item>
                <q-item v-else v-for="p in nm.Provas.provas" :key="`Prova/${p.idProva}`">

                    <span v-if="nm.Provas.notas.filter(n => n.prova.idProva == p.idProva)[0] == undefined">

                         {{p.nome}} - Não respondido

                    </span>
                    <span v-else>

                        {{p.nome}} - {{nm.Provas.notas.filter(n => n.prova.idProva == p.idProva)[0].valor}}

                    </span>

                </q-item> 

                <div>
                   
                    Trabalhos

                </div>

                <q-item v-if="nm.Trabalhos.trabalhos.length == 0">

                    Nenhuma prova encontrada

                </q-item>
                <q-item v-else v-for="t in nm.Trabalhos.trabalhos" :key="`Trabalho/${t.idTrabalho}`">
                   
                    {{t.nome}} - {{nm.Trabalhos.notas.filter(n => n.trabalho.idTrabalho == t.idTrabalho)[0].valor}}

                </q-item> 

            </q-expansion-item>

        </q-list>

    </q-page>
</template>

<script lang="ts">

import { Vue, Component } from 'vue-property-decorator';
import { DB } from '../middlewares/DBContector'
import { uid } from 'quasar'

import moment from 'moment'
import { sortNotas } from 'src/@types/vue';
import { DayCardType } from 'src/components/models';
import { Materia, NotaProva, NotaTrabalho, Prova, Trabalho, Turma } from 'src/@types/DB';

@Component({

    components: { }

})
export default class Notas extends Vue {

    dias: DayCardType[] = []
    sortNotas: sortNotas = {}

    trabalhos: Trabalho[] = []
    provas: Prova[] = []

    turmas: Turma[] = []

    materias: Materia[] = []

    uid = uid

    created() {

        const db = new DB(this.$axios, this.$store)

        db.dias.get()

        console.log(this.turmas)

        this.dias = this.$store.state.allDays

        this.dias.map(d => d.trabalhos).filter(t => t.length > 0).forEach(t => t.map(t => this.trabalhos.push(t)))

        this.dias.map(d => d.provas).filter(p => p.length > 0).forEach(p => p.map(p => this.provas.push(p)))

        this.turmas = this.$store.state.turmas.filter((t: Turma) => t.aluno.map(a => a.idAluno).includes(this.$store.state.idUser))

        this.materias = this.turmas.map(t => t.materia)

        this.materias.forEach(m => { 

            this.sortNotas[m.nome] = { Provas: { provas: this.provas.filter(p => p.materia.idMateria == m.idMateria), notas: this.turmas[0].aluno.find(a => a.idAluno == this.$store.state.idUser)?.notasProvas as NotaProva[] }, Trabalhos: { trabalhos: this.trabalhos.filter(p => p.materia.idMateria == m.idMateria), notas: this.turmas[0].aluno.find(a => a.idAluno == this.$store.state.idUser)?.notasTrabalhos as NotaTrabalho[] } }

        })





    }

}

</script>

<style lang="scss">

body {

    background-image: url("../assets/Fundo Home.png");
    background-size: cover;

}

</style>