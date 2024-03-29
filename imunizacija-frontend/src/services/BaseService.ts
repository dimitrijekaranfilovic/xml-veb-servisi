import axios from "axios";
import { json2xml } from "xml-js";
import jwt_decode from "jwt-decode";

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

  provideUserEmail(): string {
    let jwt = localStorage.getItem("jwt");
    let decoded: any = jwt_decode(jwt as string);
    return decoded.sub;
  }

  getLocalStorageJwt(): any {
    let jwt = localStorage.getItem("jwt");
    return jwt_decode(jwt as string);
  }

  async getXHTMLRepresentation(id: string, documentType: string): Promise<any> {
    let response = axios.get(
      this.basePath + documentType + "/html/" + id,
      this.getXMLConfig()
    );

    return response;
  }

  downloadXHTML(id: string, documentType: string): void {
    axios
      .get(this.basePath + documentType + "/html/" + id, {
        responseType: "blob",
      })
      .then((response) => {
        this.initialteDownload(response, documentType, "html");
      })
      .catch(console.error);
  }

  downloadPDF(id: string, documentType: string): void {
    axios
      .get(this.basePath + documentType + "/pdf/" + id, {
        responseType: "blob",
      })
      .then((response) => {
        this.initialteDownload(response, documentType, "pdf");
      })
      .catch(console.error);
  }

  initialteDownload(response: any, documentType: string, extension: string) {
    const blob = new Blob([response.data], {
      type: "application/" + extension,
    });
    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    if (extension === "html") {
      extension = "x" + extension;
    }
    link.download = documentType + "." + extension;
    link.click();
    URL.revokeObjectURL(link.href);
  }

  async sendRequest(
    restMethod: any = axios.get,
    path: string = "",
    requestBody: any = {}
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
