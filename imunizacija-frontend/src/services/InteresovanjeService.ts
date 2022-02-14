import axios from "axios";
import jwt_decode from "jwt-decode";

class InteresovanjeService {
  async getAllForUser(): Promise<any> {
    let config = {
      headers: { "Content-Type": "application/xml" },
    };

    let jwt = localStorage.getItem("jwt");
    let decoded: any = jwt_decode(jwt as string);

    let response = await axios.get(
      "http://localhost:8081/interesovanje/all/" + decoded.sub,
      config
    );

    return response;
  }
}

export default new InteresovanjeService();
