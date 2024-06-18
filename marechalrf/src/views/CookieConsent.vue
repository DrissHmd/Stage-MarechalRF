<template>
    <div v-if="!consentGiven" id="cookie-consent">
      <div class="cookie-message">
        <p>Nous utilisons des cookies pour améliorer votre expérience sur notre site. Veuillez accepter ou refuser l'utilisation de cookies ci-dessous :</p>
      </div>
      <div class="cookie-buttons">
        <button @click="acceptCookies">Accepter</button>
        <button @click="rejectCookies">Refuser</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'CookieConsent',
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
          this.loadGoogleAnalytics();
        }
      },
      acceptCookies() {
        localStorage.setItem('cookie_consent', 'accepted');
        this.consentGiven = true;
        this.loadGoogleAnalytics();
        location.reload();
      },
      rejectCookies() {
        localStorage.setItem('cookie_consent', 'rejected');
        this.consentGiven = true;
      },
      loadGoogleAnalytics() {
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');
        ga('create', 'UA-XXXXX-Y', 'auto');
        ga('send', 'pageview');
      }
    }
  };
  </script>
  
  <style scoped>
  #cookie-consent {
    background-color: #f0f0f0ce;
    padding: 10px 20px;
    box-shadow: 0 -2px 6px rgba(0, 0, 0, 0.1);
    margin-top: 20px;
  }
  
  .cookie-message {
    margin-bottom: 10px;
  }
  
  .cookie-buttons button {
    margin-right: 10px;
  }
  </style>
  