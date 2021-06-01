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

                <q-item v-if="x > 0">

                    <a :href="`/tentativas/${t[0].questao.id}/${t[0].aluno.id}/${t[0].num_tentativa}`">Tentativa: {{t[0].num_tentativa}}</a>

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

@Component({

    components: {},

})
export default class ListTentativas extends Vue {

    @Prop() respostas!: TypesDB.Resposta[]
    
    sortTentativas: sortTentativas = {}

    uid = uid

    created() {

        const alunosMap = this.respostas.map(r => r.aluno)

        const alunos: TypesDB.Aluno[] = []

        alunosMap.forEach(a => { if (!alunos.map(a => a.id).includes(a.id)) { alunos.push(a) } })

        for (let a in alunos) {

            const respostasAluno = this.respostas.filter(r => r.aluno.id == alunos[a].id)
            const nums_tentativas = Array.from(new Set(respostasAluno.map(r => r.num_tentativa)))

            nums_tentativas.forEach(n => {

                if (this.sortTentativas[alunos[a].nome] == undefined) { this.sortTentativas[alunos[a].nome] = [[]] }

                if (this.sortTentativas[alunos[a].nome][n] == undefined) { this.sortTentativas[alunos[a].nome][n] = respostasAluno.filter(r => r.num_tentativa == n) }

            })

        }

    }


}

</script>