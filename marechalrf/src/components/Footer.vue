<template>
  <footer class="footer" :class="{ 'show': isMobile }">
    <div class="footer-content">
      <div class="footer-section">
        <h3>Coordonnées</h3>
        <p>Adresse: 18 rue du Corbet <br> 01960 ST ANDRE SUR VIEUX JONC</p>
        <p>Téléphone: 04 28 36 07 93</p>
        <p>Email: contact@assurancemarechal.fr</p>
        <div class="map-container">
          <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2763.7344022486236!2d5.144370121714436!3d46.15603471495694!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47f3573b6005885b%3A0x19bcb169926e8b09!2s18%20Le%20Corbet%2C%2001960%20Saint-Andr%C3%A9-sur-Vieux-Jonc!5e0!3m2!1sfr!2sfr!4v1718120743638!5m2!1sfr!2sfr"
            width="50%"
            height="100%"
            style="border:0;"
            loading="lazy"
            referrerpolicy="no-referrer-when-downgrade">
          </iframe>
        </div>
      </div>
      <div class="footer-section">
        <h3>Conditions</h3>
        <div class="links">
          <router-link to="">Conditions d'utilisation</router-link>
          <router-link to="">Politique de confidentialité</router-link>
          <router-link to="">Mentions légales</router-link>
        </div><br>
        <h3>Navigation</h3>
        <div class="footer-navigation">
          <router-link to="/">Accueil</router-link>
          <router-link to="/about">À propos</router-link>
          <router-link to="/contact">Contact</router-link>
          <a style="cursor: not-allowed;">Compte <br> (bientôt disponible)</a>
        </div>
      </div>
      <div class="footer-section">
        <h3>Communiquation</h3>
        <div>
        <img src="/public/qualiopi-logo.png" alt="Qualiopi Logo" style="width: 20%;">
        <img src="/public/qualiopi-logo.png" alt="Qualiopi Logo" style="width: 20%;">
        </div>
        <div>
        <img src="/public/qualiopi-logo.png" alt="Qualiopi Logo" style="width: 20%;">
        <img src="/public/qualiopi-logo.png" alt="Qualiopi Logo" style="width: 20%;">
        </div>
        <div>
        <img src="/public/qualiopi-logo.png" alt="Qualiopi Logo" style="width: 20%;">
        <img src="/public/qualiopi-logo.png" alt="Qualiopi Logo" style="width: 20%;">
        </div>
        <div>
        <img src="/public/qualiopi-logo.png" alt="Qualiopi Logo" style="width: 20%;">
        <img src="/public/qualiopi-logo.png" alt="Qualiopi Logo" style="width: 20%;">
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names, vue/no-reserved-component-names
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

.footer-navigation{
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

@media (min-width: 769px) {
  .footer {
    max-height: none; 
    overflow-y: visible; 
  }
  
  .footer-content {
    flex-direction: row;
    justify-content: space-evenly;
  }
}
</style>
