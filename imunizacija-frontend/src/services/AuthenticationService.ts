import axios from "axios";
import { json2xml } from "xml-js";

class AuthenticationService {
  async login(loginRequest: any): Promise<any> {
    const xmlLoginRequest = json2xml(loginRequest, {
      compact: true,
    });

    let config = {
      headers: { "Content-Type": "application/xml" },
    };

    let response = await axios.post(
      "http://localhost:8081/auth/login",
      xmlLoginRequest,
      config
    );

    return response;
  }

  async register(registrationRequest: any): Promise<any> {
    const xmlRegistrationRequest = json2xml(registrationRequest, {
      compact: true,
    });

    let config = {
      headers: { "Content-Type": "application/xml" },
    };

    console.log(xmlRegistrationRequest);

    let response = await axios.post(
      "http://localhost:8081/auth",
      xmlRegistrationRequest,
      config
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
