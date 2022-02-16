import httpClient, { HttpClient } from "./HttpClient";

class VaccinationService {
  constructor(private http: HttpClient) {}

  getConsents(email: string): Promise<any> {
    return this.http.get(`/saglasnost/za-sluzbenika?email=${email}`);
  }

  getConsent(id: string): Promise<any> {
    return this.http.get(`/saglasnost/jedan-za-sluzbenika/${id}`);
  }
}

export default new VaccinationService(httpClient);
