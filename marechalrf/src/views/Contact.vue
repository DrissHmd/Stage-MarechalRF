<template>
  <div class="contact-page">
    <div class="background-static"></div>
    <div class="contact-container">
      <iframe src="https://docs.google.com/forms/d/e/1FAIpQLSemTDgjSGXwg75YfA7N4rYWyTYBw7w_STNni-4HNVZvzG7Z6A/viewform?embedded=true" 
        width="640" 
        height="1511" 
        frameborder="0" 
        marginheight="0" 
        marginwidth="0">
        Chargement…
      </iframe>

      <div class="map-container">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2763.7344022486236!2d5.144370121714436!3d46.15603471495694!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47f3573b6005885b%3A0x19bcb169926e8b09!2s18%20Le%20Corbet%2C%2001960%20Saint-Andr%C3%A9-sur-Vieux-Jonc!5e0!3m2!1sfr!2sfr!4v1718120743638!5m2!1sfr!2sfr"
          width="600"
          height="450"
          style="border:0;"
          loading="lazy"
          referrerpolicy="no-referrer-when-downgrade">
        </iframe>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue';

export default defineComponent({
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Contact",
  setup() {
    const form = reactive({
      name: '',
      email: '',
      message: ''
    });

    const submitForm = () => {
      // Handle form submission, e.g., send data to a server
      console.log('Form submitted:', form);
      // Clear the form after submission
      form.name = '';
      form.email = '';
      form.message = '';
    };

    return { form, submitForm };
  }
});
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

.contact-form {
  display: flex;
  flex-direction: column;
  margin-bottom: 2rem;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.form-group textarea {
  resize: vertical;
  height: 150px;
}

button {
  background-color: white;
  color: black;
  padding: 0.75rem;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: black;
  color: white;
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
