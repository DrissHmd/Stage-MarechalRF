import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import About from '../views/About.vue';
import Contact from '../views/Contact.vue';
import TrainingRequest from '../views/TrainingRequest.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import Dashboard from '../views/Dashboard.vue'

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
    path: '/training-request',
    name: 'TrainingRequest',
    component: TrainingRequest
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
    meta: { requiresAuth: true }
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

router.beforeEach((to, from, next) => {
  console.log(`Navigating to ${to.path}`);
  if (to.matched.some(record => record.meta.requiresAuth)) {
      const token = localStorage.getItem('token');
      console.log(`Token found: ${token}`);
      if (!token) {
          console.log(`No token found, redirecting to /login`);
          next({ path: '/login' });
      } else {
          const userRole = localStorage.getItem('role'); // Suppose that you store the user role in localStorage
          console.log(`User role: ${userRole}`);
          if (to.matched.some(record => record.meta.requiresAdmin) && userRole !== 'ADMIN') {
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