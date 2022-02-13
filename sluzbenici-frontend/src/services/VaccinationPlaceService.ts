import httpClient, { HttpClient } from "./HttpClient";

class VaccinationPlaceService {
  constructor(private http: HttpClient) {}

  getVaccinationPlaces(): Promise<any> {
    return this.http.get("/vakcinacioni-punkt");
  }

  getVaccinationPlace(id: string): Promise<any> {
    return this.http.get(`/vakcinacioni-punkt/${id}`);
  }

  createVaccinationPlace(payload: any): Promise<any> {
    return this.http.post("/vakcinacioni-punkt", payload);
  }
}

export default new VaccinationPlaceService(httpClient);
