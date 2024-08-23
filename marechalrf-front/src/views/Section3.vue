<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <h2>Handicap</h2>
    <div>
      <label for="handicap">Y a-t-il des apprenants atteints d'un handicap ? *</label>
      <select v-model="localForm.handicap" id="handicap" required>
        <option value="Oui">Oui</option>
        <option value="Non">Non</option>
      </select>
    </div>
    <div v-if="localForm.handicap === 'Oui'">
      <label for="typeHandicap">Type d'handicap *</label>
      <select v-model="localForm.typeHandicap" id="typeHandicap" required>
        <option value="Déficience physique">Déficience physique</option>
        <option value="Déficience auditive">Déficience auditive</option>
        <option value="Déficience visuelle">Déficience visuelle</option>
        <option value="Déficience cognitive">Déficience cognitive</option>
        <option value="Maladie invalidante">Maladie invalidante</option>
        <option value="Déficience psychique">Déficience psychique</option>
        <option value="Déficience intellectuelle">Déficience intellectuelle</option>
      </select>
    </div>
    <div>
      <label for="amenagement">Aménagement nécessaire</label>
      <textarea v-model="localForm.amenagement" id="amenagement"></textarea>
    </div>
    <div>
      <label for="commentaires">Commentaires</label>
      <textarea v-model="localForm.commentaires" id="commentaires"></textarea>
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
      localForm: { ...this.form }  // Créer une copie locale des données du formulaire
    };
  },
  watch: {
    localForm: {
      handler(newValue) {
        this.$emit('update-form', newValue);  // Émettre les changements au parent
      },
      deep: true  // Observer les changements profonds dans l'objet
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
textarea, select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>
