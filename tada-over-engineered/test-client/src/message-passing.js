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
 * Send a request to the gRPC server to receive an indefinite stream of "UI instruction" responses. Pass each UI
 * instruction  to the renderer process via the registered callback.
 */
function streamInstructionsFromGrpcServer(clientId) {
    let message = new ClientRequest();
    message.setClientid(clientId);

    console.log(`Sending a request to the gRPC server to get a streaming response of next UI instructions`);

    let call = client.nextInstructions(message, function (err) {
        if (err) {
            console.error(`The gRPC 'nextInstructions' method invocation failed.`, err);
        }
    });

    call.on('data', function(instruction) {
        let callbackExists = callback != null;
        if (callbackExists) {
            console.log(`Received a response from the gRPC server. Passing it to the renderer process.`);
            console.log(`getTextcontent: ${instruction.getTextcontent()}`);
            console.log(`getElementid: ${instruction.getElementid()}`);

            // Hmmm, Electron strips out the Prototype chain for the type of 'instruction' when we pass it across the
            // wall... I think? So to work around this, I'll just get the contents and put the into a simple object
            callback({
                textContent: instruction.getTextcontent(),
                elementId: instruction.getElementid(),
                type: instruction.getType()
            });
        } else {
            console.log("No callback is registered. So this message is going into a black hole.");
        }
    });
    call.on('end', function() {
        console.log('The gRPC server has finished sending responses');
    });
    call.on('error', function(e) {
        console.error('An error has occurred and the gRPC stream has ended');
        console.error(e);
    });

    // I dont' know what 'status' means but let's log it to find out.
    call.on('status', function(status) {
        console.log(`gRPC 'nextInstructions' stream 'status' event occurred: ${status}`);
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
 * Start an indefinite stream of UI instructions from the gRPC server
 */
streamInstructionsFromGrpcServer(1);

contextBridge.exposeInMainWorld("messagePassing", {
    registerCallbackForMessages: registerCallbackForMessages
});
