<template>
    <div>
      <div v-if="!consentGiven" class="placeholder">
        <p>Pour afficher la carte, veuillez accepter les cookies.</p>
        <button @click="acceptCookies">Accepter les cookies</button>
      </div>
      <div v-else>
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2763.7344022486236!2d5.144370121714436!3d46.15603471495694!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47f3573b6005885b%3A0x19bcb169926e8b09!2s18%20Le%20Corbet%2C%2001960%20Saint-Andr%C3%A9-sur-Vieux-Jonc!5e0!3m2!1sfr!2sfr!4v1718120743638!5m2!1sfr!2sfr"
          width="100%"
          height="100%"
          style="border:0;"
          loading="lazy"
          referrerpolicy="no-referrer-when-downgrade">
        </iframe>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'GoogleMapConsent',
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
  