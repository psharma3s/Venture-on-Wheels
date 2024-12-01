import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/views/Home.vue';
import CreateTrip from '@/views/CreateTrip.vue';
import RouteCustomization from '@/views/RouteCustomization.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage,
  },
  {
    path: '/create-trip',
    name: 'CreateTrip',
    component: CreateTrip,
  },
  {
    path: '/route-customization',
    name: 'RouteCustomization',
    component: () => import('@/views/RouteCustomization.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
