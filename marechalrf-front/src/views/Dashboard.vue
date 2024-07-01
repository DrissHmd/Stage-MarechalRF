<template>
  <div class="dashboard-container">
    <h1>Tableau de bord</h1>
    
    <div class="dashboard-card">
      <h2>Informations Utilisateur</h2>
      <p><strong>Nom:</strong> John Doe</p>
      <p><strong>Email:</strong> john.doe@example.com</p>
      <p><strong>Numéro de téléphone:</strong> +1234567890</p>
    </div>
    
    <div class="dashboard-card">
      <h2>Dernières Commandes</h2>
      <ul>
        <li>Commande #1234 - 2024-06-27</li>
        <li>Commande #5678 - 2024-06-26</li>
        <li>Commande #9012 - 2024-06-25</li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios from '@/axios'; // Import the configured axios instance
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
        if (error.response.status === 401) {
          console.log('Unauthorized, redirecting to login...');
          router.push('/login');
        } else if (error.response) {
          // Autres erreurs de réponse
          console.error('Error response:', error.response);
        } else if (error.request) {
          // Erreurs de demande
          console.error('Error request:', error.request);
        } else {
          // Autres erreurs
          console.error('Error:', error.message);
        }
      }
    };

    fetchDashboard();

    return {
      message
    };
  }
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

.dashboard-card p, .dashboard-card ul {
  margin-bottom: 10px;
}

.dashboard-card ul {
  padding-left: 20px;
  list-style-type: none;
}
</style>
