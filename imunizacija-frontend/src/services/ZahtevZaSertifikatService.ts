import { BaseService } from "@/services/BaseService";
import axios from "axios";
import jwt_decode from "jwt-decode";

class ZahtevZaSertifikatService extends BaseService {
  private zahtevPath: string = "zahtev-za-sertifikat";

  async postZahtevZaSertifikat(zahtevZaSertifikat: any): Promise<any> {
    return super.sendRequest(axios.post, zahtevZaSertifikat, this.zahtevPath);
  }

  async getAllForUser(): Promise<any> {
    let response = await axios.get(
      this.basePath + "zahtev-za-sertifikat/all/" + this.provideUserEmail(),
      this.getXMLConfig()
    );

    return response;
  }
}
export default new ZahtevZaSertifikatService();
