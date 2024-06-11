<template>
    <div id="app" class="app">
    <NavBar />

      <RouterView></RouterView>
    
    <Footer />
  </div>
</template>

<script lang="ts">
import NavBar from './components/NavBar.vue';
import Footer from './components/Footer.vue';
import Home from './views/Home.vue';

export default {
  name: 'App',
  components: {
    NavBar,
    Footer,
    Home
  }
};
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
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
