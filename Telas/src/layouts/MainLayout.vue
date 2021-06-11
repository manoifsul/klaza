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

            <q-list padding class="text-white">

                <q-item to="/home"  active-class="bg-white text-primary text-bold" clickable>

                    <q-item-section avatar>
                        <q-icon name="fas fa-home"/>
                    </q-item-section>

                    <q-item-section>Home</q-item-section>

                </q-item>

                <q-item v-if="$store.state.typeUser != 'aluno'" to="/admin" active-class="bg-white text-primary text-bold" clickable>

                    <q-item-section avatar>
                        <q-icon name="fas fa-user-shield"/>
                    </q-item-section>

                    <q-item-section>Administração</q-item-section>

                </q-item>

                <q-item v-if="$store.state.typeUser == 'aluno'" to="/notas" active-class="bg-white text-primary text-bold" clickable>

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