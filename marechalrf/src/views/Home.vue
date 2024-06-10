<template>
    <div class="home">
      <h1>Home</h1>
      <p>Home page</p>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent } from 'vue';
  
  export default defineComponent({
    name: 'Home',
  });
  </script>
  
  <style scoped>
  .home {
    padding: 2rem;
    text-align: center;
  }
  </style>
  