import axios from "axios";
import { json2xml } from "xml-js";

class SaglasnostService {
  async submitSaglasnost(saglasnost: any): Promise<any> {
    const xmlSaglasnost = json2xml(saglasnost, {
      compact: true,
    });

    let config = {
      headers: { "Content-Type": "application/xml" },
    };

    let response = await axios.post(
      "http://localhost:8081/saglasnost",
      xmlSaglasnost,
      config
    );

    return response;
  }
}

export default new SaglasnostService();
