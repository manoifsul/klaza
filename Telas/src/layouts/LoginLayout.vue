<template>
  <q-layout view="lHr lpR lFr">

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script lang="ts">

import { Vue, Component } from 'vue-property-decorator'

@Component({})
export default class App extends Vue {}

</script>