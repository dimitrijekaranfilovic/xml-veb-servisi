import httpClient, { HttpClient } from "./HttpClient";

class VaccinationService {
  constructor(private http: HttpClient) {}
}

export default new VaccinationService(httpClient);
