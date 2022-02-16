import { BaseService } from "@/services/BaseService";
import axios from "axios";

class ZahtevZaSertifikatService extends BaseService {
  private zahtevPath: string = "zahtev-za-sertifikat";

  async postZahtevZaSertifikat(zahtevZaSertifikat: any): Promise<any> {
    return super.sendRequest(axios.post, zahtevZaSertifikat, this.zahtevPath);
  }
}
export default new ZahtevZaSertifikatService();
