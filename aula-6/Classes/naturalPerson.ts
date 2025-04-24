import { User } from "./user";

export class NaturalPerson extends User{
    private natural_Person_ID:number
    private natural_Person_CPF:string

    constructor(id: number, name: string, birth_Date:string, address: string, contact: string, email: string, specifications: string, cpf:string, natural_ID:number){
        super(id, name, birth_Date, address, contact, email, specifications)
        this.natural_Person_ID = natural_ID
        this.natural_Person_CPF = cpf
    }

    getNaturalPersonID():number{
        return this.natural_Person_ID
    }

    getNaturalPersonCPF():string{
        return this.natural_Person_CPF
    }
}