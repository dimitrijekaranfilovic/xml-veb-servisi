import httpClient, { HttpClient } from "./HttpClient";

class CitizenDataService {
  constructor(private http: HttpClient) { }

  readVaccinationConsents(): Promise<any> {
    return this.http.get(`/saglasnost/za-gradjanina`);
  }

  readVaccinationCertificates(): Promise<any> {
    return this.http.get(`/potvrda-o-vakcinaciji/za-gradjanina`);
  }

  readDigitalCertificates(): Promise<any> {
    return this.http.get(`/digitalni-sertifikat/za-gradjanina`);
  }
}

export default new CitizenDataService(httpClient);
