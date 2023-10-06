<script setup lang="ts">
import type {Ref} from "vue";
import {computed, onMounted, ref} from "vue";
import {useFetchStore} from '../stores/fetch'
import type {WeekOverviewResponse} from "../../types/dto";
import type {RoomReservation} from "../../types/model";
import {storeToRefs} from "pinia";
import theme from "../theme";
import PopoverMenu from "@/components/PopoverMenu.vue";

const props = defineProps<{
  title?: string
  likes?: number
  day?: string
}>()

const fetchStore = useFetchStore();
const {fetchFromApi} = fetchStore
const {loading} = storeToRefs(fetchStore)

const loadingTable = ref(false)

const calendarAttrs = ref([
  {
    key: 'today',
    dot: true,
    dates: new Date(),
  },
]);

const daysOfWeek = ref(["Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"]);
type TimeSlot = { label: string, lessonNr: number | null }
const timeSlots: Ref<TimeSlot[]> = ref([
  {label: "8:00", lessonNr: 1},
  {label: "08:45", lessonNr: 2},
  {label: "09:45", lessonNr: 3},
  {label: "10:30", lessonNr: 4},
  {label: "11:30", lessonNr: 5},
  {label: "12:15", lessonNr: 6},
  {label: "13:30", lessonNr: 7},
  {label: "14:15", lessonNr: 8},
  {label: "15:15", lessonNr: 9},
  {label: "16:00", lessonNr: 10}
]);

const roomNames: Ref<Array<string>> = ref([]);
const weekReservations: Ref<WeekOverviewResponse> = ref({
  roomName: '',
  date: '',
  weekReservationList: []
})
const selectedRoom = ref(null);
const selectedDay = ref(new Date());

const sortedReservations: Ref<RoomReservation[][]> = computed(() => {
  let reservations: RoomReservation[][] = [];
  daysOfWeek.value.forEach((day, index) => { //for monday to friday
        reservations[index] = JSON.parse(  //copy array
            JSON.stringify(
                weekReservations
                    .value
                    .weekReservationList
                    .filter((reservation) => reservation.weekday == day) //filter by weekday
                    .sort((a, b) => { //sort by lessonNr
                      if (a.lessonNr < b.lessonNr) {
                        return -1;
                      }
                      if (a.lessonNr > b.lessonNr) {
                        return 1;
                      }
                      return 0;
                    })
            )
        )
      }
  )
  return reservations; // returns 5 array from monday to friday having the
})

const showTable = computed(() => !loadingTable.value && roomNames.value.find(room => room === selectedRoom.value))
function onNewDaySelected(day: any, e: MouseEvent): void {
  selectedDay.value = day.date;
  loadContent();
}

async function loadContent() {
  if (selectedRoom.value) {
    const queryParams = new Map();
    queryParams.set("date", formatDateToYYYYMMDD(selectedDay.value));
    queryParams.set("roomName", selectedRoom.value);
    loadingTable.value = true;
    const response = await fetchFromApi("/reservations/weekOverview", queryParams);
    loadingTable.value = false;
    weekReservations.value = await response.json();

    if (response.ok) {
    }
    else {
      // selectedRoom.value = null;
    }
  }

}

function formatDateToYYYYMMDD(date: Date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are 0-based
  const day = String(date.getDate()).padStart(2, '0');
  return `${year}-${month}-${day}`;
}

function onRoomSelected() {
  loadContent();
}

function onSearchBarFocused() {
  updateRoomNamesList()
}

async function updateRoomNamesList() {
  const response = await fetchFromApi("/computerRoomNames");
  roomNames.value = await response.json();
}

function getCardText(timeSlot: TimeSlot, reservation: RoomReservation | undefined): string {
  if (reservation && reservation.lessonNr.includes(timeSlot.lessonNr.toString())) {
    return reservation.teacher;
  }
  return "";
}

onMounted(() => {
  updateRoomNamesList();
  loadContent();
})
</script>

<template>
  <v-container fluid class="fill-height">
    <v-row>
      <v-col cols="12" md="4" lg="3" xl="2" >
        <v-container>
          <VCalendar
              :attributes="calendarAttrs"
              @dayclick="onNewDaySelected"/>
        </v-container>
      </v-col>
      <v-col cols="12" md="8" lg="9" xl="10">
        <v-row>
          <v-combobox
              :loading="loading"
              v-model="selectedRoom"
              :items="roomNames"
              label="Raum"
              prepend-icon="mdi-magnify"
              @update:focused="onSearchBarFocused()"
              @update:modelValue="onRoomSelected()"
          ></v-combobox>
        </v-row>
        <v-row v-if="loadingTable" justify="center">
          <v-progress-circular
              color="primary"
              indeterminate
          >
          </v-progress-circular>
        </v-row>
        <v-row v-else-if="!showTable" justify="center">
          <v-banner color="primary" rounded>
            <v-icon icon="mdi-information" class="ma-1" size="20"></v-icon>
            <v-banner-text class="ma-1">Bitte Raum auswählen</v-banner-text>
          </v-banner>
        </v-row>
        <v-row align="center" justify="center">
          <v-container fluid>
            <v-table v-if="showTable" class="flex-shrink-1">
              <thead>
              <tr>
                <th></th>
                <th></th>
                <th v-for="day in daysOfWeek" :key="day"
                    :class="{ 'selected-day': day === weekReservations.weekDay, 'selected-day-th': day === weekReservations.weekDay  }"
                >{{ day }}
                </th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(timeSlot, timeSlotIndex) in timeSlots" :key="timeSlotIndex">
                <td class="pa-2 tableNumberCol">{{ timeSlot.lessonNr }}</td>
                <td class="pa-0 tableTimeCol">{{ timeSlot.label }}</td>
                <td id="reservation-card-td-"
                    v-for="(day, dayIndex) in daysOfWeek"
                    :class="{ 'selected-day': day === weekReservations.weekDay, 'tableDataCol': true }"
                    :key="dayIndex"
                >
                  <v-card color="secondary"
                          v-if="sortedReservations[dayIndex].find(r => r.lessonNr.includes(timeSlot.lessonNr.toString()))"
                          class="timetable-card ma-1 reservation-card d-flex" rounded>
                    <v-card-text>
                      {{
                        getCardText(timeSlot, sortedReservations[dayIndex].find(r => r.lessonNr.includes(timeSlot.lessonNr.toString())))
                      }}
                    </v-card-text>
                    <PopoverMenu></PopoverMenu>
                  </v-card>
                  <v-card v-else class="timetable-card d-flex" color="filler">
                    <v-card-text></v-card-text>
                  </v-card>
                </td>
              </tr>
              <tr>
              </tr>
              </tbody>
            </v-table>
          </v-container>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>


<style>
.selected-day {
  background-color: rgba(224, 224, 224, 0.99);

}

.selected-day-th {
  border: 2px solid black;
  border-radius: 10px 10px 0px 0px;
}

.reservation-card {
  background-color: #E09E50;
}

td, th {
  background-color: #E8ECEB;
}

.tableDataCol {
  width: 18%;
}

.tableNumberCol {
  width: 3%;
}

.tableTimeCol {
  width: 7%;
}

.slot-color-0{
  color: #DA7B93;
}

.slot-color-1{
  color: #376E6F;
}

.slot-color-2{
  color: #1C3334;
}

</style>