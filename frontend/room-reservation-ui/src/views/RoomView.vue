<script setup lang="ts">
import type {Ref} from "vue";
import {computed, onMounted, ref} from "vue";
import {useFetchStore} from '../stores/fetch'
import type {WeekOverviewResponse} from "../../types/dto";
import type {RoomReservation} from "../../types/model";

const props = defineProps<{
  title?: string
  likes?: number
  day?: string
}>()

const fetchSotore = useFetchStore();
const {loading, fetchFromApi} = fetchSotore

const calendarAttrs = ref([
  {
    key: 'today',
    dot: true,
    dates: new Date(),
  },
]);

const daysOfWeek = ref(["Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"]);
type TimeSlot = { label: string, lessonNr: number }
const timeSlots: Ref<TimeSlot[]> = ref([
  {label: "8:00", lessonNr: 1},
  {label: "08:45", lessonNr: 2},
  {label: "09:30", lessonNr: null},
  {label: "09:45", lessonNr: 3},
  {label: "10:30", lessonNr: 4},
  {label: "11:15", lessonNr: null},
  {label: "11:30", lessonNr: 5},
  {label: "12:15", lessonNr: 6},
  {label: "13:00", lessonNr: null},
  {label: "13:30", lessonNr: 7},
  {label: "14:15", lessonNr: 8},
  {label: "15:00", lessonNr: null},
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

function onNewDaySelected(day: any, e: MouseEvent): void {
  selectedDay.value = day.date;
  loadContent();
}

async function loadContent() {
  if (selectedRoom.value) {
    const queryParams = new Map();
    queryParams.set("date", formatDateToYYYYMMDD(selectedDay.value));
    queryParams.set("roomName", selectedRoom.value);
    const response = await fetchFromApi("/rooms/weekOverview", queryParams);
    weekReservations.value = await response.json();
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
      <v-col cols="12" md="3">
        <v-container>
          <VCalendar
              expanded
              :attributes="calendarAttrs"
              @dayclick="onNewDaySelected"/>
        </v-container>
      </v-col>
      <v-col cols="12" md="9">
        <v-row>
          <v-combobox
              v-model="selectedRoom"
              :items="roomNames"
              label="Raum"
              prepend-icon="mdi-magnify"
              @update:focused="onSearchBarFocused()"
              @update:menu="onRoomSelected()"
          ></v-combobox>
        </v-row>
        <v-row>
          <v-container fluid>
            <v-skeleton-loader :loading="loading">
            <v-data-table v-if="selectedRoom" class="flex-grow-1" color="primary">
                <thead>
                <tr>
                  <th></th>
                  <th></th>
                  <th v-for="day in daysOfWeek" :key="day"
                      :class="{ 'selected-day': day === weekReservations.weekDay }">{{ day }}
                  </th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(timeSlot, timeSlotIndex) in timeSlots" :key="timeSlotIndex">
                  <th v-if="timeSlot.lessonNr" class="pa-2">{{ timeSlot.lessonNr }}</th>
                  <v-divider v-else></v-divider>
                  <th v-if="timeSlot.lessonNr" class="pa-0 float-end">{{ timeSlot.label }}</th>
                  <v-divider v-else></v-divider>
                  <td id="reservation-card-td-" v-for="(day, dayIndex) in daysOfWeek" :key="dayIndex">
                    <v-card
                        v-if="timeSlot.lessonNr && sortedReservations[dayIndex].find(r => r.lessonNr.includes(timeSlot.lessonNr.toString()))"
                        class="timetable-card ma-1 reservation-card d-flex" rounded>
                      <v-card-text>
                        {{
                          getCardText(timeSlot, sortedReservations[dayIndex].find(r => r.lessonNr.includes(timeSlot.lessonNr.toString())))
                        }}
                      </v-card-text>
                    </v-card>
                    <v-divider v-else-if="!timeSlot.lessonNr" thickness="10"></v-divider>
                    <v-card v-else class="timetable-card d-flex">
                      <v-card-text></v-card-text>
                    </v-card>
                  </td>
                </tr>
                </tbody>
            </v-data-table>
            </v-skeleton-loader>
          </v-container>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>


<style>
.selected-day {
  background-color: var(--schule-primary-color); /* Customize the background color as needed */
  border: 2px solid var(--schule-dark-color); /* Customize the border styles as needed */
}

.reservation-card {
  background-color: #E09E50;
}


</style>