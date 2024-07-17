<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="contact-page">
    <div class="background-static"></div>
    <div class="contact-container">
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="nom">Nom:</label>
          <input type="text" id="nom" v-model="formData.nom" required />
        </div>
        <div class="form-group">
          <label for="prenom">Prénom:</label>
          <input type="text" id="prenom" v-model="formData.prenom" required />
        </div>
        <div class="form-group">
          <label for="entreprise">Entreprise:</label>
          <input type="text" id="entreprise" v-model="formData.entreprise" required />
        </div>
        <div class="form-group">
          <label for="fonction">Fonction:</label>
          <input type="text" id="fonction" v-model="formData.fonction" required />
        </div>
        <div class="form-group">
          <label for="email">Adresse mail:</label>
          <input type="email" id="email" v-model="formData.email" required />
        </div>
        <div class="form-group">
          <label for="telephone">Numéro de téléphone:</label>
          <input type="tel" id="telephone" v-model="formData.telephone" />
        </div>
        <div class="form-group">
          <label for="message">Message:</label>
          <textarea id="message" v-model="formData.message"></textarea>
        </div>
        <div class="form-group">
          <label for="consent">
            Mentions légales:
            <input type="checkbox" id="consent" v-model="formData.consent" required />
            En cochant la case ci-dessous, j'accepte que les informations et documents que je transmets à MARECHAL Recrutement et Formation soient exploités dans le cadre d'études tarifaires et la demande de devis d'assurance, ainsi que la relation commerciale qui peut en découler, conformément au Règlement Général de Protection des Données
          </label>
        </div>
        <button type="submit">Envoyer</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formData: {
        nom: "",
        prenom: "",
        entreprise: "",
        fonction: "",
        email: "",
        telephone: "",
        message: "",
        consent: false
      }
    };
  },
  methods: {
    async submitForm() {
      try {
        const response = await fetch('http://localhost:8080/api/contact', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.formData),
          credentials: 'include',
        });
        if (!response.ok) {
          throw new Error('Erreur lors de l\'envoi du formulaire');
        }
        const data = await response.json();
        console.log('Contact form submitted successfully:', data);
        alert('Message envoyé avec succès');
        // Réinitialiser le formulaire après l'envoi
        this.formData = {
          nom: '',
          prenom: '',
          entreprise: '',
          fonction: '',
          email: '',
          telephone: '',
          message: '',
          consent: false
        };
      } catch (error) {
        console.error(error);
        alert('Erreur lors de l\'envoi du message');
      }
    }
  }
};
</script>


<style scoped>
.contact-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 2rem;
  position: relative;
  overflow: hidden;
}

.background-static {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('/public/background.jpg');
  background-attachment: fixed;
  background-size: cover;
  background-position: center;
  z-index: -1;
}

.contact-container {
  background: rgba(255, 255, 255, 0.9);
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  width: 100%;
  z-index: 1;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.button {
  padding: 0.5rem 1rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.button:hover {
  background-color: #0056b3;
}

.response-message {
  margin-top: 1rem;
  color: green;
}
</style>
