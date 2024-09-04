<template>
  <div class="form-with-tabs">
    <!-- Onglets d'indication, pas de navigation ici -->
    <ul class="tabs">
      <li :class="{ active: currentTab === 0 }">Informations Générales</li>
      <li :class="{ active: currentTab === 1 }">Questions Supplémentaires</li>
      <li :class="{ active: currentTab === 2 }">Handicap</li>
    </ul>

    <!-- Affiche le composant actuel basé sur l'onglet -->
    <transition name="fade">
      <component
        :is="currentComponent"
        :form="form"
        @update-form="updateForm"
        @validate-email="validateEmail"
      />
    </transition>

    <!-- Boutons de navigation et soumission -->
    <div class="buttons">
      <button @click="prevTab" :disabled="currentTab === 0">Précédent</button>
      <button v-if="currentTab < 2" @click="nextTab" :disabled="!isValidForm">Suivant</button>
      <button v-if="currentTab === 2" @click="submitForm" :disabled="!canSubmit">Envoyer la Demande</button>
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
        consent: false,
        besoinsFormation: '',
        sujetsAborder: '',
        objectifs: '',
        exigencesSpecifiques: '',
        typeFormation: '',
        effectif: null,
        duree: null,
        handicap: '',
        typeHandicap: '',
        amenagement: '',
        commentaires: ''
      },
      emailError: ''
    };
  },
  watch: {
    // Surveiller les changements dans le champ 'handicap'
    'form.handicap'(newValue) {
      if (newValue === 'Oui' && !this.form.typeHandicap) {
        this.form.isValidForm = false;
      }
    },
    'form.typeHandicap'(newValue) {
      // Si 'handicap' est "Oui", vérifie que 'typeHandicap' n'est pas vide
      if (this.form.handicap === 'Oui' && !newValue) {
        this.form.isValidForm = false;
      }
    }
  },
  computed: {
    isValidForm() {
      // Validation basée sur l'onglet actuel
      if (this.currentTab === 0) {
        return this.form.nom && this.form.prenom && this.form.entreprise && this.form.fonction && this.form.email && this.form.consent && !this.emailError && !this.phoneError;
      }
      if (this.currentTab === 1) {
        return this.form.besoinsFormation && this.form.sujetsAborder && this.form.objectifs && this.form.exigencesSpecifiques &&
               this.form.typeFormation && this.form.effectif >= 1 && this.form.duree >= 1;
      }
      if (this.currentTab === 2) {
        if (!this.form.handicap) {
          return false;
        }
        if (this.form.handicap === 'Oui') {
          return this.form.typeHandicap != "" && this.form.typeHandicap != "Non";
        }
        return this.form.handicap === 'Non';
      }
      return true;
    },
    canSubmit() {
      // Validation finale avant soumission
      return this.isValidForm && this.currentTab === 2;
    },
    currentComponent() {
      const component = ['Section1', 'Section2', 'Section3'][this.currentTab];
      console.log('Composant actuel:', component);
      return component;
    }
  },
  methods: {
    prevTab() {
      if (this.currentTab > 0) {
        console.log('Retour à l\'onglet précédent:', this.currentTab + 1);
        this.currentTab--;
      }
    },
    async nextTab() {
      console.log('Validation de l\'onglet actuel:', this.currentTab + 1);
      if (this.isValidForm && this.currentTab < 2) {
        if (this.currentTab === 0) {
          console.log('Soumission de la notification après la première section');
          if (this.form.consent) {
            await this.sendNotification();
          }
        }
        this.currentTab++;
        console.log('Passage à l\'onglet suivant:', this.currentTab + 1);
      } else {
        console.log('Formulaire non valide pour avancer:', this.form);
      }
    },
    updateForm(updatedFields) {
      this.form = { ...this.form, ...updatedFields };
    },
    validateEmail(email) {
      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailPattern.test(email)) {
        this.emailError = 'Adresse email invalide.';
      } else {
        this.emailError = '';
      }
    },
    async sendNotification() {
      console.log('Envoi de la notification avec les données:', JSON.stringify(this.form, null, 2));
      try {
        const response = await fetch('http://localhost:8080/api/contact', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            nom: this.form.nom,
            prenom: this.form.prenom,
            entreprise: this.form.entreprise,
            fonction: this.form.fonction,
            email: this.form.email,
            telephone: this.form.phone,
            message: 'Contact venant du formulaire',
            consent: this.form.consent
          }),
          credentials: 'include',
        });
        console.log('Réponse du serveur:', response);
        if (!response.ok) {
          throw new Error('Erreur lors de l\'envoi du formulaire');
        }
        const data = await response.json();
        console.log('Formulaire de contact soumis avec succès:', data);
      } catch (error) {
        console.error('Erreur lors de l\'envoi des informations:', error);
      }
    },
    async submitForm() {
      // Logique de soumission finale du formulaire
      console.log('Soumission finale du formulaire:', this.form);
      // ... (le reste de votre méthode submitForm)
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
