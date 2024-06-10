<template>
  <div id="app" class="app">
    <nav class="navbar">
      <ul class="navbar-menu">
        <li class="navbar-item">
          <router-link to="/">
            <img src="/home-logo.png" alt="Home Logo" class="nav-logo">
            Accueil
          </router-link>
        </li>
        <li class="navbar-item">
          <a href="#" @click.prevent>
            <img src="/about-logo.png" alt="About Logo" class="nav-logo">
            À propos
          </a>
        </li>
        <li class="navbar-item">
          <a href="#" @click.prevent>
            <img src="/services-logo.png" alt="Services Logo" class="nav-logo">
            Services
          </a>
        </li>
      </ul>

      <div class="navbar-item">
          <router-link to="/">
            <img src="/company-logo.png" alt="Company Logo" class="company-logo">
            <span class="company-name">MARECHAL Recrutement et Formation</span>
          </router-link>
        </div>

        
      <ul class="navbar-menu">
        <li class="navbar-item">
          <a href="#" @click.prevent>
            <img src="/contact-logo.png" alt="Contact Logo" class="nav-logo">
            Contact
          </a>
        </li>
        <li class="navbar-item">
          <a href="#" @click.prevent>
            <img src="/account-logo.png" alt="Account Logo" class="nav-logo">
            Compte
          </a>
        </li>
      </ul>
    </nav>

    <main class="content">
      <div class="welcome-message" :class="{ 'show': showWelcomeMessage }">
        Bienvenue !
      </div>
      <div v-if="showButton" class="welcome-button" :class="{ 'show': showWelcomeMessage }">
        <a href="https://example.com" class="external-link-button">Visitez notre site</a>
      </div>
    </main>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';

export default defineComponent({
  name: 'App',
  setup() {
    const showWelcomeMessage = ref(false);
    const showButton = ref(false);

    onMounted(() => {
      setTimeout(() => {
        showWelcomeMessage.value = true;
      }, 1000);

      setTimeout(() => {
        showButton.value = true;
      }, 2000);
    });

    return {
      showWelcomeMessage,
      showButton,
    };
  }
});
</script>

<style scoped>
.app {
  position: relative;
  min-height: 100vh;
  background-image: url('/background.jpg');
  background-size: cover;
  background-position: center;
  color: black;
  text-align: center;
}
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background: rgba(255, 255, 255, 0.5); 
}
.navbar-menu {
  list-style: none;
  display: flex;
  margin: 0;
  padding: 0;
}
.navbar-item {
  display: flex;
  align-items: center;
  margin-left: 1.5rem;
}
.navbar-item a, .navbar-item router-link {
  display: flex;
  align-items: center;
  color: black;
  text-decoration: none;
  font-size: 1rem;
  transition: color 0.3s;
  cursor: not-allowed;
}
.navbar-item a img, .navbar-item router-link img {
  margin-right: 0.5rem;
  width: 30px;
  height: auto;
}
.navbar-brand {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.company-name {
  font-size: 1.2rem;
  font-weight: bold;
  margin-left: 0.5rem;
}

.welcome-message {
  position: absolute;
  bottom: -50px; /* Position initiale en dehors de la vue */
  left: 50%;
  transform: translateX(-50%);
  font-size: 2rem; /* Ajustez la taille selon vos besoins */
  transition: bottom 1s ease; /* Transition vers le bas avec une durée de 1 seconde */
}

.show {
  bottom: 75%; /* Position finale à afficher */
}

.content {
  padding: 2rem;
}

.welcome-button {
  position: absolute;
  bottom: 10%; /* Position initiale ajustée */
  left: 50%;
  transform: translateX(-50%);
  transition: bottom 1s ease; /* Transition vers le bas avec une durée de 1 seconde */
}

.welcome-button.show {
  bottom: 60%; /* Position finale ajustée */
}

.external-link-button {
  display: block;
  padding: 10px 20px;
  background-color: rgba(0, 0, 0, 0.6); /* Couleur de fond du bouton */
  color: white;
  text-decoration: none;
  border-radius: 5px;
  font-size: 1rem;
  transition: bottom 1s ease; /* Transition vers le bas avec une durée de 1 seconde */
}
</style>
