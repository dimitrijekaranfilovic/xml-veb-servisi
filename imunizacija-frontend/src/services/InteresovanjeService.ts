import { BaseService } from "@/services/BaseService";

class InteresovanjeService extends BaseService {
  private basePath: string = "interesovanje";

  async sendRequest(interesovanje: any): Promise<any> {
    super.sendRequest(interesovanje, this.basePath);
  }
}

export default new InteresovanjeService();
