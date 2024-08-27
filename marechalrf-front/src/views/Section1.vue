<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <h2>Informations Générales</h2>
    <div>
      <label for="nom">Nom *</label>
      <input type="text" v-model="localForm.nom" id="nom" required />
    </div>
    <div>
      <label for="prenom">Prénom *</label>
      <input type="text" v-model="localForm.prenom" id="prenom" required />
    </div>
    <div>
      <label for="entreprise">Entreprise *</label>
      <input type="text" v-model="localForm.entreprise" id="entreprise" required />
    </div>
    <div>
      <label for="fonction">Fonction *</label>
      <input type="text" v-model="localForm.fonction" id="fonction" required />
    </div>
    <div>
      <label for="email">Adresse mail *</label>
      <input type="email" v-model="localForm.email" id="email" required />
    </div>
    <div>
      <label for="phone">Numéro de téléphone</label>
      <input type="text" v-model="localForm.phone" id="phone" />
    </div>
  </div>
</template>

<script>
export default {
  props: {
    form: Object
  },
  data() {
    return {
      localForm: { ...this.form } // Copie locale du formulaire
    };
  },
  watch: {
    localForm: {
      handler(newValue) {
        this.$emit('update-form', newValue); // Émettre la mise à jour vers le parent
      },
      deep: true
    }
  }
};
</script>

<style scoped>
/* Style pour les champs de formulaire */
div {
  margin-bottom: 15px;
}
label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}
input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>