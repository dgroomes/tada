/**
 * Creating a gRPC client and wiring up message passing between the main process and a renderer process.
 */

let grpc = require('@grpc/grpc-js');
let {ClientRequest, Instruction} = require('./user-interface_pb');
let {UserInterfaceDriverClient} = require('./user-interface_grpc_pb');
let {contextBridge} = require('electron');

let address = 'localhost:9090';
let credentials = grpc.credentials.createInsecure();
let client = new UserInterfaceDriverClient(address, credentials);
let callback = null;

/**
 * Send a request to the gRPC server and pass the response to the renderer process via the registered callback.
 */
function sendGrpcRequest(clientId) {
    let message = new ClientRequest();
    message.setClientid(clientId);

    console.log(`*Sending* a request to the gRPC for the next UI instruction`);
    client.nextInstruction(message, function (err, instruction) {
        if (err) {
            console.error(`The gRPC 'nextInstruction' method invocation failed.`, err);
            return;
        }

        let callbackExists = callback != null;
        if (callbackExists) {
            console.log(`*Received* a response from the gRPC server. Passing it to the renderer process.`);
            callback(instruction.getTextcontent());
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
setInterval(() => {
    console.log(`Simulating a message.`);
    sendGrpcRequest(1)
}, 3000);

contextBridge.exposeInMainWorld("messagePassing", {
    registerCallbackForMessages: registerCallbackForMessages
});
