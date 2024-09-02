<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <h2>Questions Supplémentaires</h2>
    <div>
      <label for="besoinsFormation">Quels sont vos besoins en matière de formation ? *</label>
      <textarea v-model="localForm.besoinsFormation" id="besoinsFormation" required></textarea>
    </div>
    <div>
      <label for="sujetsAborder">Quels sont les sujets à aborder ? *</label>
      <textarea v-model="localForm.sujetsAborder" id="sujetsAborder" required></textarea>
    </div>
    <div>
      <label for="objectifs">Quels sont les objectifs attendus pour cette formation ? *</label>
      <textarea v-model="localForm.objectifs" id="objectifs" required></textarea>
    </div>
    <div>
      <label for="exigencesSpecifiques">Avez-vous des exigences spécifiques ? *</label>
      <textarea v-model="localForm.exigencesSpecifiques" id="exigencesSpecifiques" required></textarea>
    </div>
    <div>
      <label for="typeFormation">Quels types de formation préfériez-vous ? *</label>
      <select v-model="localForm.typeFormation" id="typeFormation" required>
        <option value="100% présentielle">100% présentielle</option>
        <option value="Mixte (mix-learning, blended learning)">Mixte (mix-learning, blended learning)</option>
        <option value="À distance">À distance</option>
        <option value="En situation de travail">En situation de travail</option>
      </select>
    </div>
    <div>
      <label for="effectif">Effectif à former *</label>
      <input type="number" v-model="localForm.effectif" id="effectif" required />
    </div>
    <div>
      <label for="duree">Durée souhaitée (en heures) *</label>
      <input type="number" v-model="localForm.duree" id="duree" required />
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
textarea, select, input[type="number"] {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>