import axios from "axios";
import { BaseService } from "./BaseService";

class AuthenticationService extends BaseService {
  async login(loginRequest: any): Promise<any> {
    let response = await axios.post(
      this.basePath + "auth/login",
      this.toXML(loginRequest),
      this.getXMLConfig()
    );

    return response;
  }

  async register(registrationRequest: any): Promise<any> {
    let response = await axios.post(
      this.basePath + "auth",
      this.toXML(registrationRequest),
      this.getXMLConfig()
    );

    return response;
  }

  userLoggedIn() {
    let jwt = localStorage.getItem("jwt");
    if (jwt) {
      return true;
    } else {
      return false;
    }
  }
}

export default new AuthenticationService();
