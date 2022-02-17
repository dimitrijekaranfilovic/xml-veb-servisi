import { BaseService } from "@/services/BaseService";
import axios from "axios";

class PotvrdaOVakcinacijiService extends BaseService {
  private potvrdaOVakcinacijiPath: string = "potvrda-o-vakcinaciji";

  async getAllForUser(): Promise<any> {
    return super.sendRequest(
      axios.get,
      this.potvrdaOVakcinacijiPath + "/za-gradjanina/" + this.provideUserEmail()
    );
  }
}
export default new PotvrdaOVakcinacijiService();
