export interface ActivityDemoPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
