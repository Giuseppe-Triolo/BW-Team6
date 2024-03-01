export interface Client {
    id: string;
    businessName: string;
    vatNumber: number;
    email: string;
    startDate: Date;
    lastContact: Date;
    annualTurnover: number;
    pec: string; 
    number: number;
    emailReferee: string;
    nameReferee: string;
    surnameReferee: string;
    numberReferee: number;
    logo: string;
    type: string;
    adresses: [];
    invoices: [];
}
