import httpClient, { HttpClient } from "./HttpClient";

class DigitalCertificateRequestsService {
  constructor(private http: HttpClient) { }

  read(): Promise<any> {
    return this.http.get("/zahtev-za-sertifikat");
  }
}

export default new DigitalCertificateRequestsService(httpClient);