import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

// this is a store, which means it provides state that can be used and modified throughout the entire application and
// in/by every component
export const useFetchStore = defineStore('fetch', () => {
  const loading = ref(false)
  async function fetchFromApi(path: string, queryParam?: Map<string, string>): Promise<Response> {
    const baseApiUrl =  "http://localhost:8080/api";
    const options = {
      method: "POST",
      headers: new Headers({
        'Authorization': 'Basic '+ btoa('sysadmin:password'),
        'Content-Type': 'application/x-www-form-urlencoded'
      }),
      body: 'A=1&B=2'
    }
    let url = baseApiUrl + path;
    if (queryParam !== undefined) {
      url = buildUrl(url, queryParam);
    }
    loading.value = true;
    const response = await fetch(url);
    loading.value = false
    return response;
  }

  function buildUrl(baseUrl: string, queryParams: Map<string, string>): string {
    // Create an array to hold the query string key-value pairs
    const queryStringPairs: string[] = [];

    // Iterate through the map and convert it into an array of key-value strings
    for (const [key, value] of queryParams) {
      queryStringPairs.push(`${encodeURIComponent(key)}=${encodeURIComponent(value)}`);
    }

    // Combine the base URL and the query string
    const queryString = queryStringPairs.join('&');
    const url = `${baseUrl}${baseUrl.includes('?') ? '&' : '?'}${queryString}`;

    return url;
  }

  return { loading, fetchFromApi }
})


