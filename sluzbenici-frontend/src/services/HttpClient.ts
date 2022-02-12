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

  put(url: string, payload: any): Promise<any> {
    return axios.put(this.buildUrl(url), this.toXml(payload), this.config);
  }

  post(url: string, payload: any): Promise<any> {
    return axios.post(this.buildUrl(url), this.toXml(payload), this.config);
  }

  delete(url: string): Promise<any> {
    return axios.delete(this.buildUrl(url), this.config);
  }
}

export default new HttpClient();
