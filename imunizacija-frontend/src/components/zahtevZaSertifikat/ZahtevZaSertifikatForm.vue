<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="8" md="12">
        <v-form ref="form" v-model="valid">
          <p class="text-justify">
            У складу са одредбом Републике Србије о издавању дигиталног зеленог
            сертификата као потврде о извршеној вакцинацији против COVID-19,
            резултатима тестирања на заразну болест SARS-CoV-2 или опоравку од
            болести COVID-19, подносим захтев за издавање дигиталног зеленог
            сертификата.
          </p>
          <p class="text-justify">Подносилац захтева:</p>
          <v-row>
            <v-text-field
              v-model="formData.ime"
              label="Име*"
              disabled
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.prezime"
              label="Презиме*"
              disabled
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.datumRodjenja"
              label="Датум рођења*"
              single-line
            >
              <template v-slot:append-outer>
                <date-picker v-model="formData.datumRodjenja" />
              </template>
            </v-text-field>
          </v-row>
          <v-row>
            <v-select
              v-model="formData.pol"
              :items="polItems"
              item-text="text"
              item-value="value"
              label="Пол*"
            ></v-select>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.jmbg"
              label="Јединствени матични број грађанина"
              :counter="13"
              :rules="[validJMBGOrBrojPasosa, jmbgRules]"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.brojPasosa"
              label="Број пасоша"
              :rules="[validJMBGOrBrojPasosa]"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-select
              v-model="formData.mesto"
              label="Место*"
              :rules="mestoRules"
              :items="mestoItems"
            ></v-select>
          </v-row>
          <v-row class="rich-text-component">
            <div>Разлог за подношење захтева*</div>
            <quill-editor
              class="editor"
              ref="myTextEditor"
              v-model="formData.razlogZaPodnosenjeZahteva"
              :options="editorOption"
              @change="onEditorChange"
            />
          </v-row>
          <v-row>
            <v-flex class="text-center">
              <v-btn :disabled="!valid" color="success" @click="submit">
                Поднеси документ
              </v-btn>
            </v-flex>
          </v-row>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ZahtevZaSertifikatService from "@/services/ZahtevZaSertifikatService";
import hljs from "highlight.js";
import debounce from "lodash/debounce";
import DatePicker from "../shared/DatePicker.vue";
import jwt_decode from "jwt-decode";
import { quillEditor } from "vue-quill-editor";
// import theme style
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";

export default {
  components: { DatePicker, quillEditor },
  data: () => ({
    polItems: [
      { value: "MUSKI", text: "Мушки" },
      { value: "ZENSKI", text: "Женски" },
    ],
    mestoItems: [
      "Ада",
      "Александровац",
      "Алексинац",
      "Алибунар",
      "Апатин",
      "Аранђеловац",
      "Ариље",
      "Бабушница",
      "Бајина Башта",
      "Баточина",
      "Бач",
      "Бачка Паланка",
      "Бачка Топола",
      "Бачки Петровац",
      "Бела Паланка",
      "Бела Црква",
      "Беочин",
      "Бечеј",
      "Блаце",
      "Богатић",
      "Бојник",
      "Бољевац",
      "Босилеград",
      "Брус",
      "Бујановац",
      "Варварин",
      "Велика Плана",
      "Велико Градиште",
      "Витина",
      "Владимирци",
      "Владичин Хан",
      "Власотинце",
      "Врбас",
      "Врњачка Бања",
      "Вучитрн",
      "Гаџин Хан",
      "Глоговац",
      "Гњилане",
      "Голубац",
      "Гора (седиште Драгаш)",
      "Горњи Милановац",
      "Деспотовац",
      "Дечани",
      "Димитровград",
      "Дољевац",
      "Ђаковица",
      "Жабаљ",
      "Жабари",
      "Жагубица",
      "Житиште",
      "Житорађа",
      "Звечан",
      "Зубин Поток",
      "Ивањица",
      "Инђија",
      "Ириг",
      "Исток",
      "Кањижа",
      "Качаник",
      "Кладово",
      "Клина",
      "Кнић",
      "Књажевац",
      "Ковачица",
      "Ковин",
      "Косјерић",
      "Косово Поље",
      "Косовска Каменица",
      "Косовска Митровица",
      "Коцељева",
      "Крупањ",
      "Кула",
      "Куршумлија",
      "Кучево",
      "Лајковац",
      "Лапово",
      "Лебане",
      "Лепосавић",
      "Липљан",
      "Лучани",
      "Љиг",
      "Љубовија",
      "Мајданпек",
      "Мали Зворник",
      "Мали Иђош",
      "Мало Црниће",
      "Медвеђа",
      "Мерошина",
      "Мионица",
      "Неготин",
      "Нова Варош",
      "Нова Црња",
      "Нови Бечеј",
      "Нови Кнежевац",
      "Ново Брдо",
      "Обилић",
      "Опово",
      "Ораховац",
      "Осечина",
      "Оџаци",
      "Параћин",
      "Петровац на Млави",
      "Пећ",
      "Пећинци",
      "Пландиште",
      "Подујево",
      "Пожега",
      "Прешево",
      "Прибој",
      "Призрен",
      "Пријепоље",
      "Ражањ",
      "Рача",
      "Рашка",
      "Рековац",
      "Рума",
      "Свилајнац",
      "Сврљиг",
      "Сента",
      "Сечањ",
      "Сјеница",
      "Смедеревска Паланка",
      "Сокобања",
      "Србица",
      "Србобран",
      "Сремски Карловци",
      "Стара Пазова",
      "Сува Река",
      "Сурдулица",
      "Темерин",
      "Тител",
      "Топола",
      "Трговиште",
      "Трстеник",
      "Тутин",
      "Ћићевац",
      "Ћуприја",
      "Уб",
      "Урошевац",
      "Црна Трава",
      "Чајетина",
      "Чока",
      "Шид",
      "Штимље",
      "Штрпце",
    ],
    formData: {
      mesto: "",
      ime: "",
      prezime: "",
      datumRodjenja: "",
      jmbg: "",
      pol: "",
      brojPasosa: "",
      razlogZaPodnosenjeZahteva: "",
      email: "",
    },
    editorOption: {
      placeholder: "Унесите разлог за подношење захтева овде...",
      modules: {
        toolbar: [
          ["bold", "italic", "underline", "strike"],
          ["blockquote", "code-block"],
          [{ header: 1 }, { header: 2 }],
          [{ list: "ordered" }, { list: "bullet" }],
          [{ script: "sub" }, { script: "super" }],
          [{ indent: "-1" }, { indent: "+1" }],
          [{ direction: "rtl" }],
          [{ size: ["small", false, "large", "huge"] }],
          [{ header: [1, 2, 3, 4, 5, 6, false] }],
          [{ font: [] }],
          [{ color: [] }, { background: [] }],
          [{ align: [] }],
          ["clean"],
        ],
        syntax: {
          highlight: (text) => hljs.highlightAuto(text).value,
        },
      },
    },
    valid: false,
    jmbgRules: (v) =>
      (v && /^\d{13}$/.test(v)) ||
      v.length === 0 ||
      "ЈМБГ мора имати тачно 13 цифара",
    mestoRules: [(v) => (!!v && v && v.trim() !== "") || "Место је обавезно"],
  }),
  mounted() {
    let jwt = localStorage.getItem("jwt");
    let decoded = jwt_decode(jwt);

    this.formData.ime = decoded.name;
    this.formData.prezime = decoded.surname;
    this.formData.email = decoded.sub;
    this.formData.pol = this.polItems[0].value;
    this.formData.mesto = this.mestoItems[0];
  },
  methods: {
    submit() {
      let currentDate = new Date().toJSON().slice(0, 10);
      let zahtevZaSertifikatJSON = {
        zahtev_za_sertifikat: {
          _attributes: {
            xmlns: "https://www.vakcinacija.rs/zahtev_za_sertifikat",
            "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
            "xmlns:za": "https://www.vakcinacija.rs/zajednicko",
          },
          mesto: this.formData.mesto,
          datum: currentDate,
          podnosilac_zahteva: {
            licni_podaci: {
              "za:ime": this.formData.ime,
              "za:prezime": this.formData.prezime,
              "za:jmbg": this.formData.jmbg,
              "za:pol": this.formData.pol,
            },
            datum_rodjenja: this.formData.datumRodjenja,
            broj_pasosa: this.formData.brojPasosa,
            email: this.formData.email,
          },
          razlog_za_podnosenje_zahteva: this.formData.razlogZaPodnosenjeZahteva,
        },
      };
      let response = ZahtevZaSertifikatService.postZahtevZaSertifikat(
        zahtevZaSertifikatJSON
      );
    },
    onEditorChange: debounce(function (value) {
      this.formData.razlogZaPodnosenjeZahteva = value.html;
    }, 466),
    validJMBGOrBrojPasosa() {
      if (
        this.formData.jmbg.trim() === "" &&
        this.formData.brojPasosa.trim() === ""
      ) {
        return "Морате унети или ЈМБГ или број пасоша";
      }
      return true;
    },
  },
  computed: {
    editor() {
      return this.$refs.myTextEditor.quill;
    },
    contentCode() {
      return hljs.highlightAuto(this.formData.razlogZaPodnosenjeZahteva).value;
    },
  },
};
</script>

<style lang="scss" scoped>
.rich-text-component {
  display: flex;
  flex-direction: column;

  .editor {
    height: 10rem;
    margin-bottom: 5rem;
  }

  .output {
    width: 100%;
    height: 20rem;
    margin: 0;
    border: 1px solid #ccc;
    overflow-y: auto;
    resize: vertical;

    &.code {
      padding: 1rem;
      height: 16rem;
    }

    &.ql-snow {
      border-top: none;
      height: 24rem;
    }
  }
}
</style>
