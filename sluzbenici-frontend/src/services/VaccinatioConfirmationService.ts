import httpClient, { HttpClient } from "./HttpClient";

class VaccinationConfirmationService {
  constructor(private http: HttpClient) {}

  getConfirmationAsHtml(id: string): Promise<any> {
    return this.http.get(`/potvrda-o-vakcinaciji/html/${id}`);
  }
}

export default new VaccinationConfirmationService(httpClient);
