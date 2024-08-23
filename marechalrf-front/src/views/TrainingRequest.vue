<template>
  <div class="form-with-tabs">
    <!-- Tabs are only for indication, no navigation here -->
    <ul class="tabs">
      <li :class="{ active: currentTab === 0 }">Informations Générales</li>
      <li :class="{ active: currentTab === 1 }">Questions Supplémentaires</li>
      <li :class="{ active: currentTab === 2 }">Handicap</li>
    </ul>

    <!-- Render the current component based on the tab -->
    <transition name="fade">
      <component
        :is="currentComponent"
        :form="form"
        @update-form="updateForm"
      />
    </transition>

    <!-- Navigation and submission buttons -->
    <div class="buttons">
      <button @click="prevTab" :disabled="currentTab === 0">Précédent</button>
      <button @click="nextTab" :disabled="!isValidForm">Suivant</button>
      <button @click="submitForm" :disabled="!canSubmit">Envoyer la Demande</button>
    </div>
  </div>
</template>

<script>
import Section1 from './Section1.vue';
import Section2 from './Section2.vue';
import Section3 from './Section3.vue';

export default {
  components: {
    Section1,
    Section2,
    Section3
  },
  data() {
    return {
      currentTab: 0,
      form: {
        nom: '',
        prenom: '',
        entreprise: '',
        fonction: '',
        email: '',
        phone: '',
        besoinsFormation: '',
        sujetsAborder: '',
        objectifs: '',
        exigencesSpecifiques: '',
        typeFormation: '',
        effectif: '',
        duree: '',
        intervenant: '',
        publicFormer: '',
        postes: '',
        experience: '',
        connaissances: '',
        effectifGroupes: '',
        risques: '',
        horaires: '',
        dates: '',
        frequence: '',
        lieu: '',
        collations: '',
        equipement: '',
        outilsTech: '',
        outilsPedago: '',
        budget: '',
        financement: '',
        modalites: '',
        handicap: '',
        typeHandicap: '',
        amenagement: '',
        commentaires: ''
      }
    };
  },
  computed: {
    currentComponent() {
      switch (this.currentTab) {
        case 0:
          return 'Section1';
        case 1:
          return 'Section2';
        case 2:
          return 'Section3';
        default:
          return 'Section1';
      }
    },
    isValidForm() {
      if (this.currentTab === 0) {
        return this.form.nom && this.form.prenom && this.form.entreprise && this.form.fonction && this.form.email;
      }
      if (this.currentTab === 1) {
        return this.form.besoinsFormation && this.form.sujetsAborder && this.form.objectifs && this.form.exigencesSpecifiques && this.form.typeFormation && this.form.effectif && this.form.duree;
      }
      if (this.currentTab === 2) {
        if (this.form.handicap === 'Oui') {
          return this.form.typeHandicap && this.form.amenagement;
        }
        return true; // Juste pour ne pas bloquer l'envoi s'il n'y a pas de handicap
      }
      return false;
    },
    canSubmit() {
      return this.currentTab === 2 && this.isValidForm;
    }
  },
  methods: {
    prevTab() {
      if (this.currentTab > 0) {
        this.currentTab--;
      }
    },
    nextTab() {
      if (this.currentTab < 2) {
        this.currentTab++;
        if (this.currentTab === 1) {
          this.sendNotification();
        }
      }
    },
    updateForm(updatedFields) {
      this.form = { ...this.form, ...updatedFields };
    },
    async submitForm() {
      try {
        const response = await fetch('http://localhost:8080/api/form-submit', { // Remplace par l'URL de ton service backend
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.form),
        });
        if (!response.ok) {
          throw new Error('Erreur lors de l\'envoi du formulaire');
        }
        const data = await response.json();
        console.log('Formulaire soumis avec succès:', data);
        alert('Formulaire envoyé avec succès');
        // Réinitialiser le formulaire après l'envoi
        this.form = {
          nom: '',
          prenom: '',
          entreprise: '',
          fonction: '',
          email: '',
          phone: '',
          besoinsFormation: '',
          sujetsAborder: '',
          objectifs: '',
          exigencesSpecifiques: '',
          typeFormation: '',
          effectif: '',
          duree: '',
          intervenant: '',
          publicFormer: '',
          postes: '',
          experience: '',
          connaissances: '',
          effectifGroupes: '',
          risques: '',
          horaires: '',
          dates: '',
          frequence: '',
          lieu: '',
          collations: '',
          equipement: '',
          outilsTech: '',
          outilsPedago: '',
          budget: '',
          financement: '',
          modalites: '',
          handicap: '',
          typeHandicap: '',
          amenagement: '',
          commentaires: ''
        };
      } catch (error) {
        console.error(error);
        alert('Erreur lors de l\'envoi du formulaire');
      }
    },
    async sendNotification() {
      try {
        const response = await fetch('http://localhost:8080/api/send-email', { // Remplace par l'URL de ton service backend pour envoyer un email
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            to: 'marechalretf@gmail.com',
            subject: 'Passage à la première section du formulaire',
            text: 'L’utilisateur a passé à la première section du formulaire.'
          }),
        });
        if (!response.ok) {
          throw new Error('Erreur lors de l\'envoi de la notification');
        }
        const data = await response.json();
        console.log('Notification envoyée:', data);
      } catch (error) {
        console.error('Erreur lors de l\'envoi de la notification:', error);
      }
    }
  }
};
</script>

<style scoped>
.form-with-tabs {
  max-width: 800px;
  margin: 0 auto;
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.tabs {
  display: flex;
  list-style-type: none;
  padding: 0;
  margin-bottom: 20px;
  justify-content: space-around;
}

.tabs li {
  flex: 1;
  text-align: center;
  padding: 10px;
  background-color: #f0f0f0;
  border: 1px solid #ccc;
}

.tabs li.active {
  background-color: #007bff;
  color: white;
}

.buttons {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  border: none;
  color: white;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin: 0 5px;
}

button:disabled {
  background-color: #d0d0d0;
  cursor: not-allowed;
}

button:hover:not(:disabled) {
  background-color: #0056b3;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to /* .fade-leave-active pour Vue 2 seulement */ {
  opacity: 0;
}
</style>
