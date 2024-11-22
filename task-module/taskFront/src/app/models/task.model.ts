// src/app/models/task.model.ts
export class Task {
    id?: number;
    title: string;
    description: string;
  
    constructor(title: string, description: string, id?: number) {
      this.title = title;
      this.description = description;
      this.id = id;
    }
  }
  