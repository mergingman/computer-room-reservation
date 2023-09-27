<script setup lang="ts">
import NavBar from "@/components/NavBar.vue";
</script>

<template>
  <v-container fluid>
    <NavBar></NavBar>
    <v-container fluid class="fill-height" id="main">
      <RouterView></RouterView>
    </v-container>
  </v-container>
</template>

<style scoped>
</style>