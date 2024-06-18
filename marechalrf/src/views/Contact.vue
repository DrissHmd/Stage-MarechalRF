<template>
  <div class="contact-page">
    <div class="background-static"></div>
    <div class="contact-container">
      <GoogleFormConsent 
        formUrl="https://docs.google.com/forms/d/e/1FAIpQLSemTDgjSGXwg75YfA7N4rYWyTYBw7w_STNni-4HNVZvzG7Z6A/viewform?embedded=true"
        height="300"
      />
      <GoogleMapConsent />
    </div>
  </div>
</template>

<script lang="ts">
import GoogleFormConsent from './GoogleFormContactConsent.vue';
import GoogleMapConsent from './GoogleMapConsent.vue';

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Contact",
  components: {
    GoogleFormConsent,
    GoogleMapConsent
  }
};
</script>

<style scoped>
.contact-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 2rem;
  position: relative;
  overflow: hidden;
}

.background-static {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('/public/background.jpg');
  background-attachment: fixed;
  background-size: cover;
  background-position: center;
  z-index: -1;
}

.contact-container {
  background: rgba(255, 255, 255, 0.9);
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  width: 100%;
  z-index: 1;
}

.map-container {
  margin-top: 2rem;
}

.map-container iframe {
  width: 100%;
  border: 0;
  border-radius: 8px;
  height: 300px;
}
</style>
