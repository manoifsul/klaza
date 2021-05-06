<template>
  <q-page class="fit row wrap justify-center items-center content-center">
    
    <div class="q-pa-xl fit row wrap justify-between items-center content-center w-75 text-center gt-sm">

        <div class="q-pa-md box row wrap justify-center items-center content-center q-pa-xl col-7">

            <span class="text-bold text-h3 q-mb-xl">Login {{tipoLogin}}</span>

            <q-input filled outlined label="Matricula" placeholder="Digite a sua matricula" label-color="black" bg-color="white" class="br-10 w-80 q-mb-xl"/>

            <q-input filled outlined label="Senha" placeholder="Digite a sua senha" label-color="black" bg-color="white" class="br-10 w-80 q-mb-md"/>

            <div class="q-mx-auto column">

                <q-btn flat class="text-bold text-underline q-mx-auto q-mb-xl">Esqueci minha senha</q-btn>

                <q-btn color="white" text-color="black" class="br-10 w-60 q-mx-auto" dense>Entrar</q-btn>

            </div>

        </div>
        <div class="q-pa-md box column wrap justify-center items-center content-center self-center">

            <q-btn color="white" text-color="black" class="br-10 q-mb-xl q-mt-lg" icon="fas fa-user-alt" @click="tipoLogin = 'Aluno'">&nbsp;&nbsp;&nbsp;Aluno</q-btn>

            <q-btn color="white" text-color="black" class="br-10 q-mb-xl q-mx-xl" icon="fas fa-chalkboard-teacher" @click="tipoLogin = 'Professor'">&nbsp;&nbsp;&nbsp;Professor</q-btn>

            <q-btn color="white" text-color="black" class="br-10 q-mb-lg" icon="fas fa-user-shield" @click="tipoLogin = 'Administrator'">&nbsp;&nbsp;&nbsp;Administrator</q-btn>

        </div>

    </div>

    <div class="q-pa-xl fit row wrap justify-center items-center content-center w-100 text-center sm">

        <div class="q-pa-md box row wrap justify-center items-center content-center q-pa-xl col-7">

            <span class="text-bold text-h3 q-mb-xl text-center" v-show="tipoLogin == 'Aluno'">Login {{tipoLogin}}</span>
            <span class="text-bold text-h4 q-mb-xl text-center" v-show="tipoLogin == 'Professor'">Login {{tipoLogin}}</span>
            <span class="text-bold text-h5 q-mb-xl text-center" v-show="tipoLogin == 'Administrator'">Login {{tipoLogin}}</span>

            <q-input filled outlined label="Matricula" placeholder="Digite a sua matricula" label-color="black" bg-color="white" class="br-10 w-80 q-mb-xl"/>

            <q-input filled outlined label="Senha" placeholder="Digite a sua senha" label-color="black" bg-color="white" class="br-10 w-80 q-mb-md"/>

            <div class="q-mx-auto column">

                <q-btn flat class="text-bold text-underline q-mx-auto q-mb-xl">Esqueci minha senha</q-btn>

                <q-btn color="white" text-color="black" class="br-10 w-60 q-mx-auto" dense>Entrar</q-btn>

            </div>

        </div>
        <div class="q-pa-md box column wrap justify-center items-center content-center text-center self-center">

            <q-btn color="white" text-color="black" class="br-10 q-mb-xl q-mt-lg" icon="fas fa-user-alt" @click="tipoLogin = 'Aluno'">&nbsp;&nbsp;&nbsp;Aluno</q-btn>

            <q-btn color="white" text-color="black" class="br-10 q-mb-xl q-mx-xl" icon="fas fa-chalkboard-teacher" @click="tipoLogin = 'Professor'">&nbsp;&nbsp;&nbsp;Professor</q-btn>

            <q-btn color="white" text-color="black" class="br-10 q-mb-lg" icon="fas fa-user-shield" @click="tipoLogin = 'Administrator'">&nbsp;&nbsp;&nbsp;Administrator</q-btn>

        </div>

    </div>

    <div class="fit column wrap justify-between items-center content-center text-center w-100 xs">

        <div class="q-pa-md box row wrap justify-center items-center content-center q-pa-xl col-7">

            <div class="row wrap justify-center items-center content-center q-gutter-sm q-mb-xl">

                <q-btn color="white" text-color="black" class="br-10" icon="fas fa-user-alt" @click="tipoLogin = 'Aluno'">&nbsp;&nbsp;&nbsp;Aluno</q-btn>

                <q-btn color="white" text-color="black" class="br-10" icon="fas fa-chalkboard-teacher" @click="tipoLogin = 'Professor'">&nbsp;&nbsp;&nbsp;Professor</q-btn>

                <q-btn color="white" text-color="black" class="br-10" icon="fas fa-user-shield" @click="tipoLogin = 'Administrator'">&nbsp;&nbsp;&nbsp;Administrator</q-btn>

            </div>

            <span class="text-bold text-h4 q-mb-xl gt-xs">Login {{tipoLogin}}</span>
            <span class="text-bold text-h5 q-mb-xl lt-xs">Login {{tipoLogin}}</span>

            <q-input filled outlined label="Matricula" placeholder="Digite a sua matricula" label-color="black" bg-color="white" class="br-10 w-80 q-mb-xl"/>

            <q-input filled outlined label="Senha" placeholder="Digite a sua senha" label-color="black" bg-color="white" class="br-10 w-80 q-mb-md"/>

            <div class="q-mx-auto column">

                <q-btn flat class="text-bold text-underline q-mx-auto q-mb-xl">Esqueci minha senha</q-btn>

                <q-btn color="white" text-color="black" class="br-10 w-60 q-mx-auto" dense>Entrar</q-btn>

            </div>

        </div>

    </div>

  </q-page>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator';

@Component({

    data() {

        return {

            tipoLogin: 'Aluno'

        }

    }

})
export default class Login extends Vue {}

</script>

<style lang="scss">

body {

    background-image: url("../assets/Fundo Login.png");
    background-size: cover;

}

.box {

    background-color: rgba(255, 255, 255, 0.1);
    box-shadow: 0px 3px 5px #00000029;
    border: 1px solid #707070;
    border-radius: 20px;
    opacity: 1;
    backdrop-filter: blur(15px);
    -webkit-backdrop-filter: blur(15px);

}

</style>