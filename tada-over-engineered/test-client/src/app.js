window.messagePassing.registerCallbackForMessages( (instruction) => {
    console.log(`Received a UI instruction: ${JSON.stringify(instruction, null, 2)}`);
    executeUiInstruction(instruction);
});


const CREATE = 0
const UPDATE = 1
const DELETE = 2

/**
 * Execute a UI instruction
 * @param instruction
 */
function executeUiInstruction(instruction) {
    let elementId = instruction.elementId;

    let type = instruction.type;
    if (type === CREATE) {
        // todo
        return;
    }

    let element = document.getElementById(elementId);
    if (type === UPDATE) {
        element.innerText = instruction.textContent;
    } else if (type === DELETE) {
        element.parentElement.removeChild(element);
    }
}
