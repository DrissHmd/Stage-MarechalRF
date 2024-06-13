<template>
    <div class="about">
      <h1>About</h1>
      <p>About page</p>
    </div>
</template>
  
  <script lang="ts">
  import { defineComponent } from 'vue';
  
  export default defineComponent({
    // eslint-disable-next-line vue/multi-word-component-names
    name: 'About',
  });
  </script>
  
  <style scoped>
  .about {
    padding: 2rem;
    text-align: center;
    min-height: 80vh;
  }
  </style>
  