import httpClient, { HttpClient } from "./HttpClient";

class VaccinationConsentService {
  constructor(private http: HttpClient) { }

  readHtml(id: string): Promise<any> {
    return this.http.get(`/saglasnost/html/${id}`);
  }
}

export default new VaccinationConsentService(httpClient);
