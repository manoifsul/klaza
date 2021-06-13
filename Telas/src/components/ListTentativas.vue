<template>
    
    <div>

        <q-expansion-item v-for="(a, i) in sortTentativas" :key="uid()" class="item br-10 text-white" expand-icon-class="text-white">

            <template v-slot:header>

                <q-item-section>
                    
                    Aluno: {{i}}

                </q-item-section>

                <q-space />

            </template>

            <div v-for="(t, x) in sortTentativas[i]" :key="uid()">

                <q-item v-if="sortTentativas[i][x] != undefined && sortTentativas[i][x][0] != undefined">

                    <span class="text-white cursor-pointer" style="text-decoration: underline;" @click="goToTentativa(`/tentativas/${negocio}/${idNegocio}/${sortTentativas[i][x][0].idAluno}/${sortTentativas[i][x][0].nroTentativa}`)">Tentativa: {{sortTentativas[i][x][0].nroTentativa}}</span>
                    
                </q-item>

            </div>

        </q-expansion-item>

    </div>

</template>

<script lang="ts">

import { Vue, Component, Prop } from 'vue-property-decorator';

import { uid } from 'quasar'

import * as TypesDB from 'src/@types/DB'
import { sortTentativas } from 'src/@types/vue';

import { DB } from 'src/middlewares/DBContector'

@Component({

    components: {},

})
export default class ListTentativas extends Vue {

    @Prop() idNegocio!: number
    @Prop() respostas!: TypesDB.Resposta[]
    @Prop() negocio!: "trabalho" | "prova"
    
    sortTentativas: sortTentativas = {}

    uid = uid

    db = new DB(this.$axios, this.$store)

    created() {

        this.respostas.map(r => r.idAluno).forEach(a => { this.db.aluno.get(a) })

        const alunos = this.$store.state.alunos as TypesDB.Aluno[]

        for (let a in alunos) {

            const respostasAluno = this.respostas.filter(r => r.idAluno == alunos[a].idAluno)
            const nums_tentativas = Array.from(new Set(respostasAluno.map(r => r.nroTentativa)))

            nums_tentativas.forEach(n => {

                if (this.sortTentativas[alunos[a].nome] == undefined) { this.sortTentativas[alunos[a].nome] = [[]] }

                if (this.sortTentativas[alunos[a].nome][n] == undefined) { this.sortTentativas[alunos[a].nome][n] = respostasAluno.filter(r => r.nroTentativa == n) }

            })

        }

    }

    goToTentativa(link: string) {

        this.$router.push(link)

    }


}

</script>