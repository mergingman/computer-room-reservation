import {createApp, markRaw} from 'vue'
import App from './App.vue'
import router from './router'

import {createVuetify} from 'vuetify';
import type {ThemeDefinition} from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';
import 'vuetify/dist/vuetify.min.css';
import 'v-calendar/style.css';
import {createPinia} from "pinia";
// @ts-ignore
import VCalendar from 'v-calendar';

const customTheme: ThemeDefinition = {
    primary: '#8CBDB9', // Your primary color
    secondary: '#E09E50', // Your secondary color
    tertiary: '#2D3E4E',
    background: '#E8ECEB', // Your background color
    dark: false,
    colors: {
        background: '#E8ECEB',
        tertiary: '#2F4454',
        primary: '#2F4454',
        'primary-darken-1': '#3700B3',
        secondary: '#2E151B',
        'secondary-darken-1': '#018786',
        error: '#B00020',
        info: '#2196F3',
        success: '#4CAF50',
        warning: '#FB8C00',
        filler: '#E8ECEB',

    },
};

const vuetify = createVuetify({
        components,
        directives,
        theme: {
            defaultTheme: 'customTheme',
            themes: {
                customTheme,
            },
        },
    }
)



const pinia = createPinia();
pinia.use(({store}) => {
    store.router = markRaw(router)
})
const app = createApp(App)

app.use(router)
app.use(vuetify)
app.use(VCalendar, {})
app.use(pinia)

app.mount('#app')