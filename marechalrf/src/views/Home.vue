<template>
    <main class="content">
      <div class="welcome-message" :class="{ 'show': showWelcomeMessage }">
        Bienvenue !
      </div>
      <div class="welcome-button" :class="{ 'show': showWelcomeMessage }">
        <a href="https://marechalrf.catalogueformpro.com" class="external-link-button">Visitez notre catalogue</a>
      </div>
    </main>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';

export default defineComponent({
  name: 'App',
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

.content {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 2rem;
}

.welcome-message {
  position: relative;
  font-size: 2rem;
  transition: opacity 1.5s ease, transform 1.5s ease;
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
  transition: opacity 2s ease, transform 2s ease;
  opacity: 0;
  transform: translateY(20px);
}
.welcome-button.show {
  opacity: 1;
  transform: translateY(0);
}

.external-link-button {
  display: block;
  padding: 10px 20px;
  background-color: rgba(0, 0, 0, 0.6);
  color: white;
  text-decoration: none;
  border-radius: 5px;
  font-size: 1rem;
}
</style>
