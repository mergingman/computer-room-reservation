import { createRouter, createWebHistory } from 'vue-router'
import RoomView from "@/views/WeekRoomView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'week-view',
      alias: "/rooms",
      component: RoomView
    },
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import()
    // }
  ]
})

export default router
