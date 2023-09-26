import {createApp, markRaw} from 'vue'
import App from './App.vue'
import router from './router'

import {createVuetify} from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
// @ts-ignore
import VCalendar from 'v-calendar';
import customTheme from './theme';

const vuetify = createVuetify({
        components,
        directives,
        theme: {
            defaultTheme: 'customTheme',
            themes: {
                customTheme,
            }
        }
    }
)

import 'vuetify/dist/vuetify.min.css';
import 'v-calendar/style.css';
// import AppCssTemplate from './AppCssTemplate.css'
// import '@/assets/css/base.css'

import {createPinia} from "pinia";

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