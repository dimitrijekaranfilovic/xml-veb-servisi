import httpClient, { HttpClient } from "./HttpClient";

class ReportsService {
  constructor(private http: HttpClient) {}

  getReports(): Promise<any> {
    return this.http.get("/izvestaj-o-imunizaciji");
  }

  generateReport(payload: any): Promise<any> {
    return this.http.post("/izvestaj-o-imunizaciji", payload);
  }

  getReport(id: string): Promise<any> {
    return this.http.get(`/izvestaj-o-imunizaciji/${id}`);
  }

  getReportAsHtml(id: string): Promise<any> {
    return this.http.get(`/izvestaj-o-imunizaciji/html/${id}`);
  }
}

export default new ReportsService(httpClient);
