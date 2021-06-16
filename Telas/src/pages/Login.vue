<template>
  <q-page class="fit row wrap justify-center items-center content-center">

    <div class="q-pa-xl fit row wrap justify-between items-center content-center w-75 text-center gt-sm">

        <form class="q-pa-md box row wrap justify-center items-center content-center q-pa-xl col-7" @submit.prevent="Login()">

            <span class="text-bold text-h3 q-mb-xl">Login {{tipoLogin.substring(0, 1).toUpperCase() + tipoLogin.substring(1)}}</span>

            <q-input filled outlined label="Matricula" v-model="login" placeholder="Digite a sua matricula" label-color="black" bg-color="white" class="br-10 w-80 q-mb-xl" required/>

            <q-input filled outlined label="Senha" v-model="senha" placeholder="Digite a sua senha" label-color="black" bg-color="white" class="br-10 w-80 q-mb-md" required/>

            <div class="q-mx-auto column">

                <q-btn flat class="text-bold text-underline q-mx-auto q-mb-xl">Esqueci minha senha</q-btn>

                <q-btn color="white" text-color="black" class="br-10 w-60 q-mx-auto" type="submit" dense>Entrar</q-btn>

            </div>

        </form>
        <div class="q-pa-md box column wrap justify-center items-center content-center self-center">

            <q-btn color="white" text-color="black" class="br-10 q-mb-xl q-mt-lg" icon="fas fa-user-alt" @click="tipoLogin = 'aluno'">&nbsp;&nbsp;&nbsp;Aluno</q-btn>

            <q-btn color="white" text-color="black" class="br-10 q-mb-xl q-mx-xl" icon="fas fa-chalkboard-teacher" @click="tipoLogin = 'professor'">&nbsp;&nbsp;&nbsp;Professor</q-btn>

            <q-btn color="white" text-color="black" class="br-10 q-mb-lg" icon="fas fa-user-shield" @click="tipoLogin = 'administrator'">&nbsp;&nbsp;&nbsp;Administrador</q-btn>

        </div>

    </div>

    <div class="q-pa-xl fit row wrap justify-center items-center content-center w-100 text-center sm">

        <div class="q-pa-md box row wrap justify-center items-center content-center q-pa-xl col-7">

            <span class="text-bold text-h3 q-mb-xl text-center" v-show="tipoLogin == 'aluno'">Login {{tipoLogin}}</span>
            <span class="text-bold text-h4 q-mb-xl text-center" v-show="tipoLogin == 'professor'">Login {{tipoLogin}}</span>
            <span class="text-bold text-h5 q-mb-xl text-center" v-show="tipoLogin == 'administrador'">Login {{tipoLogin}}</span>

            <q-input filled outlined label="Matricula" v-model="login" placeholder="Digite a sua matricula" label-color="black" bg-color="white" class="br-10 w-80 q-mb-xl"/>

            <q-input filled outlined label="Senha" v-model="senha" placeholder="Digite a sua senha" label-color="black" bg-color="white" class="br-10 w-80 q-mb-md"/>

            <div class="q-mx-auto column">

                <q-btn flat class="text-bold text-underline q-mx-auto q-mb-xl">Esqueci minha senha</q-btn>

                <q-btn color="white" text-color="black" class="br-10 w-60 q-mx-auto" dense @click="Login()">Entrar</q-btn>

            </div>

        </div>
        <div class="q-pa-md box column wrap justify-center items-center content-center text-center self-center">

            <q-btn color="white" text-color="black" class="br-10 q-mb-xl q-mt-lg" icon="fas fa-user-alt" @click="tipoLogin = 'aluno'">&nbsp;&nbsp;&nbsp;Aluno</q-btn>

            <q-btn color="white" text-color="black" class="br-10 q-mb-xl q-mx-xl" icon="fas fa-chalkboard-teacher" @click="tipoLogin = 'professor'">&nbsp;&nbsp;&nbsp;Professor</q-btn>

            <q-btn color="white" text-color="black" class="br-10 q-mb-lg" icon="fas fa-user-shield" @click="tipoLogin = 'administrador'">&nbsp;&nbsp;&nbsp;Administrator</q-btn>

        </div>

    </div>

    <div class="fit column wrap justify-between items-center content-center w-100 text-center xs">

        <div class="q-pa-md box row wrap justify-center items-center content-center q-pa-xl col-7">

            <div class="row wrap justify-center items-center content-center q-gutter-sm q-mb-xl">

                <q-btn color="white" text-color="black" class="br-10" icon="fas fa-user-alt" @click="tipoLogin = 'Aluno'">&nbsp;&nbsp;&nbsp;Aluno</q-btn>

                <q-btn color="white" text-color="black" class="br-10" icon="fas fa-chalkboard-teacher" @click="tipoLogin = 'Professor'">&nbsp;&nbsp;&nbsp;Professor</q-btn>

                <q-btn color="white" text-color="black" class="br-10" icon="fas fa-user-shield" @click="tipoLogin = 'Administrator'">&nbsp;&nbsp;&nbsp;Administrator</q-btn>

            </div>

            <span class="text-bold text-h4 q-mb-xl gt-xs">Login {{tipoLogin}}</span>
            <span class="text-bold text-h5 q-mb-xl lt-xs">Login {{tipoLogin}}</span>

            <q-input filled outlined label="Matricula" v-model="login" placeholder="Digite a sua matricula" label-color="black" bg-color="white" class="br-10 w-80 q-mb-xl"/>

            <q-input filled outlined label="Senha" v-model="senha" placeholder="Digite a sua senha" label-color="black" bg-color="white" class="br-10 w-80 q-mb-md"/>

            <div class="q-mx-auto column">

                <q-btn flat class="text-bold text-underline q-mx-auto q-mb-xl">Esqueci minha senha</q-btn>

                <q-btn color="white" text-color="black" class="br-10 w-60 q-mx-auto" dense @click="Login()">Entrar</q-btn>

            </div>

        </div>

    </div>

  </q-page>
</template>

<script lang="ts">
import { DB } from 'src/middlewares/DBContector';
import { Vue, Component } from 'vue-property-decorator';

@Component
export default class Login extends Vue {

    tipoLogin: "aluno" | "professor" | "administrador" = 'aluno'
    login = ''
    senha = ''

    db = new DB(this.$axios, this.$store)

    async Login() { 
    
        const login = { login: this.$data.login, senha: this.$data.senha }

        console.log(login, this.tipoLogin)

        const ok = await this.db.login(this.tipoLogin, login.login, login.senha)

        // this.$store.state.typeUser = "aluno"

        if (ok) { return this.$router.push('home') }
        else {

            this.$swal.fire({

                title: "Login ou senha incorreto",
                text: "Seu login e/ou sua senha não existe ou esta incorreto",
                customClass: {

                    popup: "bg-primary",
                    content: "text-white",
                    title: "text-white",
                    confirmButton: "bg-white text-black"

                }

            })

        }

        // return this.$router.push('home')
        
    }


}

</script>

<style lang="scss">

body {

    background-image: url("../assets/Fundo Login.png");
    background-size: cover;

}

</style>