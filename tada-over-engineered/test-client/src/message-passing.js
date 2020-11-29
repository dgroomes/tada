/**
 * Creating a gRPC client and wiring up message passing between the main process and a renderer process.
 */

let grpc = require('@grpc/grpc-js');
let {Message} = require('./echo_pb');
let {EchoClient} = require('./echo_grpc_pb');
let {contextBridge} = require('electron');

let address = 'localhost:9090';
let credentials = grpc.credentials.createInsecure();
let echoClient = new EchoClient(address, credentials);
let callback = null;

/**
 * Send a request to the gRPC server and pass the response to the renderer process via the registered callback.
 */
function sendGrpcRequest(messageString) {
    let message = new Message();
    message.setMessage(messageString);

    console.log(`*Sending* the following message to the server:\n${message.getMessage()}\n`);
    echoClient.echo(message, function (err, message) {
        if (err) {
            console.error(`The gRPC 'echo' method invocation failed.`, err);
            return;
        }

        let callbackExists = callback != null;
        if (callbackExists) {
            console.log(`*Received* a response from the gRPC server. Passing it to the renderer process.`);
            callback(message.getMessage());
        } else {
            console.log("No callback is registered. So this message is going into a black hole.")
        }
    });
}

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
 * Set up a continually running simulation of gRPC messages.
 * TODO this is just temporary. It should be replaced with a real implementation.
 */
let idx = 1;
setInterval(() => {
    console.log(`Simulating a message.`);
    let simulatedMessage = `Simulated message ${idx++}`;
    sendGrpcRequest(simulatedMessage)
}, 3000);

contextBridge.exposeInMainWorld("messagePassing", {
    registerCallbackForMessages: registerCallbackForMessages
});
