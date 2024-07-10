<template>
  <div class="dashboard-container">
    <h1>Tableau de bord</h1>
    <div v-if="user" class="dashboard-card">
      <h2>Informations Utilisateur</h2>
      <p>Nom: {{ user?.name }}</p>
      <p>Email: {{ user?.email }}</p>
      <p>Rôle: {{ user?.role }}</p>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { useRouter } from 'vue-router';

interface User {
  name: string;
  email: string;
  role: string;
}

export default defineComponent({
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Dashboard',
  setup() {
    const user = ref<User | null>(null);
    const router = useRouter();

    const fetchUserData = () => {
      const token = localStorage.getItem('token');
      const role = localStorage.getItem('role');
      const userRole = role?.split("\"",6).pop();

      if (!token) {
        console.log('No token found, redirecting to login...');
        router.push('/login');
        return;
      }

      if (userRole !== 'ROLE_ADMIN') {
        console.log('Access restricted, redirecting to home...');
        router.push('/');
        return;
      }

      user.value = {
        name: 'Nom utilisateur', 
        email: 'email@example.com', 
        role: userRole
      };
    };

    fetchUserData();

    return {
      user,
    };
  },
});
</script>

<style scoped>
.dashboard-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.dashboard-card {
  background-color: #f0f0f0;
  border-radius: 5px;
  padding: 20px;
  margin-top: 20px;
}

.dashboard-card h2 {
  margin-bottom: 10px;
  color: #333;
}
</style>
