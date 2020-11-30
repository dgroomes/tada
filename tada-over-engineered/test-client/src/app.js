window.messagePassing.registerCallbackForMessages( (instruction) => {
    console.log(`Received a UI instruction: ${instruction}`);
    executeUiInstrction(instruction);
});


/**
 * Execute a UI instruction
 * @param instruction
 */
function executeUiInstrction(instruction) {
    document.getElementById("server-instructed-text-content").innerText = instruction;
}
