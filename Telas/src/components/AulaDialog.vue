<template>

    <div class="box fit q-pa-md contentAula">

        <form @submit.prevent="save()">

            <div class="q-gutter-x-sm row wrap justify-end q-mb-md ">

                <q-btn v-if="$store.state.typeUser != 'aluno' && editProp != true" @click="edit()" class="br-10" padding="sm" color="primary" icon="fas fa-pen"/>
                <q-btn v-if="$store.state.typeUser != 'aluno' && editProp != true" @click="remove()" class="br-10" padding="sm" color="primary" icon="fas fa-trash"/>
                <q-btn class="br-10" padding="sm" color="primary" icon="fas fa-times" v-close-popup/>

            </div>

            <div v-if="!vEdit && !vAdd && vTurma" class="column text-bold text-h4 text-white q-gutter-y-md">

                <span>Aula: {{vNome}}</span>
                <span>Turma: {{vTurma.nome}} - {{vTurma.materia.nome}}</span>
                <span>Dia: {{vDia}}</span>
                <span>Horario: {{vHora}}</span>
                 <span>Anexos: <a v-for="f in vAula.arquivo" :key="uid()" :href="f.link">{{f.link}}</a> </span>
                <span>Descrição:</span>
                <div class="descricao br-20">{{vDescricao}}</div>        

            </div>

            <div v-else class="column text-bold text-h4 q-gutter-y-md">

                <q-input outlined v-model="modelNome" label-color="white" input-class="text-white" label="Aula" placeholder="Digite o nome da aula"/>
                <q-input outlined v-model="modelLink" label-color="white" input-class="text-white" label="Link" placeholder="Digite o link da aula"/>
                <q-select outlined input-class="text-white" label-color="white" v-model="modelTurma" placeholder="Digite a turma da aula" :options="optionsTurmas" label="Turma" use-chips use-input />
                <q-input v-model="date" label-color="white" input-class="text-white" label="Dia" placeholder="Digite o dia da aula" mask="##/##/####" fill-mask outlined>
                    <template v-slot:append>
                        <q-icon name="event" class="cursor-pointer">
                        <q-popup-proxy ref="qDateProxy" transition-show="scale" transition-hide="scale">
                            <q-date v-model="date" mask="DD/MM/YYYY">
                            <div class="row items-center justify-end">
                                <q-btn v-close-popup label="Fechar" color="primary" flat />
                            </div>
                            </q-date>
                        </q-popup-proxy>
                        </q-icon>
                    </template>
                </q-input>
                <q-input outlined input-class="text-white" label-color="white" placeholder="Digite o horario da aula" label="Horario" v-model="time" mask="##:##" fill-mask :rules="['time']">
                    <template v-slot:append>
                        <q-icon name="access_time" class="cursor-pointer">
                            <q-popup-proxy transition-show="scale" transition-hide="scale">
                            <q-time v-model="time">
                                <div class="row items-center justify-end">
                                <q-btn v-close-popup label="Fechar" color="primary" flat />
                                </div>
                            </q-time>
                            </q-popup-proxy>
                        </q-icon>
                    </template>
                </q-input>
                <q-file v-model="modelFiles" label-color="white" label="Adicione o(s) anexo(s)" outlined counter use-chips append multiple>

                    <template v-slot:prepend>
                        <q-icon color="white" name="fas fa-upload" />
                    </template>

                </q-file>
                <q-input outlined v-model="modelDescricao" label-color="white" input-class="text-white" label="Descrição" type="textarea" placeholder="Digite a descrição da aula"/>

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
import { Aula, Materia, Turma } from 'src/@types/DB';
import { DB } from 'src/middlewares/DBContector';
import { uid } from 'quasar';

@Component({

    components: { }

})
export default class AulaDialog extends Vue {

    @Prop() aula!: Aula
    @Prop() editProp!: boolean
    @Prop() add!: boolean

    uid = uid

    db = new DB(this.$axios, this.$store)

    vAula = this.aula

    vNome = this.aula.nome
    modelNome = this.vNome

    vDescricao = this.aula.descricao
    modelDescricao = this.vDescricao

    vDia = moment(this.aula.inicio).format("DD/MM/YYYY")
    date = this.vDia

    vHora = moment(this.aula.inicio).format("hh:mm A")
    time = this.vHora

    vTurma = (this.$store.state.turmas as Turma[]).find(t => t.idTurma == this.aula.idTurma)
    modelTurma: qSelectOptions | null = (this.vTurma != undefined) ? { label: `${this.vTurma?.nome} - ${this.vTurma?.materia.nome}`, value: this.vTurma as Turma} : null
    optionsTurmas: qSelectOptions[] = []

    vLink = this.aula.link
    modelLink = this.vLink

    vEdit = this.editProp
    vAdd = this.add

    modelFiles = this.aula.arquivo

    created() { 

        this.$store.state.turmas.forEach((t: Turma) => { this.optionsTurmas.push({ label: t.nome, value: t }) })

    }
 
    edit() {

        this.vEdit = !this.vEdit

    }

    save() {

        this.vDia = this.date
        this.vHora = moment().set({ hour: parseInt(this.time.split(":")[0]), minute: parseInt(this.time.split(":")[1]) }).format("LT")
        this.vNome = this.modelNome
        this.vDescricao = this.modelDescricao
        this.vTurma = this.modelTurma?.value as Turma
        this.vLink = this.modelLink

       if (this.add) {

            const aula: Aula = {

                idAula: -1,
                nome: this.vNome,
                descricao: this.vDescricao,
                inicio: moment(`${this.vDia} ${this.vHora}`, "DD-MM-YYYY HH:mm").toDate(),
                link: this.vLink,
                arquivo: [],
                materia: this.vTurma.materia,
                idTurma: this.vTurma.idTurma,

            }
            
            this.db.aula.create(aula)

        }
        else {

            const aula: Aula = {

                idAula: this.aula.idAula,
                nome: this.vNome,
                descricao: this.vDescricao,
                inicio: moment(`${this.vDia} ${this.vHora}`, "DD-MM-YYYY HH:mm").toDate(),
                link: this.vLink,
                arquivo: [],
                materia: this.vTurma.materia,
                idTurma: this.vTurma.idTurma,

            }
            
            this.aula.idAula = aula.idAula

            this.db.aula.update(aula)

        }

        this.vEdit = false
        this.vAdd = false

    }

    remove() {

        this.$swal.fire({

            title: "Excluir?",
            icon: "question",
            html: `tem certeza que deseja excluir a aula '${this.vNome}'?<br>(Essa ação não pode ser desfeita)`,
            target: '.contentAula',
            backdrop: false,
            showConfirmButton: true,
            showDenyButton: true,
            confirmButtonText: 'Sim',
            denyButtonText: 'Não',
            customClass: {

                popup: "bg-primary",
                content: "text-white",
                title: "text-white",
                confirmButton: "bg-white text-black"

            }

        })
        .then(r => {

            if (r.isConfirmed) {

                // EXCLUI NO DB

                console.log("Exclui Aula")

                this.$router.go(0)

            }
            else {

                console.log("Não Exclui Aula")

            }

        })

    }

}

</script>

<style lang="scss">

.q-field__native > span {

    color: white !important;

}

</style>