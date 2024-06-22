import { WebPlugin } from '@capacitor/core';

import type { ActivityDemoPickResult, ActivityDemoPlugin } from './definitions';

export class ActivityDemoWeb extends WebPlugin implements ActivityDemoPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
  async pick(options: { value: string }): Promise<ActivityDemoPickResult> {
    console.log('PICK', options);
    throw this.unavailable('ActivityDemoPlugin API not available in this browser.');
  }
}
