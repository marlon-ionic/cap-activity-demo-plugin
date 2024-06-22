import { KeyValuePipe } from '@angular/common';
import { Component, NgZone, OnInit, inject } from '@angular/core';
import { App, RestoredListenerEvent } from '@capacitor/app';
import { Camera, CameraResultType, CameraSource} from '@capacitor/camera';
import { IonHeader, IonToolbar, IonTitle, IonContent, IonButton } from '@ionic/angular/standalone';
import { ActivityDemo } from 'cap-activity-demo-plugin';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonContent, IonButton, KeyValuePipe],
})
export class HomePage implements OnInit{
  private readonly zone = inject(NgZone);
  restoreEvent?: RestoredListenerEvent;
  constructor() {}

  async ngOnInit(): Promise<void> {
    console.log('HomePage ngOnInit');
    App.addListener('appRestoredResult', (result) => {
      this.zone.run(() => {
        console.log('appRestoredResult:', result);
        this.restoreEvent = result;
      });
    });
  }

  async pick() {
    const result = await ActivityDemo.pick({ value: 'Pick' });
    console.log('Pick result:', result);
  }

  async camera()
  {
    const photo = await Camera.getPhoto({
      quality:50,
      allowEditing: false,
      source: CameraSource.Camera,
      resultType: CameraResultType.Uri,
    });
  }
}
