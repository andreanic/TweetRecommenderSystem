import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'tableFilter'
})
export class TableFilterPipe implements PipeTransform {

  transform(items: any[], field: string, value: string): any[] {
    if (!items) {
      return [];
    }
    if (!field) {
        return items;
    }

    let filtered = [];

    switch(field){
      case "attivi": filtered = items.filter(singleItem => this.filtraAttivi(singleItem));break;
      case "consultazioni": filtered = items.filter(singleItem => this.filtraConsultazioni(singleItem));break;
      case "prenotazioni": filtered = items.filter(singleItem => this.filtraPrenotazioni(singleItem));break;
      case "daRit": filtered = items.filter(singleItem => this.filtraDaRitirare(singleItem));break;
      case "conPren": filtered = items.filter(singleItem => this.filtraConsultazioniPrenotate(singleItem));break;
      default: filtered = items.filter(singleItem => singleItem[field].toLowerCase().includes(value.toLowerCase()));
    }

    return filtered;
  }

  private filtraConsultazioni(item: any): boolean{
    return item.tipoPrestito == "CON";
  }

  private filtraDaRitirare(item: any): boolean{
    return item.stato == "daRIT" || item.stato == "ERR";
  }

  private filtraPrenotazioni(item: any): boolean{
    return item.stato == "PREN";
  }

  private filtraAttivi(item: any): boolean{
    return (item.stato == "CONS" && item.tipoPrestito != "CON");
  }

  private filtraConsultazioniPrenotate(item: any): boolean{
    return (item.stato == "PREN" && item.tipoPrestito == "CON");
  }
}
