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

  addAppointment(id: string, payload: any): Promise<any> {
    return this.http.post(`/vakcinacioni-punkt/termin/${id}/`, payload);
  }

  addVaccine(id: string, payload: any): Promise<any> {
    return this.http.post(
      `/vakcinacioni-punkt/dostupna-vakcina/${id}`,
      payload
    );
  }

  updateVaccine(id: string, payload: any): Promise<any> {
    return this.http.put(`/vakcinacioni-punkt/dostupna-vakcina/${id}`, payload);
  }
}

export default new VaccinationPlaceService(httpClient);
