import { BaseService } from "@/services/BaseService";
import axios from "axios";

class InteresovanjeService extends BaseService {
  private interesovanjePath: string = "interesovanje";

  async postInteresovanje(interesovanje: any): Promise<any> {
    return super.sendRequest(axios.post, interesovanje, this.interesovanjePath);
  }

  async getAllForUser(): Promise<any> {
    let response = await axios.get(
      this.basePath + "interesovanje/all/" + this.provideUserEmail(),
      this.getXMLConfig()
    );

    return response;
  }
}
export default new InteresovanjeService();
