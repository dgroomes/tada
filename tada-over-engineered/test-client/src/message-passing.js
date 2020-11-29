/**
 * Wiring up message passing between the main process and a renderer process.
 */

const {contextBridge} = require('electron');

let callback = null;

/*
 * Define a function that can be used by a renderer process to register a callback.
 *
 * I'm not quite sure this is the "right way" to do this. This is a pretty circuitous way to wire up the renderer
 * process to receive messages from the main process, but it works and it uses the contextBridge (a good security
 * practice, so it seems)
 *
 * What is the recommended way to message pass from the main process to the renderer process? It seems there is
 * conflicting advice in the Electron docs:
 *   * [`contents.send(channel, ...args)`](https://www.electronjs.org/docs/api/web-contents#contentssendchannel-args)
 *     says to use `ipcRenderer` to register a callback from the renderer process to receive messages.
 *   * But [Content Isolation: Security Considerations](https://www.electronjs.org/docs/tutorial/context-isolation#security-considerations)
 *     says to not expose `ipcRenderer` in the renderer.
 */
function registerCallbackForMessages(fn) {
    console.log("Registering a callback");
    callback = fn;
}

/**
 * Set up a continually running simulation of messages from the main process to a renderer process via the callback.
 */
let idx = 1;
setInterval(() => {
    let callbackExists = callback != null;
    console.log(`Simulating a message. callBackExists=${callbackExists}`);

    let simulatedMessage = `Simulated message ${idx++}`;
    if (callbackExists) {
        callback(simulatedMessage);
    } else {
        console.log("No callback is registered. So this message is going into a black hole.")
    }

}, 1000);

contextBridge.exposeInMainWorld("messagePassing", {
    registerCallbackForMessages: registerCallbackForMessages
});