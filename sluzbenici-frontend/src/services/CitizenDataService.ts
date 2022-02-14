import httpClient, { HttpClient } from "./HttpClient";

class CitizenDataService {
  constructor(private http: HttpClient) { }

  readVaccinationConsents(query: string): Promise<any> {
    return this.http.get(`/saglasnost/za-gradjanina?query=${query}`);
  }

  readVaccinationCertificates(query: string): Promise<any> {
    return this.http.get(`/potvrda-o-vakcinaciji/za-gradjanina?query=${query}`);
  }

  readDigitalCertificates(query: string): Promise<any> {
    return this.http.get(`/digitalni-sertifikat/za-gradjanina?query=${query}`);
  }
}

export default new CitizenDataService(httpClient);
