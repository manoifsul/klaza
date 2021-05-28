<template>

    <div class="box fit q-pa-md contentAula">

        <form @submit.prevent="save()">

            <div class="q-gutter-x-sm row wrap justify-end q-mb-md ">

                <q-btn v-if="$store.state.typeUser != 'Aluno'" @click="edit()" class="br-10" padding="sm" color="primary" icon="fas fa-pen"/>
                <q-btn v-if="$store.state.typeUser != 'Aluno'" @click="remove()" class="br-10" padding="sm" color="primary" icon="fas fa-trash"/>
                <q-btn class="br-10" padding="sm" color="primary" icon="fas fa-times" v-close-popup/>

            </div>

            <div v-if="!vEdit" class="column text-bold text-h4 text-white q-gutter-y-md">

                <span>Aula: {{vNome}}</span>
                <span>Materia: {{vMateria.name}}</span>
                <span>Dia: {{vDia}}</span>
                <span>Horario: {{vHora}}</span>
                <span>Descrição:</span>
                <div class="descricao br-20">{{vDescricao}}</div>        

            </div>

            <div v-else class="column text-bold text-h4 q-gutter-y-md">

                <q-input outlined v-model="modelNome" label-color="white" input-class="text-white" label="Aula" placeholder="Digite o nome da aula"/>
                <q-select outlined input-class="text-white" label-color="white" color="white" v-model="modelMateria"  placeholder="Digite o nome da materia" :options="optionsMateria" label="Materia" use-input/>
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
import { Materia } from 'src/@types/DB';

@Component({

    components: { }

})
export default class Aula extends Vue {

    @Prop() nome!: string
    @Prop() materia!: Materia
    @Prop() dia!: Date
    @Prop() descricao!: string

    vNome = this.nome
    modelNome = this.vNome

    vDescricao = this.descricao
    modelDescricao = this.vDescricao

    vDia = moment(this.dia).format("DD/MM/YYYY")
    date = this.vDia


    vHora = moment(this.dia).format("hh:mm A")
    time = this.vHora

    
    vMateria = this.materia
    modelMateria: qSelectOptions = { label: this.vMateria.name, value: this.vMateria.id }
    optionsMateria: qSelectOptions[] = []

    vEdit = false

    created() { 

        for (let mat in this.$store.state.materiasProfessor) {

            const materia: Materia = this.$store.state.materiasProfessor[mat]

            this.optionsMateria.push({label: materia.name, value: materia.id})

        }

    }
 
    edit() {

        this.vEdit = !this.vEdit

    }

    save() {

        // SALVA NO BD

        this.vDia = this.date
        this.vHora = moment().set({ hour: parseInt(this.time.split(":")[0]), minute: parseInt(this.time.split(":")[1]) }).format("LT")
        this.vMateria = { id: this.modelMateria.value as number, name: this.modelMateria.label as string }
        this.vNome = this.modelNome
        this.vDescricao = this.modelDescricao

        this.vEdit = false

    }

    remove() {

        this.$swal.fire({

            title: "Excluir?",
            icon: "question",
            html: `tem certeza que deseja excluir a aula '${this.nome}'?<br>(Essa ação não pode ser desfeita)`,
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