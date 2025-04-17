import { User } from "./user";

export class Institute extends User{
    private institute_ID: number
    private institute_CNPJ: string

    constructor(id: number, name: string, birth_Date: string, address: string, contact: string, email: string, specifications: string, institute_ID: number, cnpj: string){
        super(id, name, birth_Date, address, contact, email, specifications)
        this.institute_ID = institute_ID
        this.institute_CNPJ = cnpj
    }

    getInstituteId():number{
        return this.institute_ID
    }

    getCNPJ():string{
        return this.institute_CNPJ
    }
}