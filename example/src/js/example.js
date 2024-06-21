import { ActivityDemo } from 'cap-activity-demo-plugin';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    ActivityDemo.echo({ value: inputValue })
}
