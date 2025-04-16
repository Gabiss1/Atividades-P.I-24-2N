export class User {
    user_ID: number
    user_Name: string
    user_Address: string
    user_Contact: string
    user_Email: string

    constructor(id: number, name: string, address: string, contact: string, email: string){
        this.user_ID = id
        this.user_Name = name
        this.user_Address = address
        this.user_Contact = contact
        this.user_Email = email
    }
}