<template>
  <div class="training-request-page">
    <div class="background-static"></div>
    <div class="training-request-container">
      <GoogleForm />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import GoogleForm from './GoogleFormFormationConsent.vue';

export default defineComponent({
  name: "TrainingRequest",
  components: {
    GoogleForm
  }
});
</script>

<style scoped>
.training-request-page {
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

.training-request-container {
  background: rgba(255, 255, 255, 0.9);
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  width: 100%;
  z-index: 1;
}
</style>
