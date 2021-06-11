<template>

    <div class="box fit q-pa-md contentAlunoDialog">

        <form @submit.prevent="save()">

            <div class="q-gutter-x-sm row wrap justify-end q-mb-md ">

                <q-btn v-if="!vAdd" @click="vEdit = !vEdit" class="br-10" padding="sm" color="primary" icon="fas fa-pen"/>
                <q-btn v-if="!vAdd" @click="remove()" class="br-10" padding="sm" color="primary" icon="fas fa-trash"/>
                <q-btn class="br-10" padding="sm" color="primary" icon="fas fa-times" v-close-popup/>

            </div>

            <div v-if="!vEdit" class="column text-bold text-h4 text-white q-gutter-y-md">

                <span>Nome: {{vAluno.nome}}</span>
                <span>Matricula: {{vAluno.matricula}}</span>
                <span>Email: {{vAluno.email}}</span>
                <span>Senha: {{vAluno.senha}}</span>
                <span>Turmas: {{$store.state.turmas.filter(t => vAluno.idTurmas.includes(t.idTurma)).map(t => t.nome).join(", ") }}</span>

            </div>
            <div v-else class="column text-bold text-h4 q-gutter-y-md">

                <q-input outlined v-model="modelNome" label-color="white" input-class="text-white" label="Nome" placeholder="Digite o nome do professor" required />
                <q-input outlined v-model="modelLogin" label-color="white" input-class="text-white" label="Login" placeholder="Digite o login do professor" required />
                <q-input outlined v-model="modelEmail" type="email" label-color="white" input-class="text-white" label="Email" placeholder="Digite o email do professor" required />
                <q-input outlined v-model="modelSenha" label-color="white" input-class="text-white" label="Senha" placeholder="Digite a senha do professor" required />
                <q-select outlined v-model="modelTurmas" input-class="text-white" label-color="white" placeholder="Digite a(as) turma(as) do professor" :options="optionsTurmas" label="Turmas" use-chips use-input multiple />

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
export default class AlunoDialog extends Vue {

    @Prop() add!: boolean
    @Prop() aluno!: Aluno

    vAdd = this.add
    vEdit = this.add ? true : false

    vAluno: Aluno = this.aluno

    modelNome = this.vAluno.nome
    modelLogin = this.vAluno.matricula
    modelSenha = this.vAluno.senha
    modelEmail = this.vAluno.email

    modelTurmas: qSelectOptions[] = []
    optionsTurmas: qSelectOptions[] = []

    db = new DB(this.$axios, this.$store)

    created() {

        const turmas = this.$store.state.turmas as Turma[]

        this.vAluno.idTurmas.forEach(t => { 
            
            const turma = turmas.find(t => t.idTurma == t.idTurma) as Turma
            this.modelTurmas.push({ label: turma.nome, value: turma }) 
            
        })
        turmas.forEach(t => { this.optionsTurmas.push({ label: t.nome, value: t }) })

    }

    async save() {

        if (this.vAdd) {

            this.vAluno = await this.db.aluno.create({

                idAluno: -1,
                nome: this.modelNome,
                senha: this.modelSenha,
                email: this.modelEmail,
                matricula: this.modelLogin,
                idTurmas: this.modelTurmas.map(t => t.value as Turma).map(t => t.idTurma),
                notasTrabalhos: this.aluno.notasTrabalhos,
                notasProvas: this.aluno.notasProvas

            })

        }
        else {

            this.vAluno = {

                idAluno: this.vAluno.idAluno,
                nome: this.modelNome,
                senha: this.modelSenha,
                email: this.modelEmail,
                matricula: this.modelLogin,
                idTurmas: this.modelTurmas.map(t => t.value as Turma).map(t => t.idTurma),
                notasTrabalhos: this.aluno.notasTrabalhos,
                notasProvas: this.aluno.notasProvas

            }

            await this.db.aluno.update(this.vAluno)

        }

        this.vAdd = false
        this.vEdit = false

    }

    remove() {

        this.$swal.fire({

            title: "Excluir?",
            text: `Tem certaza que deseja excluir o aluno '${this.vAluno.nome}'?`,
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

            if (swal.isConfirmed) { this.db.aluno.delete(this.vAluno.idAluno); this.hide() }

        })

    }

    hide() {

        const dialog = this.$parent.$parent as any
        dialog.hide()

    }

}

</script>

<style lang="scss">

.q-field__native > span {

    color: white !important;

}

</style>