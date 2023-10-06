<script setup lang="ts">
import {useFetchStore} from "@/stores/fetch";
import {storeToRefs} from "pinia";

const fetchStore = useFetchStore();
const {fetchFromApi} = fetchStore
const {loading} = storeToRefs(fetchStore)
</script>

<template>
  <v-container fluid>
    <v-table>
      <thead>
      <th>Wochentag</th>
      <th>Schulstunde</th>
      <th>Startdatum</th>
      <th>Enddatum</th>
      <th>Lehrer</th>
      <th>Klasse</th>
      <th>Status</th>
      </thead>
      <tbody>
        <tr v-for=""></tr>
      </tbody>
    </v-table>
  </v-container>

</template>

<style scoped>

</style>