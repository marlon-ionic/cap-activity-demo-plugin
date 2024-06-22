export interface ActivityDemoPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  pick(options: { value: string }): Promise<ActivityDemoPickResult>;
}


export interface ActivityDemoPickResult {
  value: string;
  name: string;
  phone: string;
}