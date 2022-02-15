import { BaseService } from "@/services/BaseService";
import axios from "axios";
import jwt_decode from "jwt-decode";

class ZahtevZaSertifikatService extends BaseService {
  private zahtevPath: string = "zahtev-za-sertifikat";

  async postZahtevZaSertifikat(zahtevZaSertifikat: any): Promise<any> {
    super.sendRequest(axios.post, zahtevZaSertifikat, this.zahtevPath);
  }
}
export default new ZahtevZaSertifikatService();
