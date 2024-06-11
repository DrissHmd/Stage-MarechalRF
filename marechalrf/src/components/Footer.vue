<template>
    <footer class="footer" :class="{ 'show': isMobile }">
      <div class="footer-content">
        <div class="footer-section">
          <h3>Coordonnées</h3>
          <p>Adresse: 18 rue du Corbet <br> 01960 ST ANDRE SUR VIEUX JONC</p>
          <p>Téléphone: 04 28 36 07 93</p>
          <p>Email: contact@assurancemarechal.fr</p>
        </div>
        <div class="footer-section">
          <h3>Conditions</h3>
          <router-link to="/terms">Conditions d'utilisation</router-link>
        </div>
        <div class="footer-section">
          <h3>Mentions Légales</h3>
          <router-link to="/privacy">Politique de confidentialité</router-link>
          <router-link to="/legal">Mentions légales</router-link>
        </div>
      </div>
    </footer>
  </template>
  
  <script>
  export default {
    name: 'Footer',
    data() {
      return {
        isMobile: false
      };
    },
    mounted() {
      this.checkIfMobile();
      window.addEventListener('resize', this.checkIfMobile);
    },
    beforeDestroy() {
      window.removeEventListener('resize', this.checkIfMobile);
    },
    methods: {
      checkIfMobile() {
        this.isMobile = window.innerWidth <= 768;
      }
    }
  };
  </script>
  
  <style scoped>
  .footer {
    background: rgba(255, 255, 255, 0.5); 
    box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
    padding: 1rem 2rem;
    position: relative;
    max-height: 200px; /* Hauteur maximale du footer en mode téléphone */
    overflow-y: auto; /* Activation du défilement vertical si le contenu dépasse */
  }
  
  .footer-content {
    display: flex;
    flex-direction: column;
  }
  .footer-section {
    margin-bottom: 1rem;
  }
  .footer-section h3 {
    font-size: 1.2rem;
    margin-bottom: 0.5rem;
  }
  .footer-section p,
  .footer-section a {
    font-size: 1rem;
    color: black;
    text-decoration: none;
    margin: 0.2rem 0;
    transition: color 0.3s;
  }
  .footer-section a:hover {
    color: #555;
  }
  
  @media (min-width: 769px) {
    .footer {
      max-height: none; /* Supprimer la hauteur maximale en mode ordinateur */
      overflow-y: visible; /* Désactiver le défilement vertical en mode ordinateur */
    }
    .footer-content{
        flex-direction: row;
        justify-content: space-evenly;
    }
  }
  </style>
  