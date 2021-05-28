<template>
    <q-page>

        <q-page class="lt-sm">

            <div class="q-my-lg">

                <span class="text-bold text-h5 text-white lt-sm">Proximos Dias</span>
                <span class="text-bold text-h4 text-white sm">Proximos Dias</span>

            </div>

            <div v-if="(typeAccont == 'Professor') || (typeAccont == 'Administrador')" class="row wrap justify-around q-ma-md q-gutter-sm">

                <q-btn color="primary" label="Nova Aula" class="br-10"/>
                <q-btn color="primary" label="Nova Atividade" class="br-10"/>
                <q-btn color="primary" label="Novo Trabalho" class="br-10"/>
                <q-btn color="primary" label="Nova Prova" class="br-10"/>

            </div>

            <div class="column wrap justify-between lt-sm items-center">

                <div class="q-mb-md">
                    
                    <q-date v-model="date" today-btn minimal/>
                    
                </div>

                <div class="w-100">

                    <DayCard v-for="d in days" :key="d.id" :day="d.day" :atividades="d.atividades" :aulas="d.aulas" :provas="d.provas" :trabalhos="d.trabalhos" />

                </div>

            </div>

        </q-page>

        <q-page class="q-pa-md sm">

            <div class="q-my-lg">

                <span class="text-bold text-h4 text-white">Proximos Dias</span>

            </div>

            <div v-if="(typeAccont == 'Professor') || (typeAccont == 'Administrador')" class="row wrap justify-around q-ma-md q-gutter-sm">

                <q-btn color="primary" label="Nova Aula" class="br-10"/>
                <q-btn color="primary" label="Nova Atividade" class="br-10"/>
                <q-btn color="primary" label="Novo Trabalho" class="br-10"/>
                <q-btn color="primary" label="Nova Prova" class="br-10"/>

            </div>

            <div class="column wrap justify-between items-center">

                <div class="q-mb-md">
                    
                    <q-date v-model="date" today-btn />
                    
                </div>

                <div class="w-100">

                    <DayCard v-for="d in days" :key="d.id" :day="d.day" :atividades="d.atividades" :aulas="d.aulas" :provas="d.provas" :trabalhos="d.trabalhos" />

                </div>

            </div>

        </q-page>

        <q-page class="q-pa-md gt-sm">

            <div class="q-my-lg">

                <span class="text-bold text-h4 text-white">Proximos Dias</span>

            </div>

            <div v-if="(typeAccont == 'Professor') || (typeAccont == 'Administrador')" class="row wrap justify-around q-ma-md q-gutter-sm">

                <q-btn color="primary" label="Nova Aula" class="br-10"/>
                <q-btn color="primary" label="Nova Atividade" class="br-10"/>
                <q-btn color="primary" label="Novo Trabalho" class="br-10"/>
                <q-btn color="primary" label="Nova Prova" class="br-10"/>

            </div>

            <div class="row wrap justify-between">

                <div class="w-75">

                    <DayCard v-for="d in days" :key="d.id" :day="d.day" :atividades="d.atividades" :aulas="d.aulas" :provas="d.provas" :trabalhos="d.trabalhos"/>

                </div>

                <div>
                    
                    <q-date v-model="date" today-btn />
                    
                </div>

            </div>

        </q-page>

    </q-page>
</template>

<script lang="ts">

import { DayCardType } from 'src/components/models';
import { Vue, Component } from 'vue-property-decorator';

import DayCard from '../components/DayCard.vue'

import moment from 'moment'

@Component({

    components: { DayCard }

})
export default class Home extends Vue {

    days: DayCardType[] = [
        
        {

            day: "06/05",
            aulas: [

                {

                    dia: new Date,
                    name: "Opa",
                    materia: {id: 132, name: "aaa"},
                    id: 123,
                    descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",

                }

            ],
            trabalhos: [

                {

                    name: "Opa",
                    materia: {id: 132, name: "aaa"},
                    id: 123,
                    descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                    start: new Date,
                    finish: new Date,
                    maxTentativas: 95,
                    maxTempo: 0,
                    questoes: [{id: 1298, question: "Quanto é 1+1?", alternativas: [{id: 0, resposta: "95"}, {id: 12, resposta: "1"}, {id: 5, resposta: "1+1"}], correta: [5], type: 0}],
                    respostas: [

                        { id: 0, aluno: { id: 1, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
                        { id: 1, aluno: { id: 2, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 1, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
                        { id: 2, aluno: { id: 3, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 2, resposta: "e" }, resposta: "opa", num_tentativa: 2 },
                        { id: 3, aluno: { id: 4, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 3, resposta: "e" }, resposta: "opa", num_tentativa: 2 },
                        { id: 4, aluno: { id: 5, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 4, resposta: "e" }, resposta: "opa", num_tentativa: 2 },
                        { id: 5, aluno: { id: 6, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 5, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
                        { id: 6, aluno: { id: 7, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 6, resposta: "e" }, resposta: "opa", num_tentativa: 3 },
                        { id: 7, aluno: { id: 98, nome: "3", matricula: "aasdasd22", senha: 'oaisjdasoidj', email: "a89sd498", notasTrabalhos: [], notasProvas: [] }, questao: { id: 1, resposta: "e" }, resposta: "opa", num_tentativa: 1 },

                    ],

                }

            ],
            atividades: [

                {
                
                    dia: new Date,
                    name: "Opa",
                    materia: {id: 132, name: "aaa"},
                    id: 123,
                    descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"

                }

            ],
            provas: [

                {

                    name: "Opa",
                    materia: {id: 132, name: "aaa"},
                    id: 123,
                    descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                    start: new Date,
                    finish: new Date,
                    maxTentativas: 95,
                    maxTempo: 9223372036854775807,
                    questoes: [{id: 1298, question: "Quanto é 1+1?", alternativas: [{id: 0, resposta: "95"}, {id: 12, resposta: "1"}, {id: 5, resposta: "1+1"}], correta: [5], type: 0}],
                    tentativas: [ {id: 0, aluno: { id: 0, nome: "opa", matricula: "aa", senha: "aosiduaosd", email: "awseaee", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "opa" }, resposta: "opa", num_tentativa: 1} ],

                }

            ],
            id: 1

        },
        {

            day: "06/05",
            aulas: [

                {

                    dia: new Date,
                    name: "Opa",
                    materia: {id: 132, name: "aaa"},
                    id: 123,
                    descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",

                }

            ],
            trabalhos: [

                {

                    name: "Opa",
                    materia: {id: 132, name: "aaa"},
                    id: 123,
                    descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                    start: new Date,
                    finish: new Date,
                    maxTentativas: 95,
                    maxTempo: 1000000000000,
                    questoes: [{id: 0, question: "Quanto é 1+1?", alternativas: [{id: 0, resposta: "95"}, {id: 12, resposta: "1"}, {id: 5, resposta: "1+1"}], correta: [5], type: 1}],
                    respostas: [

                        { id: 0, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 0 },
                        { id: 1, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 0 },
                        { id: 2, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
                        { id: 3, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 1 },
                        { id: 4, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 0 },
                        { id: 5, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 3 },
                        { id: 6, aluno: { id: 98, nome: "3", matricula: "aasdasd22", senha: 'oaisjdasoidj', email: "a89sd498", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 1 },

                    ],

                }

            ],
            atividades: [

                {
                
                    dia: new Date,
                    name: "Opa",
                    materia: {id: 132, name: "aaa"},
                    id: 123,
                    descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"

                }

            ],
            provas: [

                {

                    name: "Opa",
                    materia: {id: 132, name: "aaa"},
                    id: 123,
                    descricao: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                    start: new Date,
                    finish: new Date,
                    maxTentativas: 95,
                    maxTempo: 1000000000000,
                    questoes: [{id: 0, question: "Quanto é 1+1?", alternativas: [{id: 0, resposta: "95"}, {id: 12, resposta: "1"}, {id: 5, resposta: "1+1"}], correta: [5], type: 1}],
                    tentativas: [{ id: 0, aluno: { id: 0, nome: "asoidjha", matricula: "asdasd", senha: 'oaisjdasoidj', email: "aoisjhdioajsd", notasTrabalhos: [], notasProvas: [] }, questao: { id: 0, resposta: "e" }, resposta: "opa", num_tentativa: 0 }],

                }

            ],
            id: 99

        },
        {

            day: "11/05",
            provas: [],
            aulas: [],
            trabalhos: [],
            atividades: [],
            id: 956146879879

        },

    ]
   
    date = moment().format("YYYY/MM/DD")

    typeAccont: "Aluno" | "Professor" | "Administrador" = "Aluno"

}

</script>

<style lang="scss">

body {

    background-image: url("../assets/Fundo Home.png");
    background-size: cover;

}

</style>