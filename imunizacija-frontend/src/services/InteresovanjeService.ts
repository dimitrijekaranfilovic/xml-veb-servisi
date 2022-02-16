import { BaseService } from "@/services/BaseService";
import axios from "axios";
import jwt_decode from "jwt-decode";

class InteresovanjeService extends BaseService {
  private interesovanjePath: string = "interesovanje";

  async postInteresovanje(interesovanje: any): Promise<any> {
    return super.sendRequest(axios.post, this.interesovanjePath, interesovanje);
  }

  async getAllForUser(): Promise<any> {
    let jwt = localStorage.getItem("jwt");
    let decoded: any = jwt_decode(jwt as string);
    return super.sendRequest(
      axios.get,
      this.interesovanjePath + "/all/" + decoded.sub
    );
  }
}
export default new InteresovanjeService();
