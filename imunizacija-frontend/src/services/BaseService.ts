import axios from "axios";
import { json2xml } from "xml-js";

export class BaseService {
  private basePath: string = "http://localhost:8081/";

  async sendRequest(
    restMethod: any = axios.get,
    requestBody: any = {},
    path: string = ""
  ): Promise<any> {
    const xmlRequest = json2xml(requestBody, {
      compact: true,
    });
    let config = {
      headers: { "Content-Type": "application/xml" },
    };
    let response = await restMethod(this.basePath + path, xmlRequest, config);
    return response;
  }
}

export default new BaseService();
