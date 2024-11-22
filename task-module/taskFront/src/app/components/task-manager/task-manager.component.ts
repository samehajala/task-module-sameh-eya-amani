// src/app/components/task-manager/task-manager.component.ts
import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../services/task.service';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-task-manager',
  templateUrl: './task-manager.component.html',
  styleUrls: ['./task-manager.component.css']
})
export class TaskManagerComponent implements OnInit {
  tasks: Task[] = [];
  newTask: Task = new Task('', '');
  editingTask: Task | null = null;

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks(): void {
    this.taskService.getTasks().subscribe(tasks => {
      this.tasks = tasks;
    });
  }

  addTask(): void {
    if (this.newTask.title.trim() && this.newTask.description.trim()) {
      this.taskService.addTask(this.newTask).subscribe(() => {
        this.newTask = new Task('', ''); // Reset form
        this.loadTasks(); // Refresh task list
      });
    }
  }

  startEditing(task: Task): void {
    this.editingTask = { ...task };
  }

  updateTask(): void {
    if (this.editingTask) {
      this.taskService.updateTask(this.editingTask.id!, this.editingTask).subscribe(() => {
        this.editingTask = null;
        this.loadTasks();
      });
    }
  }

  deleteTask(id: number): void {
    this.taskService.deleteTask(id).subscribe(() => {
      this.loadTasks();
    });
  }

  cancelEditing(): void {
    this.editingTask = null;
  }
}
