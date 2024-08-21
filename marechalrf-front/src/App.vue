<template>
  <div id="app" class="app">
    <NavBar v-if="showHeaderAndFooter" />
    <RouterView></RouterView>
    <CookieConsent v-if="showHeaderAndFooter" />
    <Footer v-if="showHeaderAndFooter" />
  </div>
</template>

<script lang="ts">
import { defineComponent, computed } from 'vue';
import { useRoute } from 'vue-router';
import NavBar from './components/NavBar.vue';
import Footer from './components/Footer.vue';
import CookieConsent from './views/CookieConsent.vue';

export default defineComponent({
  name: 'App',
  components: {
    NavBar,
    // eslint-disable-next-line vue/no-reserved-component-names
    Footer,
    CookieConsent
  },
  setup() {
    const route = useRoute();
    const showHeaderAndFooter = computed(() => {
      return route.path !== '/login' && route.path !== '/register' && route.path !== '/user-edit/:id';
    });
    return {
      showHeaderAndFooter
    };
  }
});
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
