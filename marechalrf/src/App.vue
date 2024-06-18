<template>
  <div id="app" class="app">
    <NavBar />
    <RouterView></RouterView>
    <CookieConsent />
    <Footer />
  </div>
</template>

<script lang="ts">
import NavBar from './components/NavBar.vue';
import Footer from './components/Footer.vue';
import CookieConsent from './views/CookieConsent.vue';

export default {
  name: 'App',
  components: {
    NavBar,
    // eslint-disable-next-line vue/no-reserved-component-names
    Footer,
    CookieConsent
  }
};
</script>

<style scoped>
.app {
  position: relative;
  min-height: 100vh;
  background-image: url('/background.jpg');
  background-attachment: fixed;
  background-size: cover;
  background-position: center;
  color: black;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
