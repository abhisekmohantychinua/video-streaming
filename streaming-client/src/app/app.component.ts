import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {HttpClient, HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet,HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'streaming-client';
  videos:Array<string>=[]
  baseUrl:string='http://localhost:8080/'
  currentVideo='first.mp4'
  constructor(private http:HttpClient) {
  }
  ngOnInit(): void {
    this.http.get(this.baseUrl).subscribe((data:any)=>{
      console.log(data)
      this.videos=data;
    })
  }

  onClick(title:string){
    this.currentVideo=title
  }

}
