import { BaseService } from "@/services/BaseService";
import axios from "axios";

class DigitalniSertifikatService extends BaseService {
  private digitalniSertifikatPath: string = "digitalni-sertifikat";

  async getAllForUser(): Promise<any> {
    return super.sendRequest(
      axios.get,
      this.digitalniSertifikatPath + "/za-gradjanina/" + this.provideUserEmail()
    );
  }
}
export default new DigitalniSertifikatService();
