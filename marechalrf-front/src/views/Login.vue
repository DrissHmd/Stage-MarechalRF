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
        <p v-if="errorMessage" style="color: red;">{{ errorMessage }}</p>
        <button type="submit" class="login-button">Se connecter</button>
      </form>
      <router-link to="/register" class="register-link">Pas de compte ? Inscrivez-vous ici</router-link>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios, { AxiosError } from 'axios';
import { useRouter } from 'vue-router';

export default defineComponent({
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Login',
  setup() {
    const username = ref('');
    const password = ref('');
    const router = useRouter();
    const errorMessage = ref('');

    const login = async () => {
      try {
        console.log('Login attempt:', { username: username.value, password: password.value });

        const response = await axios.post('http://localhost:8080/api/auth/login', {
          username: username.value,
          password: password.value,
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        const { token, role } = response.data;
        console.log("Response.data : ", response.data);
        console.log("Role id : ", role);

        if (!role) {
          errorMessage.value = 'Aucun rôle trouvé pour cet utilisateur';
          return;
        }

        localStorage.setItem('token', token);
        localStorage.setItem('roleId', role.toString());

        if (role === 2) {
          router.push('/dashboard');
        } else {
          router.push('/');
        }
      } catch (error) {
        console.log('Login error:', error);

        const axiosError = error as AxiosError;
        if (axiosError.response) {
          console.log('Response error data:', axiosError.response.data);
          console.log('Response error status:', axiosError.response.status);

          if (axiosError.response.status === 401) {
            errorMessage.value = 'Identifiant ou mot de passe incorrect';
          } else if (axiosError.response.status === 404) {
            errorMessage.value = "L'utilisateur n'existe pas";
          } else {
            errorMessage.value = `Une erreur inattendue est survenue. Veuillez réessayer plus tard. (Status: ${axiosError.response.status})`;
          }
        } else if (axiosError.request) {
          console.log('Request error:', axiosError.request);
          errorMessage.value = 'Aucune réponse du serveur. Veuillez vérifier votre connexion.';
        } else {
          console.log('General error:', axiosError.message);
          errorMessage.value = `Une erreur inattendue est survenue. Veuillez réessayer plus tard. (Error: ${axiosError.message})`;
        }
      }
    };

    axios.interceptors.request.use(
      config => {
        const token = localStorage.getItem('token');
        if (token) {
          config.headers['Authorization'] = 'Bearer ' + token;
        }
        return config;
      },
      error => {
        return Promise.reject(error);
      }
    );

    return {
      username,
      password,
      login,
      errorMessage
    };
  }
});
</script>



<style scoped>
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
