<template>

    <div class="box fit q-pa-md contentAlunoDialog">

        <form @submit.prevent="save()">

            <div class="q-gutter-x-sm row wrap justify-end q-mb-md ">

                <q-btn v-if="!vAdd" @click="vEdit = !vEdit" class="br-10" padding="sm" color="primary" icon="fas fa-pen"/>
                <q-btn v-if="!vAdd" @click="remove()" class="br-10" padding="sm" color="primary" icon="fas fa-trash"/>
                <q-btn class="br-10" padding="sm" color="primary" icon="fas fa-times" v-close-popup/>

            </div>

            <div v-if="!vEdit" class="column text-bold text-h4 text-white q-gutter-y-md">

                <span>Nome: {{vTurma.nome}}</span>
                <span>Materia: {{vTurma.materia.nome}}</span>
                <span>Professores: {{vTurma.professor.map(p => p.nome).join(", ")}}</span>
                <span>Alunos: {{vTurma.aluno.map(p => p.nome).join(", ")}}</span>
                <span>Webhook Notificações: <a :href="vTurma.discord.notificacoes"> {{vTurma.discord.notificacoes}} </a> </span>
                <span>Webhook Provas e Trabalhos: <a :href="vTurma.discord.provasTrabalhos"> {{vTurma.discord.provasTrabalhos}} </a> </span>

            </div>
            <div v-else class="column text-bold text-h4 q-gutter-y-md">

                <q-input outlined v-model="modelNome" label-color="white" input-class="text-white" label="Nome" placeholder="Digite o nome do professor" required />
                <q-select outlined v-model="modelMateria" input-class="text-white" label-color="white" placeholder="Selecione a materia da turma" :options="optionsMateria" label="Materia" use-chips use-input />
                <q-select outlined v-model="modelProfessor" input-class="text-white" label-color="white" placeholder="Selecione o(s) professor(es) da turma" :options="optionsProfessor" label="Professores" use-chips use-input multiple />
                <q-select outlined v-model="modelAluno" input-class="text-white" label-color="white" placeholder="Selecione o(s) aluno(s) da turma" :options="optionsAluno" label="Alunos" use-chips use-input multiple />
                <q-input outlined v-model="modelDiscord.notificacoes" label-color="white" input-class="text-white" label="Webhook Notificações" placeholder="Digite o webhook das notificações do Discord" :rules="[patthenDiscord]" required />        
                <q-input outlined v-model="modelDiscord.provasTrabalhos" label-color="white" input-class="text-white" label="Webhook Provas e Trabalhos" placeholder="Digite o webhook das provas e trabalhos do Discord" :rules="[patthenDiscord]" required />                        

            </div>
            <div class="row justify-center q-mt-md">

                <q-btn v-if="vEdit" type="submit" label="Salvar" color="primary"/>

            </div>

        </form>

    </div>
</template>

<script lang="ts">

import { Vue, Component, Prop } from 'vue-property-decorator';
import { qSelectOptions } from 'src/@types/vue'

import moment from 'moment';
import { Administrador, Aluno, Materia, Professor, Turma } from 'src/@types/DB';
import { DB } from 'src/middlewares/DBContector';

@Component({

    components: { }

})
export default class TurmaDialog extends Vue {

    @Prop() add!: boolean
    @Prop() turma!: Turma

    vAdd = this.add
    vEdit = this.add ? true : false

    vTurma: Turma = this.turma

    modelNome = this.vTurma.nome

    modelDiscord = this.vTurma.discord

    modelAluno: qSelectOptions[] = []
    optionsAluno: qSelectOptions[] = []

    modelMateria: qSelectOptions | null = (this.add) ? null : { label: this.vTurma.materia.nome, value: this.vTurma.materia }
    optionsMateria: qSelectOptions[] = []

    modelProfessor: qSelectOptions[] = []
    optionsProfessor: qSelectOptions[] = []

    db = new DB(this.$axios, this.$store)

    created() {

        const alunos = this.$store.state.alunos as Aluno[]
        const professores = this.$store.state.professores as Professor[]
        const materias = this.$store.state.materias as Materia[]

        this.vTurma.aluno.forEach(a => { this.modelAluno.push({ label: a.nome, value: a }) })
        alunos.forEach(a => { this.optionsAluno.push({ label: a.nome, value: a }) })

        this.vTurma.professor.forEach(p => { this.modelProfessor.push({ label: p.nome, value: p }) })
        professores.forEach(p => { this.optionsProfessor.push({ label: p.nome, value: p }) })

        materias.forEach(m => { this.optionsMateria.push({ label: m.nome, value: m }) })

    }

    async save() {

        if (this.vAdd) {

            this.vTurma = await this.db.turma.create({

                idTurma: -1,
                nome: this.modelNome,
                discord: this.modelDiscord,
                materia: this.modelMateria?.value as Materia,
                professor: this.modelProfessor.map(p => p.value as Professor),
                aluno: this.modelAluno.map(a => a.value as Aluno),
                aula: this.turma.aula,
                trabalho: this.turma.trabalho,
                atividade: this.turma.atividade,
                prova: this.turma.prova,
                
            })

        }
        else {

            this.vTurma = {

                idTurma: this.vTurma.idTurma,
                nome: this.modelNome,
                discord: this.modelDiscord,
                materia: this.modelMateria?.value as Materia,
                professor: this.modelProfessor.map(p => p.value as Professor),
                aluno: this.modelAluno.map(a => a.value as Aluno),
                aula: this.turma.aula,
                trabalho: this.turma.trabalho,
                atividade: this.turma.atividade,
                prova: this.turma.prova,

            }

            await this.db.turma.update(this.vTurma)

        }

        this.vAdd = false
        this.vEdit = false

    }

    remove() {

        this.$swal.fire({

            title: "Excluir?",
            text: `Tem certaza que deseja excluir o aluno '${this.vTurma.nome}'?`,
            showConfirmButton: true,
            showDenyButton: true,
            confirmButtonText: "Sim",
            denyButtonText: "Não",
            target: ".contentAlunoDialog",
            customClass: {

                popup: "bg-primary",
                content: "text-white",
                title: "text-white",
                confirmButton: "bg-white text-black"

            }

        }).then(swal => {

            if (swal.isConfirmed) { this.db.turma.delete(this.turma.idTurma); this.hide() }

        })

    }

    hide() {

        const dialog = this.$parent.$parent as any
        dialog.hide()

    }

    patthenDiscord = (v: string) => /(https:\/\/discord.com\/api\/webhooks\/)\w+\/\S+/.test(v)

}

</script>

<style lang="scss">

.q-field__native > span {

    color: white !important;

}

</style>