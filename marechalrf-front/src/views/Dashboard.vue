<template>
  <div class="dashboard-container">
    <h1>Tableau de bord</h1>
    <div v-if="message" class="dashboard-card">
      <h2>Informations Utilisateur</h2>
      <p>{{ message }}</p>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios from '@/axios';
import { useRouter } from 'vue-router';

export default defineComponent({
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Dashboard',
  setup() {
    const message = ref('');
    const router = useRouter();

    const fetchDashboard = async () => {
      try {
        const response = await axios.get('/dashboard');
        message.value = response.data.message;
      } catch (error: any) {
        if (error.response && error.response.status === 401) {
          console.log('Unauthorized, redirecting to login...');
          router.push('/login');
        } else if (error.response) {
          console.error('Error response:', error.response);
        } else if (error.request) {
          console.error('Error request:', error.request);
        } else {
          console.error('Error:', error.message);
        }
      }
    };

    fetchDashboard();

    return {
      message,
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
