<template>
  <q-layout view="lHr lpR lFr">

    <q-header elevated class="bg-primary text-white">
        <q-toolbar>
            <q-btn dense flat round icon="menu" @click="menu = !menu" />

            <q-toolbar-title>
                <q-avatar square>
                    <q-icon name="fas fa-book-reader" size="md"/>
                </q-avatar>
                <span class="text-bold">Klaza</span>
            </q-toolbar-title>
        </q-toolbar>
    </q-header>

    <q-drawer v-model="menu" side="left" content-class="bg-primary" bordered elevated>
      
        <q-scroll-area class="fit">

            <q-list padding>

                <q-item to="/home" active-class="bg-white text-primary text-bold" clickable>

                    <q-item-section avatar>
                        <q-icon name="fas fa-home"/>
                    </q-item-section>

                    <q-item-section>Home</q-item-section>

                </q-item>

                <q-item to="/atividades" class="text-black" active-class="bg-white text-primary text-bold" clickable>

                    <q-item-section avatar>
                        <q-icon name="fas fa-file-alt"/>
                    </q-item-section>

                    <q-item-section>Atividades</q-item-section>

                </q-item>

                <q-item to="/trabalhos" class="text-black" active-class="bg-white text-primary text-bold" clickable>

                    <q-item-section avatar>
                        <q-icon name="fas fa-newspaper"/>
                    </q-item-section>

                    <q-item-section>Trabalhos</q-item-section>

                </q-item>

                <q-item to="/provas" class="text-black" active-class="bg-white text-primary text-bold" clickable>

                    <q-item-section avatar>
                        <q-icon name="fas fa-scroll"/>
                    </q-item-section>

                    <q-item-section>Provas</q-item-section>

                </q-item>

                <q-item to="/aulas" class="text-black" active-class="bg-white text-primary text-bold" clickable>

                    <q-item-section avatar>
                        <q-icon name="fas fa-book-open"/>
                    </q-item-section>

                    <q-item-section>Aulas</q-item-section>

                </q-item>

                <q-item to="/boletim" class="text-black" active-class="bg-white text-primary text-bold" clickable>

                    <q-item-section avatar>
                        <q-icon name="fas fa-clipboard-list"/>
                    </q-item-section>

                    <q-item-section>Boletim</q-item-section>

                </q-item>

            </q-list>

        </q-scroll-area>

    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script lang="ts">

import { Vue, Component } from 'vue-property-decorator'

@Component
export default class App extends Vue {

    menu = false

}

</script>