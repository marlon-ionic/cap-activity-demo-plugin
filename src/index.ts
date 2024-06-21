import { registerPlugin } from '@capacitor/core';

import type { ActivityDemoPlugin } from './definitions';

const ActivityDemo = registerPlugin<ActivityDemoPlugin>('ActivityDemo', {
  web: () => import('./web').then(m => new m.ActivityDemoWeb()),
});

export * from './definitions';
export { ActivityDemo };
