import httpClient, { HttpClient } from "./HttpClient";

class CitizenDataService {
  constructor(private http: HttpClient) { }

  readVaccinationConsents(params: any): Promise<any> {
    return this.http.getParams(`/saglasnost/za-gradjanina`, params);
  }

  readVaccinationCertificates(params: any): Promise<any> {
    return this.http.getParams(`/potvrda-o-vakcinaciji/za-gradjanina`, params);
  }

  readDigitalCertificates(params: any): Promise<any> {
    return this.http.getParams(`/digitalni-sertifikat/za-gradjanina`, params);
  }
}

export default new CitizenDataService(httpClient);
