import httpClient, { HttpClient } from "./HttpClient";

class DigitalCertificateRequestsService {
  constructor(private http: HttpClient) { }

  read(): Promise<any> {
    return this.http.get("/zahtev-za-sertifikat");
  }

  readOne(id: string): Promise<any> {
    return this.http.get(`/zahtev-za-sertifikat/${id}`);
  }
}

export default new DigitalCertificateRequestsService(httpClient);