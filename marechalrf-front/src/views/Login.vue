<template>
  <div class="login-container">
    <div class="login-box">
      <h1>Connexion</h1>
      <form @submit.prevent="login">
        <div class="form-group">
          <label for="username">Identifiant</label>
          <input type="text" id="username" v-model="username" required />
        </div>
        <div class="form-group">
          <label for="password">Mot de passe</label>
          <input type="password" id="password" v-model="password" required />
        </div>
        <button type="submit" class="login-button">Se connecter</button>
      </form>
      <router-link to="/register" class="register-link">Pas de compte ? Inscrivez-vous ici</router-link>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

export default defineComponent({
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Login',
  setup() {
    const store = useStore();
    const username = ref('');
    const password = ref('');
    const router = useRouter();
    const message = ref('');

    const login = async () => {
      try {
        const response = await axios.post('http://localhost:8080/api/auth/login', {
          username: username.value,
          password: password.value,
        });
        console.log('Login successful:', response.data);
        store.dispatch('login', response.data.user); 
        router.push('/');
      } catch (error: any) {
        console.error('Login failed:', error);
        // Gérez les erreurs de connexion ici
        message.value = 'Login failed: ' + error.message;
      }
    };

    return {
      username,
      password,
      login,
      message
    };
  }
});
</script>

<style scoped>
/* Votre style reste inchangé */
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.login-box {
  background: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.login-box h1 {
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
}

.form-group {
  margin-bottom: 20px;
  text-align: left;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #333;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 16px;
}

.login-button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  font-size: 16px;
  cursor: pointer;
  margin-top: 10px;
}

.login-button:hover {
  background-color: #0056b3;
}

.register-link {
  display: block;
  margin-top: 20px;
  color: #007bff;
  text-decoration: none;
}

.register-link:hover {
  text-decoration: underline;
}
</style>
