<template>

    <div class="box fit q-pa-md contentAdmDialog">

        <form @submit.prevent="save()">

            <div class="q-gutter-x-sm row wrap justify-end q-mb-md ">

                <q-btn v-if="!vAdd" @click="vEdit = !vEdit" class="br-10" padding="sm" color="primary" icon="fas fa-pen"/>
                <q-btn v-if="!vAdd" @click="remove()" class="br-10" padding="sm" color="primary" icon="fas fa-trash"/>
                <q-btn class="br-10" padding="sm" color="primary" icon="fas fa-times" v-close-popup/>

            </div>

            <div v-if="!vEdit" class="column text-bold text-h4 text-white q-gutter-y-md">

                <span>Nome: {{vAdministrador.nome}}</span>
                <span>Login: {{vAdministrador.login}}</span>
                <span>Email: {{vAdministrador.email}}</span>
                <span>Senha: {{vAdministrador.senha}}</span>     

            </div>
            <div v-else class="column text-bold text-h4 q-gutter-y-md">

                <q-input outlined v-model="modelNome" label-color="white" input-class="text-white" label="Nome" placeholder="Digite o nome do administrador" required />
                <q-input outlined v-model="modelLogin" label-color="white" input-class="text-white" label="Login" placeholder="Digite o login do administrador" required />
                <q-input outlined v-model="modelEmail" type="email" label-color="white" input-class="text-white" label="Email" placeholder="Digite o email do administrador" required />
                <q-input outlined v-model="modelSenha" label-color="white" input-class="text-white" label="Senha" placeholder="Digite a senha do administrador" required />
               
            </div>
            <div class="row justify-center q-mt-md">

                <q-btn v-if="vEdit" type="submit" label="Salvar" color="primary"/>

            </div>

        </form>

    </div>
</template>

<script lang="ts">

import { Vue, Component, Prop } from 'vue-property-decorator';

import { Administrador } from 'src/@types/DB';
import { DB } from 'src/middlewares/DBContector';

@Component({

    components: { }

})
export default class AdmDialog extends Vue {

    @Prop() add!: boolean
    @Prop() administrador!: Administrador

    vAdd = this.add
    vEdit = this.add ? true : false

    vAdministrador: Administrador = this.administrador

    modelNome = this.vAdministrador.nome
    modelLogin = this.vAdministrador.login
    modelSenha = this.vAdministrador.senha
    modelEmail = this.vAdministrador.email

    db = new DB(this.$axios, this.$store)

    async save() {

        if (this.vAdd) {

            this.vAdministrador = await this.db.administrador.create({

                idAdministrador: -1,
                nome: this.modelNome,
                senha: this.modelSenha,
                email: this.modelEmail,
                login: this.modelLogin

            })

        }
        else {

            this.vAdministrador = {

                idAdministrador: this.vAdministrador.idAdministrador,
                nome: this.modelNome,
                senha: this.modelSenha,
                email: this.modelEmail,
                login: this.modelLogin

            }

            await this.db.administrador.update(this.vAdministrador)

        }

        this.vAdd = false
        this.vEdit = false

    }

    remove() {

        this.$swal.fire({

            title: "Excluir?",
            text: `Tem certaza que deseja excluir o administrador '${this.administrador.nome}'?`,
            showConfirmButton: true,
            showDenyButton: true,
            confirmButtonText: "Sim",
            denyButtonText: "Não",
            target: ".contentAdmDialog",
            customClass: {

                popup: "bg-primary",
                content: "text-white",
                title: "text-white",
                confirmButton: "bg-white text-black"

            }

        }).then(swal => {

            if (swal.isConfirmed) { this.db.administrador.delete(this.administrador.idAdministrador); this.hide() }

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