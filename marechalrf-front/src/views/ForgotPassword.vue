<template>
  <div class="forgot-password-container">
    <div class="forgot-password-box">
      <h1>Réinitialiser le mot de passe</h1>
      <form @submit.prevent="resetPassword">
        <div class="form-group">
          <label for="email">Adresse e-mail</label>
          <input type="email" id="email" v-model="email" required />
        </div>

        <div v-if="isCodeSent" class="form-group">
          <label for="code">Code de vérification</label>
          <input type="text" id="code" v-model="code" required />
        </div>

        <div v-if="isCodeSent" class="form-group">
          <label for="newPassword">Nouveau mot de passe</label>
          <input type="password" id="newPassword" v-model="newPassword" required />
        </div>

        <p v-if="errorMessage" style="color: red;">{{ errorMessage }}</p>
        <p v-if="successMessage" style="color: green;">{{ successMessage }}</p>

        <button type="submit" class="forgot-password-button">
          {{ isCodeSent ? 'Réinitialiser le mot de passe' : 'Envoyer le code' }}
        </button>
      </form>
      <router-link to="/login" class="login-link">Se connecter</router-link>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios from 'axios';

export default defineComponent({
  name: 'ForgotPassword',
  setup() {
    const email = ref('');
    const code = ref('');
    const newPassword = ref('');
    const errorMessage = ref('');
    const successMessage = ref('');
    const isCodeSent = ref(false);

    const resetPassword = async () => {
      try {
        if (!isCodeSent.value) {
          // Envoyer le code de vérification
          await axios.post('http://localhost:8080/api/auth/send-verification-code', null, {
            params: { email: email.value },
            headers: { 'Content-Type': 'application/json' },
          });

          successMessage.value = 'Le code de vérification a été envoyé à votre adresse e-mail.';
          isCodeSent.value = true; // Passer à l'étape de réinitialisation du mot de passe
        } else {
          // Réinitialiser le mot de passe
          await axios.post('http://localhost:8080/api/auth/reset-password', null, {
            params: {
              email: email.value,
              code: code.value,
              newPassword: newPassword.value,
            },
            headers: { 'Content-Type': 'application/json' },
          });

          successMessage.value = 'Mot de passe réinitialisé avec succès !';
          errorMessage.value = '';
        }
      } catch (error) {
        if (axios.isAxiosError(error)) {
          if (error.response && error.response.status === 404) {
            errorMessage.value = "Aucun utilisateur n'est associé à cet e-mail.";
          } else if (error.response && error.response.status === 400) {
            errorMessage.value = 'Code de vérification invalide ou expiré.';
          } else {
            errorMessage.value = 'Une erreur inattendue est survenue. Veuillez réessayer plus tard.';
          }
        } else {
          errorMessage.value = 'Une erreur inattendue est survenue. Veuillez réessayer plus tard.';
        }
        successMessage.value = ''; // Vider le message de succès en cas d'erreur
      }
    };

    return {
      email,
      code,
      newPassword,
      resetPassword,
      errorMessage,
      successMessage,
      isCodeSent,
    };
  },
});
</script>

<style scoped>
.forgot-password-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.forgot-password-box {
  background: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.forgot-password-box h1 {
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

.forgot-password-button {
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

.forgot-password-button:hover {
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
