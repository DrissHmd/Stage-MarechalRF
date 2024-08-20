import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import About from '../views/About.vue';
import Contact from '../views/Contact.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import Dashboard from '../views/Dashboard.vue'
import UserEdit from '@/views/UserEdit.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/contact',
    name: 'Contact',
    component: Contact
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard,
    meta: { requiresAuth: true, requiresAdmin: true }
  },
  {
    path: '/user-edit',
    name: 'UserEdit',
    component: UserEdit,
    props: true,
    meta: { requiresAuth: true, requiresAdmin: true }
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
      const token = localStorage.getItem('token');
      console.log(`Token found: ${token}`);
      if (!token) {
          console.log(`No token found, redirecting to /login`);
          next({ path: '/login' });
      } else {
        const roleId = parseInt(localStorage.getItem('roleId') || '0', 10);
        console.log(`User role ID: ${roleId}`);
        if (to.matched.some(record => record.meta.requiresAdmin) && roleId !== 2) {
          console.log(`User is not admin, redirecting to /`);
          next({ path: '/' });
        } else {
          next();
        }
      }
  } else {
      next();
  }
});

export default router;