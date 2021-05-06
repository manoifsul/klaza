<template>
  <q-layout view="lHr lpR lFr">

    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="menu = !menu" />

        <q-toolbar-title>
          <q-avatar>
            
          </q-avatar>
          Klaza
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="menu" side="left" elevated>
      <!-- drawer content -->
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script lang="ts">

import { Vue, Component } from 'vue-property-decorator'

@Component({

    data() {

        return {

            menu: false

        }

    }


})
export default class App extends Vue {}

</script>