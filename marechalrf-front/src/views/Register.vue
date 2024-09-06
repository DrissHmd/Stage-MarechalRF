<template>
  <div class="register-container">
    <div class="register-box">
      <h1>Inscription</h1>
      <form @submit.prevent="register">
        <div class="form-group">
          <label for="first_name">Prénom</label>
          <input type="text" id="first_name" v-model="first_name" required />
        </div>
        <div class="form-group">
          <label for="last_name">Nom</label>
          <input type="text" id="last_name" v-model="last_name" required />
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" id="email" v-model="email" required />
        </div>
        <div class="form-group">
          <label for="phone">Numéro</label>
          <input type="text" id="phone" v-model="phone" required />
        </div>
        <div class="form-group">
          <label for="username">Identifiant</label>
          <input type="text" id="username" v-model="username" required />
        </div>
        <div class="form-group">
          <label for="password">Mot de passe</label>
          <input type="password" id="password" v-model="password" required />
        </div>
        <p v-if="errorMessage" style="color: red;">{{ errorMessage }}</p>
        <button type="submit" class="register-button">S'inscrire</button>
      </form>
      <router-link to="/login" class="login-link">Déjà un compte ? Connectez-vous ici</router-link>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export default defineComponent({
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Register',
  setup() {
    const first_name = ref('');
    const last_name = ref('');
    const email = ref('');
    const phone = ref('');
    const username = ref('');
    const password = ref('');
    const router = useRouter();
    const errorMessage = ref('');

    const register = async () => {
      try {
        const response = await axios.post('http://localhost:8080/api/auth/register', {
          first_name: first_name.value,
          last_name: last_name.value,
          email: email.value,
          phone: phone.value,
          username: username.value,
          password: password.value,
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        console.log('Registration successful:', response.data);
        localStorage.setItem('token', response.data.token);
        localStorage.setItem('roleId', response.data.roleId);
        localStorage.setItem('userId', response.data.id)
        router.push('/');
      } catch (error) {
        if (axios.isAxiosError(error)) {
          if (error.response && error.response.status === 400) {
            errorMessage.value = error.response.data as string;
          } else {
            errorMessage.value = 'Une erreur inattendue est survenue. Veuillez réessayer plus tard.';
          }
        } else {
          errorMessage.value = 'Une erreur inattendue est survenue. Veuillez réessayer plus tard.';
        }
      }
    };

    return {
      first_name,
      last_name,
      email,
      phone,
      username,
      password,
      register,
      errorMessage
    };
  }
});
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.register-box {
  background: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.register-box h1 {
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

.register-button {
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

.register-button:hover {
  background-color: #0056b3;
}

.login-link {
  display: block;
  margin-top: 20px;
  color: #007bff;
  text-decoration: none;
}

.login-link:hover {
  text-decoration: underline;
}
</style>
