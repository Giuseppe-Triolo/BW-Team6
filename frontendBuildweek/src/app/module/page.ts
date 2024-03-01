import { Client } from "./client"

export interface Page {
    
content: Client[];
empty: boolean;
first: boolean;
last: boolean;
number: number;
numberOfElements: number;
pageable: {pageNumber: number, pageSize: number, sort: {empty: boolean; sorted: boolean; unsorted: boolean}, offset: number, unpaged: boolean}
size: number;
sort: {empty: boolean; sorted: boolean; unsorted: boolean};
totalElements: number;
totalPages: number;
}
