import axios from "axios";
import { BaseService } from "@/services/BaseService";

class AuthenticationService extends BaseService {
  async login(loginRequest: any): Promise<any> {
    return super.sendRequest(axios.post, "auth/login", loginRequest);
  }

  async register(registrationRequest: any): Promise<any> {
    return super.sendRequest(axios.post, "auth", registrationRequest);
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
