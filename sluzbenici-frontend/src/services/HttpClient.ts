import axios from "axios";
import { json2xml } from "xml-js";

const BASE_PATH = "http://localhost:8082";

export class HttpClient {
  config = {
    headers: { "Content-Type": "application/xml" },
  };

  toXml(request: any): any {
    return json2xml(request, { compact: true });
  }

  buildUrl(url: string): string {
    return `${BASE_PATH}${url}`;
  }

  get(url: string): Promise<any> {
    return axios.get(this.buildUrl(url), this.config);
  }

  getParams(url: string, params: any): Promise<any> {
    return axios.get(this.buildUrl(url), {
      ...this.config.headers,
      params: params,
    });
  }

  put(url: string, payload: any): Promise<any> {
    return axios.put(this.buildUrl(url), this.toXml(payload), this.config);
  }

  post(url: string, payload: any): Promise<any> {
    return axios.post(this.buildUrl(url), this.toXml(payload), this.config);
  }

  delete(url: string): Promise<any> {
    return axios.delete(this.buildUrl(url), this.config);
  }

  downloadXHTML(id: string, documentType: string): void {
    axios
      .get(`${BASE_PATH}/${documentType}/html/${id}`, {
        responseType: "blob",
      })
      .then((response) => {
        this.initiateDownload(response, documentType, "html");
      })
      .catch(console.error);
  }

  downloadPDF(id: string, documentType: string): void {
    axios
      .get(`${BASE_PATH}/${documentType}/pdf/${id}`, {
        responseType: "blob",
      })
      .then((response) => {
        this.initiateDownload(response, documentType, "pdf");
      })
      .catch(console.error);
  }

  initiateDownload(response: any, documentType: string, extension: string) {
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
}

export default new HttpClient();
