import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

import { Tarefa } from '../interface/tarefa';

@Injectable({
  providedIn: 'root',
})
export class TarefaService {
  private readonly API = 'http://localhost:3000/tarefas';
  private tarefasSubjet = new BehaviorSubject<Tarefa[]>([]);
  tarefas$ = this.tarefasSubjet.asObservable();

  constructor(private http: HttpClient) {}

  listar(): void {
    let params = new HttpParams().appendAll({
      _sort: 'id',
      _order: 'desc',
    });
    this.http.get<Tarefa[]>(this.API, { params }).subscribe((tarefas) => {
      let tarefasTemp = this.tarefasSubjet.getValue();
      tarefasTemp = tarefasTemp.concat(tarefas);
      this.tarefasSubjet.next(tarefasTemp);
    });
  }

  criar(tarefa: Tarefa): void {
    this.http.post<Tarefa>(this.API, tarefa).subscribe((novaTarefa) => {
      const tarefas = this.tarefasSubjet.getValue();
      tarefas.unshift(novaTarefa);
      this.tarefasSubjet.next(tarefas);
    });
  }

  editar(tarefa: Tarefa, atualizarSubject: boolean): void {
    const url = `${this.API}/${tarefa.id}`;
    this.http.put<Tarefa>(url, tarefa).subscribe((tarefaEditada) => {
      if (atualizarSubject) {
        const tarefas = this.tarefasSubjet.getValue();
        const index = tarefas.findIndex((tarefa) => tarefa.id === tarefaEditada.id);
        if (index !== -1) {
          tarefas[index] = tarefaEditada;
        }
        this.tarefasSubjet.next(tarefas);
      }
    });
  }

  excluir(id: number): void {
    const url = `${this.API}/${id}`;
    this.http.delete<Tarefa>(url).subscribe(() => {
      const tarefas = this.tarefasSubjet.getValue();
      const index = tarefas.findIndex((tarefa) => tarefa.id === id);
      if (index !== -1) {
        tarefas.splice(index, 1);
        this.tarefasSubjet.next(tarefas);
      }
    });
  }

  buscarPorId(id: number): Observable<Tarefa> {
    const url = `${this.API}/${id}`;
    return this.http.get<Tarefa>(url);
  }

  atualizarStatusTarefa(tarefa: Tarefa): void {
    tarefa.statusFinalizado = !tarefa.statusFinalizado;
    this.editar(tarefa, false);
  }
}
