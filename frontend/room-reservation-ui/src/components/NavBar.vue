<script setup lang="ts">
const props = defineProps<{
  title?: string
  likes?: number
}>()
</script>

<template>

    <v-container fluid>
      <v-app-bar id="app-bar" color="primary">
        <v-app-bar-title href="#">Realschule Northeim</v-app-bar-title>
      </v-app-bar>
    </v-container>

</template>

<style scoped>
</style>