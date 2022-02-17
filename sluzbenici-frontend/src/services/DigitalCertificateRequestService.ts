import httpClient, { HttpClient } from "./HttpClient";

class DigitalCertificateRequestsService {
  constructor(private http: HttpClient) { }

  read(): Promise<any> {
    return this.http.get("/zahtev-za-sertifikat");
  }

  readOne(id: string): Promise<any> {
    return this.http.get(`/zahtev-za-sertifikat/${id}`);
  }

  approve(id: string): Promise<any> {
    return this.http.post(`/zahtev-za-sertifikat/${id}/odobri`, {});
  }

  reject(id: string, reason: any): Promise<any> {
    return this.http.post(`/zahtev-za-sertifikat/${id}/odbij`, reason);
  }

  readHtml(id: string): Promise<any> {
    return this.http.get(`/zahtev-za-sertifikat/html/${id}`);
  }
}

export default new DigitalCertificateRequestsService(httpClient);
