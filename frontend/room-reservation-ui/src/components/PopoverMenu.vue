<script setup lang="ts">
import {ref} from "vue";

const props = defineProps<{

}>()

const dialog = ref(false);
const dateValue = ref(new Date())
</script>

<template>
  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        min-width="50vw"
        max-width="90vw"
    >
      <template v-slot:activator="{ props }">
        <v-btn
            color="tertiary"
            v-bind="props"
            icon="mdi-menu"
        >
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">Details</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" md="4">
                <v-label>Datum</v-label>
              </v-col>
              <v-col cols="12" md="4">
                <v-label>Von</v-label>
              </v-col>
              <v-col cols="12" md="4">
                <v-label>Bis</v-label>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" md="4">
                <v-text-field
                    readonly
                    v-model="dateValue"
                >
                  {{new Date().toLocaleDateString()}}
                </v-text-field>
              </v-col>
              <v-col cols="12" md="4">
                <v-text-field
                    readonly
                    v-model="dateValue"
                >
                  {{new Date().toLocaleTimeString()}}
                </v-text-field>
              </v-col>
              <v-col cols="12" md="4">
                <v-text-field
                    readonly
                    v-model="dateValue"
                >
                  {{new Date().toLocaleTimeString()}}
                </v-text-field>
              </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue-darken-1"
              variant="text"
              @click="dialog = false"
          >
            Close
          </v-btn>
          <v-btn
              color="blue-darken-1"
              variant="text"
              @click="dialog = false"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<style scoped>

</style>