import { BaseService } from "@/services/BaseService";
import axios from "axios";

class InteresovanjeService extends BaseService {
  private interesovanjePath: string = "interesovanje";

  async postInteresovanje(interesovanje: any): Promise<any> {
    super.sendRequest(axios.post, interesovanje, this.interesovanjePath);
  }
}

export default new InteresovanjeService();
