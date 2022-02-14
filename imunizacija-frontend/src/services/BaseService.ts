import axios from "axios";
import { json2xml } from "xml-js";

export class BaseService {
  async sendRequest(request: any, path: string): Promise<any> {
    const xmlRequest = json2xml(request, {
      compact: true,
    });

    let config = {
      headers: { "Content-Type": "application/xml" },
    };

    let response = await axios.post(
      "http://localhost:8081/" + path,
      xmlRequest,
      config
    );

    return response;
  }
}

export default new BaseService();
