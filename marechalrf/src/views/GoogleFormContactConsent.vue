<template>
    <div>
      <div v-if="!consentGiven" class="placeholder">
        <p>Pour afficher le formulaire, veuillez accepter les cookies.</p>
        <button @click="acceptCookies">Accepter les cookies</button>
      </div>
      <div v-else>
        <iframe src="https://docs.google.com/forms/d/e/1FAIpQLSemTDgjSGXwg75YfA7N4rYWyTYBw7w_STNni-4HNVZvzG7Z6A/viewform?embedded=true" width="100%" height="500"></iframe>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'GoogleForm',
    data() {
      return {
        consentGiven: false
      };
    },
    mounted() {
      this.checkConsent();
    },
    methods: {
      checkConsent() {
        const consent = localStorage.getItem('cookie_consent');
        if (consent === 'accepted') {
          this.consentGiven = true;
        }
      },
      acceptCookies() {
        localStorage.setItem('cookie_consent', 'accepted');
        this.consentGiven = true;
        location.reload();
      }
    }
  };
  </script>
  
  <style scoped>
  .placeholder {
    text-align: center;
    padding: 20px;
    background-color: #f0f0f0;
  }
  </style>
  