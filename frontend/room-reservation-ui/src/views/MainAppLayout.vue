<script setup lang="ts">
import NavBar from "@/components/NavBar.vue";
</script>

<template>
  <v-container fluid>
    <v-theme-provider theme="light">
    <NavBar></NavBar>
    <v-container fluid class="fill-height" id="main">
      <RouterView></RouterView>
    </v-container>
    </v-theme-provider>
  </v-container>
</template>

<style scoped>
</style>