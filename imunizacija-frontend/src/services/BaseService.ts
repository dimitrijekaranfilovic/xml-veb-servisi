import axios from "axios";
import { json2xml } from "xml-js";

export class BaseService {
  basePath: string = "http://localhost:8081/";

  getXMLConfig(): any {
    return {
      headers: { "Content-Type": "application/xml" },
    };
  }

  toXML(entity: any): any {
    return json2xml(entity, {
      compact: true,
    });
  }

  initialteXHTMLDownload(response: any, documentType: string) {
    const blob = new Blob([response.data], { type: "application/html" });
    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.download = documentType + ".xhtml";
    link.click();
    URL.revokeObjectURL(link.href);
  }

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
