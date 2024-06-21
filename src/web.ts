import { WebPlugin } from '@capacitor/core';

import type { ActivityDemoPlugin } from './definitions';

export class ActivityDemoWeb extends WebPlugin implements ActivityDemoPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
