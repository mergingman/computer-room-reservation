import { ref } from 'vue'
import { defineStore } from 'pinia'

// this is a store, which means it provides state that can be used and modified throughout the entire application and
// in/by every component
export const useMenuStore = defineStore('menu', () => {
    const isTableMenuOpen = ref(false)
    function fetchFromApi(path: string, queryParam?: Map<string, string>): Promise<Response> {

    }

    return { loading, fetchFromApi }
})