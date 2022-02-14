import axios from "axios";
import { json2xml } from "xml-js";
import jwt_decode from "jwt-decode";

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

  async getAllForUser(): Promise<any> {
    let config = {
      headers: { "Content-Type": "application/xml" },
    };

    let jwt = localStorage.getItem("jwt");
    let decoded: any = jwt_decode(jwt as string);

    let response = await axios.get(
      "http://localhost:8081/saglasnost/all/" + decoded.sub,
      config
    );

    return response;
  }
}

export default new SaglasnostService();
