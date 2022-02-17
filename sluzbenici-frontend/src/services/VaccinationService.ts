import httpClient, { HttpClient } from "./HttpClient";

class VaccinationService {
  constructor(private http: HttpClient) {}

  getConsents(email: string): Promise<any> {
    return this.http.get(`/saglasnost/za-sluzbenika?email=${email}`);
  }

  getConsent(id: string): Promise<any> {
    return this.http.get(`/saglasnost/jedan-za-sluzbenika/${id}`);
  }

  createDoctorAndBuilding(id: string, payload: any): Promise<any> {
    return this.http.post(
      `/saglasnost/${id}/podaci-o-lekaru-ustanovi`,
      payload
    );
  }

  changeDecision(id: string): Promise<any> {
    return this.http.get(`/saglasnost/${id}/odluka-komisije`);
  }

  addSideEffect(id: string, payload: any): Promise<any> {
    return this.http.post(`/saglasnost/${id}/kontraindikacije`, payload);
  }

  addVaccine(id: string, payload: any): Promise<any> {
    return this.http.post(`/saglasnost/${id}/vakcine`, payload);
  }
}

export default new VaccinationService(httpClient);
