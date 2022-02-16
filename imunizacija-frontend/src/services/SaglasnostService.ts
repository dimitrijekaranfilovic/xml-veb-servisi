import axios from "axios";
import { json2xml } from "xml-js";
import jwt_decode from "jwt-decode";
import { BaseService } from "./BaseService";

class SaglasnostService extends BaseService {
  async submitSaglasnost(saglasnost: any): Promise<any> {
    let response = axios.post(
      this.basePath + "saglasnost",
      this.toXML(saglasnost),
      this.getXMLConfig()
    );
    return response;
  }

  async getAllForUser(): Promise<any> {
    let jwt = localStorage.getItem("jwt");
    let decoded: any = jwt_decode(jwt as string);

    let response = axios.get(
      this.basePath + "saglasnost/all/" + decoded.sub,
      this.getXMLConfig()
    );

    return response;
  }

  async getXHTMLRepresentation(id: string): Promise<any> {
    let response = axios.get(
      this.basePath + "saglasnost/" + id,
      this.getXMLConfig()
    );

    return response;
  }

  downloadXHTML(id: string): void {
    axios
      .get(this.basePath + "saglasnost/" + id, {
        responseType: "blob",
      })
      .then((response) => {
        this.initialteDownload(response, "saglasnost", "html");
      })
      .catch(console.error);
  }

  downloadPDF(id: string): void {
    axios
      .get(this.basePath + "saglasnost/pdf/" + id, {
        responseType: "blob",
      })
      .then((response) => {
        this.initialteDownload(response, "saglasnost", "pdf");
      })
      .catch(console.error);
  }
}

export default new SaglasnostService();
