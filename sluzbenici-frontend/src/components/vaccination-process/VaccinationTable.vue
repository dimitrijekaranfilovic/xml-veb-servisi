<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Датум подношења</th>
          <th class="text-left">Име пацијента</th>
          <th class="text-left">Презиме пацијента</th>
          <th class="text-left">Адреса електронске поште</th>
          <th class="text-left">Детаљи</th>
        </tr>
      </thead>
      <tbody v-if="consents">
        <tr v-for="(item, i) in consents" :key="i">
          <td>{{ item.datum | deRdf | moment("DD.MM.YYYY.") }}</td>
          <td>{{ item.pacijent.licneInformacije.punoIme.ime | deRdf }}</td>
          <td>{{ item.pacijent.licneInformacije.punoIme.prezime | deRdf }}</td>
          <td>{{ item.pacijent.licneInformacije.kontakt.email | deRdf }}</td>
          <td>
            <v-btn
              text
              color="primary"
              plain
              :to="{ name: 'SingleVaccinationView', params: { id: item.id } }"
              >Прикажи детаље</v-btn
            >
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
export default {
  name: "VaccinationTable",
  props: ["consents"],
};
</script>
