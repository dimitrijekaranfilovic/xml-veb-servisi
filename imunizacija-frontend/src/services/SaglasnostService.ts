import axios from "axios";
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
    let response = axios.get(
      this.basePath + "saglasnost/all/" + this.getLocalStorageJwt().sub,
      this.getXMLConfig()
    );

    return response;
  }
}

export default new SaglasnostService();
