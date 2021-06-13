<template>

    <div class="box fit q-pa-md contentAula">

        <form @submit.prevent="save()">

            <div class="q-gutter-x-sm row wrap justify-end q-mb-md ">

                <q-btn v-if="$store.state.typeUser != 'aluno' && editProp != true" @click="edit()" class="br-10" padding="sm" color="primary" icon="fas fa-pen"/>
                <q-btn v-if="$store.state.typeUser != 'aluno' && editProp != true" @click="remove()" class="br-10" padding="sm" color="primary" icon="fas fa-trash"/>
                <q-btn class="br-10" padding="sm" color="primary" icon="fas fa-times" v-close-popup/>

            </div>

            <div v-if="!vEdit && !vAdd && vTurma" class="column text-bold text-h4 text-white q-gutter-y-md">

                <span>Atividade: {{vNome}}</span>
                <span>Turma: {{vTurma.nome}} - {{vTurma.materia.nome}}</span>
                <span>Dia: {{vDia}}</span>
                <span>Horario: {{vHora}}</span>
                <span>Anexos: <a v-for="f in vAtividade.arquivo" :key="uid()" :href="f.link">{{f.link}}</a> </span>
                <span>Descrição:</span>
                <div class="descricao br-20">{{vDescricao}}</div>        

            </div>
            <div v-else class="column text-bold text-h4 q-gutter-y-md">

                <q-input outlined v-model="modelNome" label-color="white" input-class="text-white" label="Atividade" placeholder="Digite o nome da atividade" required/>
                <q-select outlined input-class="text-white" label-color="white" v-model="modelProfessores" placeholder="Digite o(s) nome(s) dos professor(es)" :options="optionsProfessores" label="Professores" use-chips use-input multiple />
                <q-select outlined input-class="text-white" label-color="white" v-model="modelTurma" placeholder="Digite a turma da atividade" :options="optionsTurmas" label="Turma" use-chips use-input />
                <q-input v-model="date" label-color="white" input-class="text-white" label="Dia" placeholder="Digite o dia da atividade" mask="##/##/####" fill-mask outlined required>
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
                <q-input outlined input-class="text-white" label-color="white" placeholder="Digite o horario da atividade" label="Horario" v-model="time" mask="##:##" fill-mask :rules="['time']" required>
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
                <q-input outlined v-model="modelDescricao" label-color="white" input-class="text-white" label="Descrição" type="textarea" placeholder="Digite a descrição da atividade" required/>

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
import { Atividade, Materia, Professor, Turma } from 'src/@types/DB';
import { DB } from 'src/middlewares/DBContector';
import { uid } from 'quasar';
import { Discord } from 'src/middlewares/DiscordConector';

@Component({

    components: { }

})
export default class AtividadeDialog extends Vue {

    @Prop() atividade!: Atividade
    @Prop() editProp!: boolean
    @Prop() add!: boolean

    uid = uid

    db = new DB(this.$axios, this.$store)

    vAtividade = this.atividade

    vNome = this.atividade.nome
    modelNome = this.vNome

    vDescricao = this.atividade.descricao
    modelDescricao = this.vDescricao

    vDia = moment(this.atividade.inicio).format("DD/MM/YYYY")
    date = this.vDia

    vHora = moment(this.atividade.inicio).format("hh:mm A")
    time = this.vHora

    vProfessores = this.atividade.professor
    modelProfessores: qSelectOptions[] = []
    optionsProfessores: qSelectOptions[] = []

    vTurma = (this.$store.state.turmas as Turma[]).find(t => t.idTurma == this.atividade.idTurma)
    modelTurma: qSelectOptions | null = (this.vTurma != undefined) ? { label: `${this.vTurma?.nome} - ${this.vTurma?.materia.nome}`, value: this.vTurma as Turma} : null
    optionsTurmas: qSelectOptions[] = []

    modelFiles = this.atividade.arquivo 

    vEdit = this.editProp
    vAdd = this.add

    async created() { 

        await this.db.professor.get()

        this.vProfessores.forEach(p => { this.modelProfessores.push({ label: p.nome, value: p }) })
        this.$store.state.professores.forEach((p: Professor) => { this.optionsProfessores.push({ label: p.nome, value: p }) })

        this.$store.state.turmas.forEach((t: Turma) => { this.optionsTurmas.push({ label: t.nome, value: t }) })

    }
 
    edit() { this.vEdit = !this.vEdit }

    save() {

        this.vDia = this.date
        this.vHora = moment().set({ hour: parseInt(this.time.split(":")[0]), minute: parseInt(this.time.split(":")[1]) }).format("LT")
        this.vNome = this.modelNome
        this.vDescricao = this.modelDescricao
        this.vProfessores = this.modelProfessores.map(p => p.value as Professor)
        this.vTurma = this.modelTurma?.value as Turma

        if (this.add) {

            const atividade: Atividade = {

                idAtividade: -1,
                nome: this.vNome,
                descricao: this.vDescricao,
                inicio: moment(`${this.vDia} ${this.vHora}`, "DD-MM-YYYY HH:mm").toDate(),
                arquivo: [],
                administrador: [],
                professor: this.vProfessores,
                materia: this.vTurma.materia,
                idTurma: this.vTurma.idTurma,

            }

            this.db.atividade.create(atividade)

            new Discord((this.modelTurma?.value as Turma).discord.provasTrabalhos, this.$store).atividade.add(atividade)

        }
        else {

            const atividade: Atividade = {

                idAtividade: this.atividade.idAtividade,
                nome: this.vNome,
                descricao: this.vDescricao,
                inicio: new Date,
                arquivo: [],
                administrador: [],
                professor: this.vProfessores,
                materia: this.vTurma.materia,
                idTurma: this.vTurma.idTurma,

            }
            
            this.atividade.idAtividade = atividade.idAtividade

            this.db.atividade.update(atividade)

            new Discord((this.modelTurma?.value as Turma).discord.provasTrabalhos, this.$store).atividade.update(atividade)

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

                this.db.atividade.delete(this.atividade.idAtividade)

                new Discord((this.modelTurma?.value as Turma).discord.provasTrabalhos, this.$store).atividade.delete(this.vAtividade)

                this.$router.go(0)

            }
            else {

                console.log("Não Exclui Atividade")

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