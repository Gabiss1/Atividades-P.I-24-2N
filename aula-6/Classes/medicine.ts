export class Medicine{
    private medicine_Id: number;
    private medicine_Name: string;
    private medicine_Category: string;
    private medicine_Dosage: string;
    private medicine_Time_Of_Usage: string;
    private medicine_Pharmaceutical_Formula: string;

    constructor (medicine_Id:number, medicine_Name:string, medicine_Category:string, medicine_Dosage:string, medicine_Time_Of_Usage:string, medicine_Pharmaceutical_Formula:string){
        this.medicine_Id = medicine_Id;
        this.medicine_Name= medicine_Name;
        this.medicine_Category= medicine_Category;
        this.medicine_Dosage= medicine_Dosage;
        this.medicine_Time_Of_Usage= medicine_Time_Of_Usage;
        this.medicine_Pharmaceutical_Formula= medicine_Pharmaceutical_Formula
    }

    getmedicine_Id():number{
        return this.medicine_Id
    }
    
    getmedicine_Name():string{
        return this.medicine_Name
    }
    
    getmedicine_Category():string{
        return this.medicine_Category
    }
    
    getmedicine_Dosage():string{
        return this.medicine_Dosage
    }
    
    getmedicine_Time_Of_Usage():string{
        return this.medicine_Time_Of_Usage
    }
    
    getmedicine_Pharmaceutical_Formula():string{
        return this.medicine_Pharmaceutical_Formula
    }
    
}