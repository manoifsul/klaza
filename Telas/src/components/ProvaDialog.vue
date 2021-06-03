<template>

    <div class="box fit q-pa-md contentProva">

        <form @submit.prevent="save()">

            <div class="q-gutter-x-sm row wrap justify-end q-mb-md ">

                <q-btn v-if="$store.state.typeUser != 'aluno' && editProp != true" @click="edit()" class="br-10" padding="sm" color="primary" icon="fas fa-pen"/>
                <q-btn v-if="$store.state.typeUser != 'aluno' && editProp != true" @click="remove()" class="br-10" padding="sm" color="primary" icon="fas fa-trash"/>
                <q-btn class="br-10" padding="sm" color="primary" icon="fas fa-times" v-close-popup/>

            </div>

            <div v-if="!vEdit" class="column text-bold text-h4 text-white q-gutter-y-md">

                <span>Prova: {{vNome}}</span>
                <span>Materia: {{vMateria.nome}}</span>
                <span>Dia: {{vDia}}</span>
                <span>Horario: {{vHora}}</span>
                <span>Descrição:</span>
                <div class="descricao br-20">{{vDescricao}}</div>        

            </div>

            <div v-else class="column text-bold text-h4 q-gutter-y-md">

                <q-input outlined v-model="modelNome" label-color="white" input-class="text-white" label="Prova" placeholder="Digite o nome da prova"/>
                <q-select outlined input-class="text-white" label-color="white" color="white" v-model="modelMateria"  placeholder="Digite a materia da prova" :options="optionsMateria" label="Materia" use-input/>
                <q-input v-model="date" label-color="white" input-class="text-white" label="Dia" placeholder="Digite o dia da prova" mask="##/##/####" fill-mask outlined>
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
                <q-input outlined input-class="text-white" label-color="white" placeholder="Digite o horario da prova" label="Horario" v-model="time" mask="##:##" fill-mask :rules="['time']">
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
                <q-input outlined v-model="modelDescricao" label-color="white" input-class="text-white" label="Descrição" type="textarea" placeholder="Digite a descrição da prova"/>

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
import { Materia } from 'src/@types/DB';

@Component({

    components: { }

})
export default class ProvaDialog extends Vue {

    @Prop() nome!: string
    @Prop() materia!: Materia
    @Prop() dia!: Date
    @Prop() descricao!: string
    @Prop() editProp!: boolean

    vNome = this.nome
    modelNome = this.vNome

    vDescricao = this.descricao
    modelDescricao = this.vDescricao

    vDia = moment(this.dia).format("DD/MM/YYYY")
    date = this.vDia


    vHora = moment(this.dia).format("hh:mm A")
    time = this.vHora

    
    vMateria = this.materia
    modelMateria: qSelectOptions = { label: this.vMateria.nome, value: this.vMateria.idMateria }
    optionsMateria: qSelectOptions[] = []

    vEdit = (this.editProp != true) ? false : true

    created() { 

        for (let mat in this.$store.state.materiasProfessor) {

            const materia: Materia = this.$store.state.materiasProfessor[mat]

            this.optionsMateria.push({label: materia.nome, value: materia.idMateria})

        }

    }
 
    edit() {

        this.vEdit = !this.vEdit

    }

    save() {

        // SALVA NO BD

        this.vDia = this.date
        this.vHora = moment().set({ hour: parseInt(this.time.split(":")[0]), minute: parseInt(this.time.split(":")[1]) }).format("LT")
        this.vMateria = { idMateria: this.modelMateria.value as number, nome: this.modelMateria.label as string, idProfessor: this.$store.state.materias[this.modelMateria.value].idProfessor }
        this.vNome = this.modelNome
        this.vDescricao = this.modelDescricao

        this.vEdit = false

    }

    remove() {

        this.$swal.fire({

            title: "Excluir?",
            icon: "question",
            html: `tem certeza que deseja excluir a prova '${this.nome}'?<br>(Essa ação não pode ser desfeita)`,
            target: '.contentProva',
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