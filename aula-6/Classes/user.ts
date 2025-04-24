export class User {
    private user_ID: number
    private user_Name: string
    private user_Birth_Date: string
    private user_Address: string
    private user_Contact: string
    private user_Email: string
    private user_Specifications: string

    constructor(id: number, name: string, birth_Date: string, address: string, contact: string, email: string, specifications: string){
        this.user_ID = id
        this.user_Name = name
        this.user_Birth_Date = birth_Date
        this.user_Address = address
        this.user_Contact = contact
        this.user_Email = email
        this.user_Specifications = specifications
    }

    getId():number{
        return this.user_ID
    }

    getName():string{
        return this.user_Name
    }

    getBirthDate():string{
        return this.user_Birth_Date
    }

    getAddress():string{
        return this.user_Address
    }

    getContact():string{
        return this.user_Contact
    }

    getEmail():string{
        return this.user_Email
    }

    getSpecifications():string{
        return this.user_Specifications
    }
}