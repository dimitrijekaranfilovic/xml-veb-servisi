import httpClient, { HttpClient } from "./HttpClient";

class DigitalCertificateService {
  constructor(private http: HttpClient) { }

  readHtml(id: string): Promise<any> {
    return this.http.get(`/digitalni-sertifikat/html/${id}`);
  }
}

export default new DigitalCertificateService(httpClient);
