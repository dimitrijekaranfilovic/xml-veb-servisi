import httpClient, { HttpClient } from "./HttpClient";

class CitizenDataService {
  constructor(private http: HttpClient) { }

  readVaccinationConsents(email: string, query: string): Promise<any> {
    return this.http.get(`/saglasnost/za-gradjanina/${email}?query=${query}`);
  }

  readVaccinationCertificates(email: string, query: string): Promise<any> {
    return this.http.get(`/potvrda-o-vakcinaciji/za-gradjanina/${email}?query=${query}`);
  }

  readDigitalCertificates(email: string, query: string): Promise<any> {
    return this.http.get(`/digitalni-sertifikat/za-gradjanina/${email}?query=${query}`);
  }
}

export default new CitizenDataService(httpClient);
