<template>
    <div>
      <div v-if="!consentGiven" class="placeholder">
        <p>Pour afficher le formulaire, veuillez accepter les cookies.</p>
        <button @click="acceptCookies">Accepter les cookies</button>
      </div>
      <div v-else>
        <iframe src="https://docs.google.com/forms/d/e/1FAIpQLSdFOxogeai5NHjl5satKTUoSa25TJlv-HBRxuUXchd33Kx-hQ/viewform?embedded=true" width="100%" height="1000"></iframe>
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
        this.$router.push('/');
        setTimeout(() => {
          location.reload();
        }, 100);
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
  