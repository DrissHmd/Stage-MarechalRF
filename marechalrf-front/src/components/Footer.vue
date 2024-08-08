<template>
  <footer class="footer" :class="{ 'show': isMobile }">
    <div class="footer-content">
      <div class="footer-section">
        <h3>Coordonnées</h3>
        <p>Adresse: 18 rue du Corbet <br> 01960 ST ANDRE SUR VIEUX JONC</p>
        <p>Téléphone: 04 37 62 64 11</p>
        <p>Email: marechalretf@gmail.com</p>
        <div class="map-container">
          <GoogleMapConsent />
        </div>
      </div>
      <div class="footer-section">
        <h3>Conditions</h3>
        <div class="links">
          <a href="/liste-cookies.pdf" target="_blank">Conditions d'utilisation</a>
          <a href="/politique_confidentialite.pdf" target="_blank">Politique de confidentialité</a>
          <a href="/mentions-legales.pdf" target="_blank">Mentions légales</a>
        </div><br>
        <h3>Navigation</h3>
        <div class="footer-navigation">
          <router-link to="/">Accueil</router-link>
          <router-link to="/about">À propos</router-link>
          <router-link to="/contact">Contact</router-link>
        </div>
      </div>
      <div class="footer-section">
        <h3>Nos certifications</h3>
        <div>
          certifications de notre organisme de formation<br>
          <a href="https://cdn.filestackcontent.com/9iH5NDVET3i1hAgOEU7Q" target="_blank"><img class="qualiopi" src="/public/qualiopi-logo.png" alt="Qualiopi Logo"></a>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
import GoogleMapConsent from '../views/GoogleMapConsent.vue';

export default {
  // eslint-disable-next-line vue/multi-word-component-names, vue/no-reserved-component-names
  name: 'Footer',
  components: {
    GoogleMapConsent
  },
  data() {
    return {
      isMobile: false
    };
  },
  mounted() {
    this.checkIfMobile();
    window.addEventListener('resize', this.checkIfMobile);
  },
  beforeUnmount() {
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
  background: rgba(229, 230, 255, 0.8); 
  box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  padding: 1rem 2rem;
  position: relative;
  max-height: 200px; 
  overflow-y: auto; 
}

.footer-content {
  display: flex;
  flex-direction: column;
}

.footer-section {
  margin-bottom: 1rem;
  width: 100%;
}

.footer-navigation {
  list-style: none;
  display: flex;
  flex-direction: column;
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

.links {
  display: flex;
  flex-direction: column;
}

.qualiopi {
  width: 100%;
  padding-top: 3%;
}

@media (min-width: 769px) {
  .footer {
    max-height: none; 
    overflow-y: visible; 
  }
  
  .footer-content {
    flex-direction: row;
    justify-content: space-evenly;
  }

  .qualiopi {
    width: 80%;
  }
}
</style>
