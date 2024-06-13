<template>
  <main class="content">
    <div class="welcome-message" :class="{ 'show': showWelcomeMessage }">
      Bienvenue !
    </div>
    <div class="welcome-button" :class="{ 'show': showWelcomeMessage }">
      <a href="https://marechalrf.catalogueformpro.com" class="external-link-button">Visitez notre catalogue</a>
    </div>
    <div class="welcome-button" :class="{ 'show': showWelcomeMessage }">
      <router-link to="/training-request" class="internal-link-button">Demande de formation personnalisée</router-link>
    </div>
  </main>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';

export default defineComponent({
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Home',
  setup() {
    const showWelcomeMessage = ref(false);

    onMounted(() => {
      setTimeout(() => {
        showWelcomeMessage.value = true;
      }, 1000);
    });

    return {
      showWelcomeMessage
    };
  }
});
</script>

<style scoped>
html, body {
  height: 100%;
  margin: 0;
}

.content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
  padding: 2rem;
  background: url('/public/background.jpg') no-repeat center center fixed;
  background-size: cover;
}

.welcome-message {
  position: relative;
  font-size: 2rem;
  transition: opacity 1s ease, transform 1s ease;
  opacity: 0;
  transform: translateY(20px);
}
.welcome-message.show {
  opacity: 1;
  transform: translateY(0);
}

.welcome-button {
  position: relative;
  margin-top: 2rem;
  transition: opacity 1.5s ease, transform 1.5s ease;
  opacity: 0;
  transform: translateY(20px);
}
.welcome-button.show {
  opacity: 1;
  transform: translateY(0);
}

.external-link-button, .internal-link-button {
  display: block;
  padding: 10px 20px;
  background-color: rgba(0, 0, 0, 0.6);
  color: white;
  text-decoration: none;
  border-radius: 5px;
  font-size: 1rem;
}

.internal-link-button {
  text-align: center;
}
</style>
