import { BaseService } from "@/services/BaseService";
import axios from "axios";

class InteresovanjeService extends BaseService {
  private interesovanjePath: string = "interesovanje";

  async postInteresovanje(interesovanje: any): Promise<any> {
    return super.sendRequest(axios.post, this.interesovanjePath, interesovanje);
  }

  async getAllForUser(): Promise<any> {
    return super.sendRequest(
      axios.get,
      this.interesovanjePath + "/all/" + this.getLocalStorageJwt().sub
    );
  }
}
export default new InteresovanjeService();
