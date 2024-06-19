<template>
  <div class="about-page">
    <div class="about-container">
      <h2>La SARL MARECHAL Recrutement & Formation</h2>
      <p>
        La SARL MARECHAL Recrutement & Formation, filiale de la SAS Groupe Maréchal a été fondée en juillet 2023 pour vous proposer des solutions de formation et de recrutement.
        Que vous souhaitiez vous former en banque, assurance, finance ou immobilier, nous pouvons vous accompagner. Nous vous proposons nos formations catalogue ou nous adaptons à vos besoins en vous proposant une formation sur mesure ! N’hésitez pas à nous appeler si vous souhaitez que nous vous aidions à bâtir votre formation.
        Une grande partie des formations délivrées interviennent dans le cadre du Plan de développement des compétences et sont éligibles DDA, y compris en sur-mesure.
      </p>
      
      <h3>Vous êtes une entreprise ?</h3>
      <p>
        Nous sommes en mesure de vous accompagner dans l’élaboration de votre plan de formation, de votre registre des formations, et bien sûr dans l’élaboration et la mise en place de formations du Plan de Développement des Compétences adaptées à vos besoins. Nous proposons également des formations de type POE et des bilans de compétences pour vos salariés.
      </p>

      <h3>Vous êtes salarié ?</h3>
      <p>
        Nos bilans de compétences vous permettent de faire le point sur votre carrière et de l’orienter, pour vous sentir en phase dans le monde du travail et vous épanouir.
      </p>

      <h3>Nos points forts</h3>
      <ul>
        <li>Une technicité reconnue et des formateurs pédagogues et expérimentés.</li><br>
        <li>Une capacité d’adaptation aux demandes sur-mesure.</li><br>
        <li>Besoins de renforcer les compétences techniques de vos équipes, notre option formation à quatre mains vous permet de bénéficier de l’intervention d’un technicien de l’assurance et d’un expert métier (BTP, Immobilier, Médical, Sécurité….)</li><br>
        <li>Des formations vivantes favorisant l’échange avec les apprenants.</li><br>
        <li>Une ouverture à l’international et notamment un accompagnement pour les candidats désireux de s’expatrier au Québec.</li><br>
        <li>Un réseau de partenaires implantés en France et à l’International.</li><br>
        <li>Une sensibilité accrue à la question du handicap sous toutes ses formes avec un personnel formé et qualifié pour s’adapter aux besoins de nos clients.</li><br>
      </ul>

      <h3>Qui est notre gérante ?</h3>
      <div class="manager-section">
        <div class="manager-photo">
          <img src="../../public/manager.jpg" alt="Anne MARECHAL DEMARS" />
        </div>
        <div class="manager-description">
          <p>
            Anne MARECHAL DEMARS, gère la SARL MARECHAL Recrutement & Formation.
            Major nationale de la licence professionnelle banque, assurance finance du CNAM Enass, elle a reçu son diplôme des mains du Ministre de l’Enseignement supérieur dans le grand amphithéâtre de la Sorbonne. Elle est également lauréate du Prix de l’AIEFFA 2010. 
          </p>
          <p>
            Depuis, elle exerce comme formatrice en assurance et intermédiaire d’assurance sur le terrain depuis 2010 et a forgé son expérience, notamment en intervenant à l’IFPASS et au CNAM dans différentes matières techniques, juridiques, économiques et commerciales en lien avec les métiers de la banque, assurance et finance ou encore comme jury du BTS Assurance de l’Académie de Lyon. Elle est également trésorière de l’ONG Internationale « AIEFFA » (Association Internationale des Etablissements Francophones de Formation à l’Assurance.
            Convaincue de l’importance de la technicité dans les formations métier mais également soucieuse de rester en prise avec les évolutions « terrain », elle œuvre chaque jour pour vous proposer des formations dynamiques, vivantes, adaptées et permettant une réelle montée en compétences des participants.
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'About'
};
</script>

<style scoped>
.about-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 2rem;
  position: relative;
  overflow: hidden;
}

.about-container {
  background: rgba(255, 255, 255, 0.9);
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  width: 100%;
  z-index: 1;
}

.about-container h2,
.about-container h3 {
  margin-top: 1.5rem;
}

.manager-section {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-top: 2rem;
}

.manager-photo img {
  width: 150px;
  height: auto;
  border-radius: 50%;
  margin-right: 1rem;
}

ul{
  list-style: none;
}

.manager-description {
  flex: 1;
}

@media (max-width: 768px) {
  .manager-section {
    flex-direction: column;
    text-align: center;
  }
  
  .manager-photo {
    margin-bottom: 1rem;
  }
}
</style>
