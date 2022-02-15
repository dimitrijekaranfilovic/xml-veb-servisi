import httpClient, { HttpClient } from "./HttpClient";

class VaccinationService {
  constructor(private http: HttpClient) {}

  getConsents(email: string): Promise<any> {
    return this.http.get(`/saglasnost/za-sluzbenika?email=${email}`);
  }
}

export default new VaccinationService(httpClient);
