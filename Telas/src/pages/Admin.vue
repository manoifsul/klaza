<template>
    <q-page>

        <q-page class="q-pa-md">

            <div class="q-mb-xl">

                <span class="text-bold text-h4 text-white">Administação</span>

            </div>

            <div class="text-center q-mb-xl">

                <q-btn class="bg-primary text-white" @click="buttonAdd()">Adicionar {{tab}}</q-btn>
                <q-dialog v-if="tab == 'administrador'" v-model="dialog" @hide="dialogClose" full-width> <AdmDialog :administrador="administrador" :add="add"/> </q-dialog>
                <q-dialog v-if="tab == 'professor'" v-model="dialog" @hide="dialogClose" full-width> <ProfessorDialog :professor="professor" :add="add"/> </q-dialog>
                <q-dialog v-if="tab == 'aluno'" v-model="dialog" @hide="dialogClose" full-width> <AlunoDialog :aluno="aluno" :add="add"/> </q-dialog>
                <q-dialog v-if="tab == 'turma'" v-model="dialog" @hide="dialogClose" full-width> <TurmaDialog :turma="turma" :add="add"/> </q-dialog>

            </div>

            <q-tabs class="text-white bg-primary br-t-20" align="justify" v-model="tab">

                <q-tab name="administrador" label="Administradores" @click="getADM()" />
                <q-tab name="professor" label="Professores" @click="getProfessor()" />
                <q-tab name="aluno" label="Alunos" @click="getAluno()" />
                <q-tab name="turma" label="Turmas" @click="getTurma()" />

            </q-tabs>

            <q-tab-panels class="box br-t-0" v-model="tab">

                <q-tab-panel name="administrador">

                    <q-table :data="$store.state.administradores" :columns="columns.adm" row-key="idAdministrador" class="bg-transparent text-white" flat @row-click="rowClick"/>

                </q-tab-panel>

                <q-tab-panel name="professor">

                    <q-table :data="$store.state.professores" :columns="columns.professor" row-key="idPorfessor" class="bg-transparent text-white" flat @row-click="rowClick"/>

                </q-tab-panel>

                <q-tab-panel name="aluno">

                    <q-table :data="$store.state.alunos" :columns="columns.aluno" row-key="idAluno" class="bg-transparent text-white" flat @row-click="rowClick"/>

                </q-tab-panel>

                <q-tab-panel name="turma">

                    <q-table :data="$store.state.turmas" :columns="columns.turmas" row-key="idTurma" class="bg-transparent text-white" flat @row-click="rowClick"/>

                </q-tab-panel>

            </q-tab-panels>

        </q-page>

    </q-page>
</template>

<script lang="ts">

import { ColumnsTableAdm } from 'src/@types/vue';
import { Vue, Component } from 'vue-property-decorator';
import { DB } from '../middlewares/DBContector'

import AdmDialog from '../components/AdmDialog.vue'
import ProfessorDialog from '../components/ProfessorDialog.vue'
import AlunoDialog from '../components/AlunoDialog.vue'
import TurmaDialog from '../components/TurmaDialog.vue'

import { Administrador, Aluno, Materia, Professor, Turma } from 'src/@types/DB';

@Component({

    components: { AdmDialog, ProfessorDialog, AlunoDialog, TurmaDialog }

})
export default class Admin extends Vue {
   
    tab: "administrador" | "professor" | "aluno" | "turma" = "administrador"

    dialog = false

    add = false

    db = new DB(this.$axios, this.$store)

    columns: ColumnsTableAdm = {

        adm: [

            { name: "idAdministrador", label: "ID", field: "idAdministrador", sortable: true, align: "left", sortOrder: "ad" },
            { name: "nome", label: "Nome", field: "nome", sortable: true, align: "left" },
            { name: "login", label: "Login", field: "login", sortable: true, align: "left" },
            { name: "email", label: "Email", field: "email", sortable: true, align: "left" },
            { name: "senha", label: "Senha", field: "senha", sortable: true, align: "left" }

        ],
        aluno: [

            { name: "idAluno", label: "ID", field: "idAluno", sortable: true, align: "left", sortOrder: "ad" },
            { name: "nome", label: "Nome", field: "nome", sortable: true, align: "left" },
            { name: "matricula", label: "Matricula", field: "matricula", sortable: true, align: "left" },
            { name: "email", label: "Email", field: "email", sortable: true, align: "left" },
            { name: "senha", label: "Senha", field: "senha", sortable: true, align: "left" },
            { name: "turmas", label: "Turmas", field: "idTurmas", sortable: true, align: "left", format: (val: number[]) => this.$store.state.turmas.filter( (t: Turma) => val.includes(t.idTurma) ).map((t: Turma) => t.nome).join("\n"), style: "white-space: pre;" }

        ],
        professor: [

            { name: "idProfessor", label: "ID", field: "idProfessor", sortable: true, align: "left", sortOrder: "ad" },
            { name: "nome", label: "Nome", field: "nome", sortable: true, align: "left" },
            { name: "matricula", label: "Matricula", field: "matricula", sortable: true, align: "left" },
            { name: "email", label: "Email", field: "email", sortable: true, align: "left" },
            { name: "senha", label: "Senha", field: "senha", sortable: true, align: "left" },
            { name: "materia", label: "Materias", field: "materia", sortable: true, align: "left", format: (val: Materia[]) => val.map(v => v.nome).join("\n"), style: "white-space: pre;" },
            { name: "turmas", label: "Turmas", field: "idTurmas", sortable: true, align: "left", format: (val: number[]) => this.$store.state.turmas.filter( (t: Turma) => val.includes(t.idTurma) ).map((t: Turma) => t.nome).join("\n"), style: "white-space: pre;" }

        ],
        turmas: [ 
            
            { name: "idTurma", label: "ID", field: "idTurma", sortable: true, align: "left", sortOrder: "ad" },
            { name: "nome", label: "Nome", field: "nome", sortable: true, align: "left" },
            { name: "materia", label: "Materia", field: "materia", sortable: true, align: "left", format: (val: Materia) => val.nome },
            { name: "professor", label: "Professores", field: "professor", sortable: true, align: "left", format: (val: Professor[]) => val.map(p => p.nome).join("\n"), style: "white-space: pre;" },
            { name: "aluno", label: "Alunos", field: "aluno", sortable: true, align: "left", format: (val: Aluno[]) => val.map(p => p.nome).join("\n"), style: "white-space: pre;" },

        ]

    }

    administradorNull: Administrador = { idAdministrador: -1, nome: "", senha: "", email: "", login: "" }
    administrador = this.administradorNull

    professorNull: Professor = { idProfessor: -1, nome: "", senha: "", email: "", matricula: "", materia: [], idTurmas: [] }
    professor = this.professorNull

    alunoNull: Aluno = { idAluno: -1, nome: "", senha: "", email: "", matricula: "", idTurmas: [], notasTrabalhos: [], notasProvas: [] }
    aluno = this.alunoNull

    turmaNull: Turma = { idTurma: -1, nome: "", aula: [], professor: [], trabalho: [], atividade: [], prova: [], materia: { idMateria: -1, nome: "", idProfessor: [] }, discord: { idDiscord: -1, notificacoes: "", provasTrabalhos: "" }, aluno: [] }
    turma = this.turmaNull

    created() {

        this.db.administrador.get()

    }

    getADM() { this.db.administrador.get() }

    getProfessor() { 
        
        this.db.materia.get()
        this.db.turma.get()
        this.db.professor.get()
        
    }

    getAluno() { this.db.aluno.get() }

    getTurma() { 
    
        this.db.professor.get()
        this.db.aluno.get()
        this.db.materia.get()
        this.db.turma.get() 
        
    }

    buttonAdd() {

        this.dialog = true
        this.add = true

    }

    rowClick(evt, row: Administrador | Professor | Aluno | Turma, index: number) { 

        console.log("rowClick ->", row)

        if (this.tab == "administrador") { this.administrador = row as Administrador }

        if (this.tab == "professor") { this.professor = row as Professor }

        if (this.tab == "aluno") { this.aluno = row as Aluno }

        if (this.tab == "turma") { this.turma = row as Turma }

        this.dialog = true

    }

    dialogClose() {

        this.dialog = false
        this.add = false

        this.administrador = this.administradorNull
        this.professor = this.professorNull
        this.aluno = this.alunoNull
        this.turma = this.turmaNull

    }

}

</script>

<style lang="scss">

body {

    background-image: url("../assets/Fundo Home.png");
    background-size: cover;

}

.q-field__inner > .q-field__control > .q-field__control-container > .q-field__native > span {

    color: white;

}

.q-field__inner > .q-field__control > .q-field__control-container > .q-field__append > i {

    color: white !important;

}

</style>