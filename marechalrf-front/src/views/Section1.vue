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
    <div>
      <input type="checkbox" v-model="localForm.consent" id="consent" />
      <label for="consent">Mentions légales *</label>
      En cochant la case ci-dessus, j'accepte que les informations et documents que je transmets à MARECHAL Recrutement et Formation soient exploités dans le cadre d'études tarifaires et la demande de devis d'assurance, ainsi que la relation commerciale qui peut en découler, conformément au Règlement Général de Protection des Données
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
      localForm: { ...this.form } // Ajout de la propriété 'consent'
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
input[type="text"], input[type="email"] {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}
input[type="checkbox"] {
  margin-right: 10px;
}
</style>
