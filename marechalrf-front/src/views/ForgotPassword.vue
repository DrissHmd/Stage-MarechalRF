<template>
    <div class="forgot-password-container">
      <h1>Mot de passe oublié</h1>
      <form @submit.prevent="handleForgotPassword">
        <div class="form-group">
          <label for="email">Adresse email:</label>
          <input type="email" id="email" v-model="email" required />
        </div>
        <button type="submit">Envoyer le code de vérification</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        email: ""
      };
    },
    methods: {
      async sendVerificationEmail(email) {
        try {
          // Étape 1: Vérification de l'existence de l'email
          const verifyResponse = await fetch('http://localhost:8080/api/auth/check-email', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email }),
            credentials: 'include',
          });

          if (!verifyResponse.ok) {
            if (verifyResponse.status === 404) {
              throw new Error("L'adresse email n'est pas enregistrée.");
            } else {
              throw new Error('Erreur lors de la vérification de l\'adresse email.');
            }
          }

          const verifyData = await verifyResponse.json();
          console.log('Email verification successful:', verifyData);

          // Étape 2: Envoi du code de vérification
          const response = await fetch('http://localhost:8080/api/auth/send-verification-code', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email }),
            credentials: 'include',
          });

          if (!response.ok) {
            throw new Error('Erreur lors de l\'envoi du code de vérification.');
          }

          const data = await response.json();
          console.log('Verification code sent successfully:', data);
          alert('Code de vérification envoyé avec succès');
          
        } catch (error) {
          console.error(error);
          alert(error.message || 'Erreur lors de l\'envoi du code de vérification.');
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .forgot-password-container {
    max-width: 400px;
    margin: 0 auto;
    padding: 2rem;
    background: white;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  
  .form-group {
    margin-bottom: 1rem;
  }
  
  .form-group label {
    display: block;
    margin-bottom: 0.5rem;
    text-align: left;
  }
  
  .form-group input {
    width: 100%;
    padding: 0.5rem;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  button {
    padding: 0.5rem 1rem;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  </style>
  